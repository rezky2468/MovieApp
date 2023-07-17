package com.example.movieapp.api

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.movieapp.models.Movie
import com.example.movieapp.models.UpcomingMovie
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {

    @Query("SELECT * FROM movies")
    fun getNowPlayingMovies(): Flow<List<Movie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNowPlayingMovies(movies: List<Movie>)

    @Query("DELETE FROM movies")
    suspend fun deleteNowPlayingMovies()

    @Query("SELECT * FROM upcoming_movies")
    fun getUpcomingMovies(): Flow<List<UpcomingMovie>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUpcomingMovies(movies: List<UpcomingMovie>)

    @Query("DELETE FROM upcoming_movies")
    suspend fun deleteUpcomingMovies()

}