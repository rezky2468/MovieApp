package com.example.movieapp.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.models.MultiSearch
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

@Database(
    entities = [
        MovieNowPlaying::class,
        MovieUpcoming::class,
        MoviePopular::class,
        MovieTopRated::class,
        MovieSearch::class,
        MultiSearch::class,
        TvAiringToday::class,
        TvOnTheAir::class,
        TvPopular::class,
        TvTopRated::class,
        Articles::class],
    version = 1
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun localDao(): LocalDao
}