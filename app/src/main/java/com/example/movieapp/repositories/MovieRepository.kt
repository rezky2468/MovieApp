package com.example.movieapp.repositories

import com.example.movieapp.BuildConfig
import androidx.room.withTransaction
import com.example.movieapp.api.ApiService
import com.example.movieapp.api.MovieDatabase
import com.example.movieapp.utils.networkBoundResource

import javax.inject.Inject

//class MovieRepository {
//
//    private val client = ApiConfig.getApiService()
//
//    suspend fun getPopularMovies(page: Int) = client.getPopularMovies(BuildConfig.API_KEY, page)
//
//}


class MovieRepository @Inject constructor(
    private val api: ApiService,
    private val db: MovieDatabase
) {

    private val movieDao = db.movieDao()

    fun getNowPlayingMovies() = networkBoundResource(
        query = {
            movieDao.getNowPlayingMovies()
        },
        fetch = {
//            delay(2000)
            api.getNowPlayingMovies(BuildConfig.API_KEY).results
        },
        saveFetchResult = {
            db.withTransaction {
                movieDao.deleteNowPlayingMovies()
                movieDao.insertNowPlayingMovies(it)
            }
        }
    )

    fun getUpcomingMovies() = networkBoundResource(
        query = {
            movieDao.getUpcomingMovies()
        },
        fetch = {
            api.getUpcomingMovies(BuildConfig.API_KEY).results
        },
        saveFetchResult = {
            db.withTransaction {
                movieDao.deleteUpcomingMovies()
                movieDao.insertUpcomingMovies(it)
            }
        }
    )



}