package com.example.movieapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import com.example.movieapp.repositories.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

//class MovieViewModel : ViewModel() {
//
//    private val repository: MovieRepository = MovieRepository()
//    private var page = 1
//
//    fun getPopularMovies(): LiveData<RequestState<MovieResponse>> = liveData {
//        emit(RequestState.Loading)
//        try {
//            val response = repository.getPopularMovies(page)
//            emit(RequestState.Success(response))
//        } catch (e: HttpException) {
//            e.response()?.errorBody()?.string()?.let { RequestState.Error(it) }?.let { emit(it) }
//        }
//    }
//
//}

@HiltViewModel
class MovieViewModel @Inject constructor(
    repository: MovieRepository
) : ViewModel() {
    val nowPlayingMovies = repository.getNowPlayingMovies().asLiveData()
    val upcomingMovies = repository.getUpcomingMovies().asLiveData()
}