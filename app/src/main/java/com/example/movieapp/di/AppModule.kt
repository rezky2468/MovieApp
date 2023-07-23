package com.example.movieapp.di

import dagger.Module
import dagger.hilt.InstallIn
import android.app.Application
import androidx.room.Room
import com.example.movieapp.BuildConfig
import com.example.movieapp.api.ApiService
import com.example.movieapp.api.LocalDatabase
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideHttpLoggerInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(loggingInterceptor: HttpLoggingInterceptor): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    @Named("movieApi")
    fun provideRetrofitMovie(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.TMDB_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("movieApi")
    fun provideMovieApi(@Named("movieApi") retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    @Named("newsApi")
    fun provideRetrofitNews(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.NEWS_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(httpClient)
            .build()
    }

    @Provides
    @Singleton
    @Named("newsApi")
    fun provideNewsApi(@Named("newsApi") retrofit: Retrofit): ApiService =
        retrofit.create(ApiService::class.java)

    @Provides
    @Singleton
    fun provideDatabase(app: Application): LocalDatabase =
        Room.databaseBuilder(app, LocalDatabase::class.java, "now_playing_database")
            .fallbackToDestructiveMigration()
            .build()

}