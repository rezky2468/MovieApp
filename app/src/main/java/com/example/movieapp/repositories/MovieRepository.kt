package com.example.movieapp.repositories

import com.example.movieapp.BuildConfig
import androidx.room.withTransaction
import com.example.movieapp.api.ApiService
import com.example.movieapp.api.LocalDatabase
import com.example.movieapp.utils.networkBoundResource
import com.example.movieapp.utils.simpleNetworkBoundResource

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
//            delay(2000)
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

    fun getMovieDetail(movieId: Int) = networkBoundResource(
        query = {
            localDao.getMovieDetail()
        },
        fetch = {
            movieApi.getMovieDetail(movieId, BuildConfig.TMDB_API_KEY)
        },
        saveFetchResult = {
            db.withTransaction {
                localDao.deleteMovieDetail()
                localDao.insertMovieDetail(it)
            }
        }
    )
//    fun getMovieImages(movieId: Int) = networkBoundResource(
//        query = {
//            localDao.getMovieImages()
//        },
//        fetch = {
//            movieApi.getMovieImages(movieId, BuildConfig.TMDB_API_KEY).backdrops?.take(6)
//        },
//        saveFetchResult = {
//            db.withTransaction {
//                localDao.deleteMovieImages()
//                localDao.insertMovieImages(it)
//            }
//        }
//    )

    fun getMovieImages(movieId: Int) = simpleNetworkBoundResource(
        fetch = {
            movieApi.getMovieImages(movieId, BuildConfig.TMDB_API_KEY).backdrops?.take(6)
        }
    )
    fun getMovieCasts(movieId: Int) = simpleNetworkBoundResource(
        fetch = {
            movieApi.getMovieCredits(movieId, BuildConfig.TMDB_API_KEY).cast
        }
    )
    fun getMovieVideos(movieId: Int) = simpleNetworkBoundResource(
        fetch = {
            movieApi.getMovieVideos(movieId, BuildConfig.TMDB_API_KEY).results?.take(6)
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

    fun getTvBackdrops(seriesId: Int) = simpleNetworkBoundResource(
        fetch = {
            movieApi.getTvImages(seriesId, BuildConfig.TMDB_API_KEY).backdrops
        }
    )
    fun getTvVideos(seriesId: Int) = simpleNetworkBoundResource(
        fetch = {
            movieApi.getTvVideos(seriesId, BuildConfig.TMDB_API_KEY).results
        }
    )
    fun getTvCasts(seriesId: Int) = simpleNetworkBoundResource(
        fetch = {
            movieApi.getTvCasts(seriesId, BuildConfig.TMDB_API_KEY).cast
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


}