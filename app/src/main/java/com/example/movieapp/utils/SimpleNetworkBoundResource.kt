package com.example.movieapp.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

//inline fun <ResultType> simpleNetworkBoundResource(
//    crossinline fetch: suspend () -> ResultType,
//) = flow {
//    emit(Resource.Loading(null))
//    try {
//        val result = fetch()
//        Resource.Success(result)
//    } catch (throwable: Throwable) {
//        Resource.Error(throwable, null)
//    }
//}

inline fun <ResultType> simpleNetworkBoundResource(
    crossinline fetch: suspend () -> ResultType
): Flow<Resource<ResultType>> = flow {
    emit(Resource.Loading(null))
    try {
        val result = fetch()
        emit(Resource.Success(result))
    } catch (throwable: Throwable) {
        emit(Resource.Error(throwable, null))
    }
}
