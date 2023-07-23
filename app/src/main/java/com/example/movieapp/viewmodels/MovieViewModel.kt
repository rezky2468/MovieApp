package com.example.movieapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.movieapp.models.movies.MovieDetailResponse
import com.example.movieapp.repositories.MovieRepository
import com.example.movieapp.utils.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
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

//    val movieDetail = repository.getMovieDetail().asLiveData()

    // Expose a method to fetch movie details with a dynamic movie ID
    private val _movieDetail = MutableLiveData<Resource<MovieDetailResponse>>()
    val movieDetail: LiveData<Resource<MovieDetailResponse>> get() = _movieDetail

    fun fetchMovieDetail(movieId: Int) {
        _movieDetail.value = Resource.Loading(null)
        viewModelScope.launch {
            repository.getMovieDetail(movieId).asLiveData().observeForever {
                _movieDetail.value = it
            }
        }
    }

    fun fetchMovieImages(movieId: Int) = repository.getMovieImages(movieId).asLiveData()
    fun fetchMovieCasts(movieId: Int) = repository.getMovieCasts(movieId).asLiveData()
    fun fetchMovieVideos(movieId: Int) = repository.getMovieVideos(movieId).asLiveData()
    fun fetchMovieSearch(query: String) = repository.getMovieSearch(query).asLiveData()

    //======================================== TV SERIES ========================================//

    val tvAiringToday = repository.getTvAiringToday().asLiveData()
    val tvOnTheAir = repository.getTvOnTheAir().asLiveData()
    val tvPopular = repository.getTvPopular().asLiveData()
    val tvTopRated = repository.getTvTopRated().asLiveData()

    fun fetchTvBackdrops(seriesId: Int) = repository.getTvBackdrops(seriesId).asLiveData()
    fun fetchTvCasts(seriesId: Int) = repository.getTvCasts(seriesId).asLiveData()
    fun fetchTvVideos(seriesId: Int) = repository.getTvVideos(seriesId).asLiveData()



    fun fetchNewsByRelevancy(pageSize: Int) =  repository.getNewsByRelevancy(pageSize).asLiveData()

}