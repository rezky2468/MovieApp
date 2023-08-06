package com.example.movieapp.api

import com.example.movieapp.models.movies.MovieDetailsResponse
import com.example.movieapp.models.MultiSearchResponse
import com.example.movieapp.models.movies.MovieSearchResponse
import com.example.movieapp.models.movies.MovieNowPlayingResponse
import com.example.movieapp.models.news.NewsResponse
import com.example.movieapp.models.movies.MoviePopularResponse
import com.example.movieapp.models.movies.MovieTopRatedResponse
import com.example.movieapp.models.movies.MovieUpcomingResponse
import com.example.movieapp.models.network.RequestTokenResponse
import com.example.movieapp.models.tv.TvAiringTodayResponse
import com.example.movieapp.models.tv.TvOnTheAirResponse
import com.example.movieapp.models.tv.TvPopularResponse
import com.example.movieapp.models.tv.TvSeriesDetailsResponse
import com.example.movieapp.models.tv.TvTopRatedResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("movie/now_playing")
    suspend fun getMovieNowPlaying(@Query("api_key") apiKey: String?): MovieNowPlayingResponse
    @GET("movie/upcoming")
    suspend fun getMovieUpcoming(@Query("api_key") apiKey: String?): MovieUpcomingResponse
    @GET("movie/popular")
    suspend fun getMoviePopular(@Query("api_key") apiKey: String?): MoviePopularResponse
    @GET("movie/top_rated")
    suspend fun getMovieTopRated(@Query("api_key") apiKey: String?): MovieTopRatedResponse

//    @GET("movie/{movie_id}")
//    suspend fun getMovieDetail(
//        @Path("movie_id") id: Int?,
//        @Query("api_key") apiKey: String?,
//    ): MovieDetailResponse

//    @GET("movie/{movie_id}/images")
//    suspend fun getMovieImages(
//        @Path("movie_id") id: Int?,
//        @Query("api_key") apiKey: String?,
//    ): MovieImageResponse
//
//    @GET("movie/{movie_id}/credits")
//    suspend fun getMovieCredits(
//        @Path("movie_id") id: Int?,
//        @Query("api_key") apiKey: String?,
//    ): CreditsResponse
//
//    @GET("movie/{movie_id}/videos")
//    suspend fun getMovieVideos(
//        @Path("movie_id") id: Int?,
//        @Query("api_key") apiKey: String?,
//    ): VideosResponse

    @GET("search/movie")
    suspend fun getMovieSearch(
        @Query("api_key") apiKey: String?,
        @Query("query") query: String?,
    ): MovieSearchResponse

    @GET("search/multi")
    suspend fun getMultiSearch(
        @Query("api_key") apiKey: String?,
        @Query("query") query: String?,
        @Query("include_adult") adult: Boolean
    ): MultiSearchResponse

//    AIzaSyDGHiuM-wI97inmp9JubTd5x6Nhu3ixS-M

    @GET("everything")
    suspend fun getNewsByRelevancy(
        @Query("apiKey") apiKey: String?,
        @Query("q") q: String?,
        @Query("sortBy") sortBy: String?,
        @Query("language") language: String?,
        @Query("pageSize") pageSize: Int?,
    ): NewsResponse

//    https://newsapi.org/v2/everything/?apiKey=d37a640ddb254672bc866778500f91b0&q=movie&sortBy=relevancy&pageSize=10&language=en



    @GET("tv/airing_today")
    suspend fun getTvAiringToday(@Query("api_key") apiKey: String?): TvAiringTodayResponse
    @GET("tv/on_the_air")
    suspend fun getTvOnTheAir(@Query("api_key") apiKey: String?): TvOnTheAirResponse
    @GET("tv/popular")
    suspend fun getTvPopular(@Query("api_key") apiKey: String?): TvPopularResponse
    @GET("tv/top_rated")
    suspend fun getTvTopRated(@Query("api_key") apiKey: String?): TvTopRatedResponse

    // 203737
//    @GET("tv/{series_id}/images")
//    suspend fun getTvImages(
//        @Path("series_id") id: Int?,
//        @Query("api_key") apiKey: String?,
//    ): TvImagesResponse

//    @GET("tv/{series_id}/videos")
//    suspend fun getTvVideos(
//        @Path("series_id") id: Int?,
//        @Query("api_key") apiKey: String?,
//    ): VideosResponse
//
//    @GET("tv/{series_id}/credits")
//    suspend fun getTvCasts(
//        @Path("series_id") id: Int?,
//        @Query("api_key") apiKey: String?,
//    ): CreditsResponse

    // ==============================================================

    @GET("movie/{movie_id}")
    suspend fun getMovieDetails(
        @Path("movie_id") id: Int?,
        @Query("api_key") apiKey: String?,
        @Query("append_to_response") appendToResponse: String?,
//        @Query("language") language: String?
    ) : MovieDetailsResponse

    @GET("tv/{series_id}")
    suspend fun getTvSeriesDetails(
        @Path("series_id") id: Int?,
        @Query("api_key") apiKey: String?,
        @Query("append_to_response") appendToResponse: String?,
//        @Query("language") language: String?
    ) : TvSeriesDetailsResponse

    @GET("authentication/guest_session/new")
    suspend fun createGuestSession()

    @GET("authentication/token/new")
    suspend fun createRequestToken(
        @Query("api_key") apiKey: String?,
    ) : Response<RequestTokenResponse>

    @POST("authentication/session/new")
    suspend fun createSession()

    @POST("authentication/token/validate_with_login")
    suspend fun createSessionWithLogin()

}