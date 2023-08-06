package com.example.movieapp.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

inline fun <ResultType, RequestType> convertNetworkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
    crossinline mapToEntity: (RequestType) -> ResultType // Mapper function to convert RequestType to ResultType
) = flow {
    // Get the cached data from the database (room, SQLite, etc.)
    val data = query().first()

    if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
            // Fetch data from the network and map it to the database entity before saving
            val fetchedData = fetch()
            saveFetchResult(fetchedData)
            val mappedData = mapToEntity(fetchedData)
            emit(Resource.Success(mappedData))
        } catch (throwable: Throwable) {
            emit(Resource.Error(throwable, data))
        }
    } else {
        // If shouldFetch returns false, emit the Success state with the cached data
        query().collect { cachedData ->
            emit(Resource.Success(cachedData))
        }
    }
}
