package com.example.movieapp.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.models.MultiSearch
import com.example.movieapp.models.movies.MovieDetailsEntity
import com.example.movieapp.models.news.Articles
import com.example.movieapp.models.movies.MovieSearch
import com.example.movieapp.models.movies.MovieNowPlaying
import com.example.movieapp.models.movies.MoviePopular
import com.example.movieapp.models.movies.MovieTopRated
import com.example.movieapp.models.movies.MovieUpcoming
import com.example.movieapp.models.tv.TvAiringToday
import com.example.movieapp.models.tv.TvOnTheAir
import com.example.movieapp.models.tv.TvPopular
import com.example.movieapp.models.tv.TvTopRated
import kotlinx.coroutines.flow.Flow

@Dao
interface LocalDao {

    // ======================================== MOVIE ======================================== //
    @Query("SELECT * FROM movie_now_playing")
    fun getMovieNowPlaying(): Flow<List<MovieNowPlaying>>
    @Query("SELECT * FROM movie_upcoming")
    fun getMovieUpcoming(): Flow<List<MovieUpcoming>>
    @Query("SELECT * FROM movie_popular")
    fun getMoviePopular(): Flow<List<MoviePopular>>
    @Query("SELECT * FROM movie_top_rated")
    fun getMovieTopRated(): Flow<List<MovieTopRated>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieNowPlaying(movies: List<MovieNowPlaying>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieUpcoming(movies: List<MovieUpcoming>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMoviePopular(movies: List<MoviePopular>)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieTopRated(movies: List<MovieTopRated>)

    @Query("DELETE FROM movie_now_playing")
    suspend fun deleteMovieNowPlaying()
    @Query("DELETE FROM movie_upcoming")
    suspend fun deleteMovieUpcoming()
    @Query("DELETE FROM movie_popular")
    suspend fun deleteMoviePopular()
    @Query("DELETE FROM movie_top_rated")
    suspend fun deleteMovieTopRated()


//    // ==================== Movie Detail ==================== //

//    @Query("SELECT * FROM movie_details WHERE id = :movieId")
//    suspend fun getMovieDetails(movieId: Int): MovieDetailsEntity?
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertMovieDetails(movie: MovieDetailsEntity)
//    @Query("DELETE FROM movie_details")
//    suspend fun deleteMovieDetails()



//    @Query("SELECT * FROM movie_detail")
//    fun getMovieDetail(): Flow<MovieDetailResponse>
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertMovieDetail(movies: MovieDetailResponse)
//    @Query("DELETE FROM movie_detail")
//    suspend fun deleteMovieDetail()
//
//    // ==================== Movie Images ==================== //
//    @Query("SELECT * FROM backdrops")
//    fun getMovieImages(): Flow<List<BackdropsItem>?>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertMovieImages(movies: List<BackdropsItem>?)
//
//    @Query("DELETE FROM backdrops")
//    suspend fun deleteMovieImages()
//
//    // ==================== Movie Credits ==================== //
//    @Query("SELECT * FROM casts")
//    fun getMovieCredits(): Flow<List<MovieCast>?>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertMovieCredits(movies: List<MovieCast>?)
//
//    @Query("DELETE FROM backdrops")
//    suspend fun deleteMovieCredits()
//
//    // ==================== Movie Videos ==================== //
//    @Query("SELECT * FROM videos")
//    fun getMovieVideos(): Flow<List<Video>?>
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertMovieVideos(movies: List<Video>?)
//
//    @Query("DELETE FROM videos")
//    suspend fun deleteMovieVideos()

    // ==================== Movie Search ==================== //
    @Query("SELECT * FROM movie_search")
    fun getMovieSearch(): Flow<List<MovieSearch>?>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMovieSearch(movies: List<MovieSearch>?)
    @Query("DELETE FROM movie_search")
    suspend fun deleteMovieSearch()




    @Query("SELECT * FROM multi_search")
    fun getMultiSearch(): Flow<List<MultiSearch>?>
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMultiSearch(movies: List<MultiSearch>?)
    @Query("DELETE FROM multi_search")
    suspend fun deleteMultiSearch()


//    https://www.googleapis.com/youtube/v3/videos?id=t0Q2otsqC4I&key=AIzaSyDGHiuM-wI97inmp9JubTd5x6Nhu3ixS-M&part=snippet,contentDetails,fileDetails,player,processingDetails,recordingDetails,statistics,status,suggestions,topicDetails
//    https://www.googleapis.com/youtube/v3/videos?id=t0Q2otsqC4I&key=AIzaSyDGHiuM-wI97inmp9JubTd5x6Nhu3ixS-M&part=snippet,contentDetails,player,recordingDetails,statistics,status,topicDetails
//    https://www.googleapis.com/youtube/v3/videos?id=t0Q2otsqC4I&key=AIzaSyDGHiuM-wI97inmp9JubTd5x6Nhu3ixS-M&part=snippet,contentDetails,statistics,status

    //======================================== TV SERIES ========================================//
    @Query("SELECT * FROM tv_airing_today")
    fun getTvAiringToday(): Flow<List<TvAiringToday>?>
    @Query("SELECT * FROM tv_on_the_air")
    fun getTvOnTheAir(): Flow<List<TvOnTheAir>?>
    @Query("SELECT * FROM tv_popular")
    fun getTvPopular(): Flow<List<TvPopular>?>
    @Query("SELECT * FROM tv_top_rated")
    fun getTvTopRated(): Flow<List<TvTopRated>?>
//    @Query("SELECT * FROM tv_backdrops")
//    fun getTvBackdrops(): Flow<List<TvBackdrops>?>
//    @Query("SELECT * FROM tv_videos")
//    fun getTvVideos(): Flow<List<TvVideo>?>
//    @Query("SELECT * FROM tv_casts")
//    fun getTvCasts(): Flow<List<TvCast>?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvAiringToday(tv: List<TvAiringToday>?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvOnTheAir(tv: List<TvOnTheAir>?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvPopular(tv: List<TvPopular>?)
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTvTopRated(tv: List<TvTopRated>?)
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertTvBackdrops(tv: List<TvBackdrops>?)
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertTvVideos(tv: List<TvVideo>?)
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertTvCasts(tv: List<TvCast>?)

    @Query("DELETE FROM tv_airing_today")
    suspend fun deleteTvAiringToday()
    @Query("DELETE FROM tv_on_the_air")
    suspend fun deleteTvOnTheAir()
    @Query("DELETE FROM tv_popular")
    suspend fun deleteTvPopular()
    @Query("DELETE FROM tv_top_rated")
    suspend fun deleteTvTopRated()
//    @Query("DELETE FROM tv_backdrops")
//    suspend fun deleteTvBackdrops()
//    @Query("DELETE FROM tv_videos")
//    suspend fun deleteTvVideos()
//    @Query("DELETE FROM tv_casts")
//    suspend fun deleteTvCasts()

































    // ==================== News ==================== //
    @Query("SELECT * FROM news_relevancy")
    fun getNewsByRelevancy(): Flow<List<Articles>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNewsByRelevancy(movies: List<Articles>)

    @Query("DELETE FROM news_relevancy")
    suspend fun deleteNewsByRelevancy()










}