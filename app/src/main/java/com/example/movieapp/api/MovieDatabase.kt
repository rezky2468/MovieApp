package com.example.movieapp.api

import androidx.room.Database
import androidx.room.RoomDatabase
//import androidx.room.RoomDatabase
import com.example.movieapp.models.Movie
import com.example.movieapp.models.UpcomingMovie

@Database(entities = [Movie::class, UpcomingMovie::class], version = 1)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}