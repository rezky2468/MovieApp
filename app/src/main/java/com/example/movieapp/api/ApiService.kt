package com.example.movieapp.api

import com.example.movieapp.models.MovieResponse
import com.example.movieapp.models.UpcomingMovie
import com.example.movieapp.models.UpcomingMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

//    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhODQ5OWMyNGRjYjRiYjRjMTdhZmYyNDY3OWYyNWQ0ZCIsInN1YiI6IjYxNWZjNTEyYWJkZWMwMDA2MzgzMmNjNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.XarBQLqT2JDbttQ2EbPAsVNTfGaK6PWfxOtfITdnXhw")
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String?,
        @Query("page") page: Int?
    ): MovieResponse

//    companion object {
//        const val BASE_URL = "https://api.themoviedb.org/3/"
//        const val BASE_URL_2 = "https://random-data-api.com/api/"
//        const val API_KEY = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhODQ5OWMyNGRjYjRiYjRjMTdhZmYyNDY3OWYyNWQ0ZCIsInN1YiI6IjYxNWZjNTEyYWJkZWMwMDA2MzgzMmNjNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.XarBQLqT2JDbttQ2EbPAsVNTfGaK6PWfxOtfITdnXhw"
//    }

//    @Headers(
//        "accept: application/json",
//        "Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhODQ5OWMyNGRjYjRiYjRjMTdhZmYyNDY3OWYyNWQ0ZCIsInN1YiI6IjYxNWZjNTEyYWJkZWMwMDA2MzgzMmNjNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.XarBQLqT2JDbttQ2EbPAsVNTfGaK6PWfxOtfITdnXhw"
//    )
    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(@Query("api_key") key: String?): MovieResponse

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(@Query("api_key") key: String?): UpcomingMovieResponse
//
//    @GET("coffee/random_coffee?size=10")
//    suspend fun getCoffee(): List<Coffee>
//
//    @Headers("Authorization: Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJhODQ5OWMyNGRjYjRiYjRjMTdhZmYyNDY3OWYyNWQ0ZCIsInN1YiI6IjYxNWZjNTEyYWJkZWMwMDA2MzgzMmNjNiIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.XarBQLqT2JDbttQ2EbPAsVNTfGaK6PWfxOtfITdnXhw")
//    @GET("movie/popular")
//    suspend fun getPopularMovie(
////        @Query("api_key") key: String?,
//        @Query("page") page: Int?
//    ): Response<MovieResponse>

//    https://newsapi.org/v2/everything/?apiKey=d37a640ddb254672bc866778500f91b0&q=movie&sortBy=relevancy&pageSize=10&language=en

}