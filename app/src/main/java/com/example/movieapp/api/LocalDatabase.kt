package com.example.movieapp.api

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.movieapp.models.news.Articles
import com.example.movieapp.models.movies.BackdropsItem
import com.example.movieapp.models.movies.MovieCast
import com.example.movieapp.models.movies.MovieDetailResponse
import com.example.movieapp.models.movies.MovieSearch
import com.example.movieapp.models.movies.MovieNowPlaying
import com.example.movieapp.models.movies.MoviePopular
import com.example.movieapp.models.movies.MovieTopRated
import com.example.movieapp.models.movies.MovieUpcoming
import com.example.movieapp.models.movies.Video
import com.example.movieapp.models.tv.TvAiringToday
import com.example.movieapp.models.tv.TvBackdrops
import com.example.movieapp.models.tv.TvCast
import com.example.movieapp.models.tv.TvOnTheAir
import com.example.movieapp.models.tv.TvPopular
import com.example.movieapp.models.tv.TvTopRated
import com.example.movieapp.models.tv.TvVideo

@Database(
    entities = [
        MovieNowPlaying::class,
        MovieUpcoming::class,
        MoviePopular::class,
        MovieTopRated::class,
        MovieDetailResponse::class,
        BackdropsItem::class,
        MovieCast::class,
        Video::class,
        MovieSearch::class,
        TvAiringToday::class,
        TvOnTheAir::class,
        TvPopular::class,
        TvTopRated::class,
        TvBackdrops::class,
        TvVideo::class,
        TvCast::class,
        Articles::class],
    version = 12
)
abstract class LocalDatabase : RoomDatabase() {
    abstract fun localDao(): LocalDao
}