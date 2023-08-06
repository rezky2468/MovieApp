package com.example.movieapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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
    private val repository: MovieRepository
) : ViewModel() {

    val movieNowPlaying = repository.getMovieNowPlaying().asLiveData()
    val movieUpcoming = repository.getMovieUpcoming().asLiveData()
    val moviePopular = repository.getMoviePopular().asLiveData()
    val movieTopRated = repository.getMovieTopRated().asLiveData()



    fun fetchMovieSearch(query: String) = repository.getMovieSearch(query).asLiveData()
    fun fetchMultiSearch(query: String) = repository.getMultiSearch(query).asLiveData()

    //======================================== TV SERIES ========================================//

    val tvAiringToday = repository.getTvAiringToday().asLiveData()
    val tvOnTheAir = repository.getTvOnTheAir().asLiveData()
    val tvPopular = repository.getTvPopular().asLiveData()
    val tvTopRated = repository.getTvTopRated().asLiveData()

    fun fetchNewsByRelevancy(pageSize: Int) =  repository.getNewsByRelevancy(pageSize).asLiveData()

    // ============================================================================================

    fun fetchMovieDetails(movieId: Int) = repository.getMovieDetails(movieId).asLiveData()
    fun fetchTvSeriesDetails(seriesId: Int) = repository.getTvSeriesDetails(seriesId).asLiveData()

    val requestToken = repository.createRequestToken().asLiveData() // is this correct?
    val callback = repository.createRequestToken()


}