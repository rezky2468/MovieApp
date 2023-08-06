package com.example.movieapp.repositories

import com.example.movieapp.BuildConfig
import androidx.room.withTransaction
import com.example.movieapp.api.ApiService
import com.example.movieapp.api.LocalDatabase
import com.example.movieapp.models.movies.MovieCreditResponse
import com.example.movieapp.models.movies.MovieDetailsEntity
import com.example.movieapp.models.movies.MovieDetailsResponse
import com.example.movieapp.models.movies.MovieGenreEntity
import com.example.movieapp.models.movies.MovieImageResponse
import com.example.movieapp.models.movies.MovieVideoResponse
import com.example.movieapp.utils.Resource
import com.example.movieapp.utils.convertNetworkBoundResource
import com.example.movieapp.utils.networkBoundResource
import com.example.movieapp.utils.simpleNetworkBoundResource
import com.google.gson.Gson
import kotlinx.coroutines.delay

import javax.inject.Inject
import javax.inject.Named

class MovieRepository @Inject constructor(
    @Named("movieApi") private val movieApi: ApiService,
    @Named("newsApi") private val newsApi: ApiService,
    private val db: LocalDatabase
) {

    private val localDao = db.localDao()

    fun getMovieNowPlaying() = networkBoundResource(
        query = {
            localDao.getMovieNowPlaying()
        },
        fetch = {
            movieApi.getMovieNowPlaying(BuildConfig.TMDB_API_KEY).results
        },
        saveFetchResult = {
            db.withTransaction {
                localDao.deleteMovieNowPlaying()
                localDao.insertMovieNowPlaying(it)
            }
        }
    )
    fun getMovieUpcoming() = networkBoundResource(
        query = {
            localDao.getMovieUpcoming()
        },
        fetch = {
            movieApi.getMovieUpcoming(BuildConfig.TMDB_API_KEY).results
        },
        saveFetchResult = {
            db.withTransaction {
                localDao.deleteMovieUpcoming()
                localDao.insertMovieUpcoming(it)
            }
        }
    )
    fun getMoviePopular() = networkBoundResource(
        query = {
            localDao.getMoviePopular()
        },
        fetch = {
            movieApi.getMoviePopular(BuildConfig.TMDB_API_KEY).results
        },
        saveFetchResult = {
            db.withTransaction {
                localDao.deleteMoviePopular()
                localDao.insertMoviePopular(it)
            }
        }
    )
    fun getMovieTopRated() = networkBoundResource(
        query = {
            localDao.getMovieTopRated()
        },
        fetch = {
            movieApi.getMovieTopRated(BuildConfig.TMDB_API_KEY).results
        },
        saveFetchResult = {
            db.withTransaction {
                localDao.deleteMovieTopRated()
                localDao.insertMovieTopRated(it)
            }
        }
    )

    fun getMovieDetails(movieId: Int) = simpleNetworkBoundResource(
        fetch = {
            movieApi.getMovieDetails(movieId, BuildConfig.TMDB_API_KEY, "images,videos,credits,similar,recommendations")
        }
    )

    fun getMovieSearch(query: String) = networkBoundResource(
        query = {
            localDao.getMovieSearch()
        },
        fetch = {
            movieApi.getMovieSearch(BuildConfig.TMDB_API_KEY, query).results
        },
        saveFetchResult = {
            db.withTransaction {
                localDao.deleteMovieSearch()
                localDao.insertMovieSearch(it)
            }
        }
    )
    fun getMultiSearch(query: String) = simpleNetworkBoundResource(
        fetch = {
            movieApi.getMultiSearch(BuildConfig.TMDB_API_KEY, query, false).results
        }
    )

    //======================================== TV SERIES ========================================//

    fun getTvAiringToday() = networkBoundResource(
        query = {
            localDao.getTvAiringToday()
        },
        fetch = {
            movieApi.getTvAiringToday(BuildConfig.TMDB_API_KEY).results
        },
        saveFetchResult = {
            db.withTransaction {
                localDao.deleteTvAiringToday()
                localDao.insertTvAiringToday(it)
            }
        }
    )
    fun getTvOnTheAir() = networkBoundResource(
        query = {
            localDao.getTvOnTheAir()
        },
        fetch = {
            movieApi.getTvOnTheAir(BuildConfig.TMDB_API_KEY).results
        },
        saveFetchResult = {
            db.withTransaction {
                localDao.deleteTvOnTheAir()
                localDao.insertTvOnTheAir(it)
            }
        }
    )
    fun getTvPopular() = networkBoundResource(
        query = {
            localDao.getTvPopular()
        },
        fetch = {
            movieApi.getTvPopular(BuildConfig.TMDB_API_KEY).results
        },
        saveFetchResult = {
            db.withTransaction {
                localDao.deleteTvPopular()
                localDao.insertTvPopular(it)
            }
        }
    )
    fun getTvTopRated() = networkBoundResource(
        query = {
            localDao.getTvTopRated()
        },
        fetch = {
            movieApi.getTvTopRated(BuildConfig.TMDB_API_KEY).results
        },
        saveFetchResult = {
            db.withTransaction {
                localDao.deleteTvTopRated()
                localDao.insertTvTopRated(it)
            }
        }
    )

    fun getTvSeriesDetails(seriesId: Int) = simpleNetworkBoundResource(
        fetch = {
            movieApi.getTvSeriesDetails(seriesId, BuildConfig.TMDB_API_KEY, "images,videos,credits,similar,recommendations")
        }
    )
























    fun getNewsByRelevancy(pageSize: Int) = networkBoundResource(
        query = {
            localDao.getNewsByRelevancy()
        },
        fetch = {
            newsApi.getNewsByRelevancy(
                BuildConfig.NEWS_API_KEY,
                "movie",
                "relevancy",
                "en",
                pageSize
            ).articles
        },
        saveFetchResult = {
            db.withTransaction {
                localDao.deleteNewsByRelevancy()
                localDao.insertNewsByRelevancy(it)
            }
        }
    )




    fun createRequestToken() = simpleNetworkBoundResource(
        fetch = {
            movieApi.createRequestToken(BuildConfig.TMDB_API_KEY)
//            val authenticationCallback = response.headers()["authentication-callback"]
        }
    )









}