package com.example.movieapp.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,                  // Lambda to get the cached data
    crossinline fetch: suspend () -> RequestType,               // Lambda to fetch data from the network
    crossinline saveFetchResult: suspend (RequestType) -> Unit, // Lambda to save the fetched result to the database
    crossinline shouldFetch: (ResultType) -> Boolean = { true } // Lambda to determine if a network request is needed
) = flow {
    // Get the cached data from the database (room, SQLite, etc.)
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        // Emit a Loading state with the cached data while fetching new data
        emit(Resource.Loading(data))
        try {
            // Fetch data from the network and save it to the database
            saveFetchResult(fetch())
            // After saving the fetched result, emit the new Success state with the updated data
            query().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            // If an error occurs during fetching, emit the Error state with the cached data
            query().map { Resource.Error(throwable, it) }
        }
    } else {
        // If shouldFetch returns false, emit the Success state with the cached data
        query().map { Resource.Success(it) }
    }

    // Emit the states to the Flow so that the observing components can react accordingly
    emitAll(flow)
}





//inline fun <ResultType, RequestType> networkBoundResource(
//    crossinline query: () -> Flow<ResultType>,
//    crossinline fetch: suspend () -> RequestType,
//    crossinline saveFetchResult: suspend (RequestType) -> Unit,
//    crossinline shouldFetch: (ResultType) -> Boolean = { true }
//) = flow {
//    val data = query().first()
//    val flow = if (shouldFetch(data)) {
//        emit(Resource.Loading(data))
//        try {
//            saveFetchResult(fetch()) // Pass the response directly
//            query().map { Resource.Success(it) }
//        } catch (throwable: Throwable) {
//            query().map { Resource.Error(throwable, it) }
//        }
//    } else {
//        query().map { Resource.Success(it) }
//    }
//    emitAll(flow)
//}
