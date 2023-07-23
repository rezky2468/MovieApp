package com.example.movieapp.views.home.movies

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.movieapp.models.news.Articles
import com.example.movieapp.models.movies.MovieNowPlaying
import com.example.movieapp.models.movies.MoviePopular
import com.example.movieapp.models.movies.MovieTopRated
import com.example.movieapp.models.movies.MovieUpcoming
import com.example.movieapp.viewmodels.MovieViewModel
import com.example.movieapp.views.detail.MovieDetailActivity
import com.example.movieapp.views.home.movies.adapters.NowPlayingPreviewAdapter
import com.example.movieapp.views.home.movies.adapters.PopularMovieAdapter
import com.example.movieapp.views.home.movies.adapters.TopRatedMovieAdapter
import com.example.movieapp.views.home.movies.adapters.UpcomingMoviePreviewAdapter
import com.example.movieapp.views.home.news.NewsActivity
import com.example.movieapp.views.home.news.NewsAdapter
import com.example.movieapp.views.home.news.NewsDetailActivity
import com.example.movieapp.views.nowplaying.NowPlayingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(),
    NowPlayingPreviewAdapter.OnItemClickListener,
    UpcomingMoviePreviewAdapter.OnItemClickListener,
    PopularMovieAdapter.OnItemClickListener,
    TopRatedMovieAdapter.OnItemClickListener,
    NewsAdapter.OnItemClickListener {


    private lateinit var binding: FragmentMoviesBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inside onCreateView
        val viewModel: MovieViewModel = ViewModelProvider(this).get(MovieViewModel::class.java)

        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(inflater, container, false)

        // Adapters
        val nowPlayingAdapter = NowPlayingPreviewAdapter(this)
        val upcomingPreviewAdapter = UpcomingMoviePreviewAdapter(this)
        val popularMovieAdapter = PopularMovieAdapter(this)
        val topRatedMovieAdapter = TopRatedMovieAdapter(this)
        val newsAdapter = NewsAdapter(this)

        binding.apply {

            recyclerView.adapter = nowPlayingAdapter
            recyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            upcomingRecyclerView.adapter = upcomingPreviewAdapter
            upcomingRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            popularRecyclerView.adapter = popularMovieAdapter
            popularRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            topRatedRecyclerView.adapter = topRatedMovieAdapter
            topRatedRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

            newsRecyclerView.adapter = newsAdapter
            newsRecyclerView.layoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

//            val snapHelper = LinearSnapHelper()
//            snapHelper.attachToRecyclerView(recyclerView)

//            val snapOnScrollListener = SnapOnScrollListener(snapHelper, SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE)
//            recyclerView.addOnScrollListener(snapOnScrollListener)

            showNowPlayingImageView.setOnClickListener {
                val intent = Intent(context, NowPlayingActivity::class.java)
                startActivity(intent)
            }

            showUpcomingImageView.setOnClickListener {
                val intent = Intent(context, MovieDetailActivity::class.java)
                startActivity(intent)
            }

            showNewsImageView.setOnClickListener {
                val intent = Intent(context, NewsActivity::class.java)
                startActivity(intent)
            }






        }

        viewModel.movieNowPlaying.observe(viewLifecycleOwner) { result ->
            nowPlayingAdapter.submitList(result.data)
        }

        viewModel.movieUpcoming.observe(viewLifecycleOwner) { result ->
            upcomingPreviewAdapter.submitList(result.data)
        }

        viewModel.moviePopular.observe(viewLifecycleOwner) { result ->
            popularMovieAdapter.submitList(result.data)
        }

        viewModel.movieTopRated.observe(viewLifecycleOwner) { result ->
            topRatedMovieAdapter.submitList(result.data)
        }

        viewModel.fetchNewsByRelevancy(5).observe(viewLifecycleOwner) { result ->
            newsAdapter.submitList(result.data)
        }

        return binding.root



    }

    override fun onNowPlayingClick(movie: MovieNowPlaying) {
        Toast.makeText(context, "Clicked ${movie.title}", Toast.LENGTH_LONG).show()
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("IDENTIFIER", "movie")
        intent.putExtra("MOVIE_TYPE", "movie_now_playing")
        intent.putExtra("MOVIE_NOW_PLAYING", movie)
        intent.putExtra("MOVIE_ID", movie.id)
        startActivity(intent)
    }

    override fun onUpcomingClick(movie: MovieUpcoming) {
        Toast.makeText(context, "Clicked ${movie.title}", Toast.LENGTH_LONG).show()
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("IDENTIFIER", "movie")
        intent.putExtra("MOVIE_TYPE", "movie_upcoming")
        intent.putExtra("MOVIE_UPCOMING", movie)
        intent.putExtra("MOVIE_ID", movie.id)
        startActivity(intent)
    }

    override fun onPopularClick(movie: MoviePopular) {
        Toast.makeText(context, "Clicked ${movie.title}", Toast.LENGTH_LONG).show()
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("IDENTIFIER", "movie")
        intent.putExtra("MOVIE_TYPE", "movie_popular")
        intent.putExtra("MOVIE_POPULAR", movie)
        intent.putExtra("MOVIE_ID", movie.id)
        startActivity(intent)
    }

    override fun onTopRatedClick(movie: MovieTopRated) {
        Toast.makeText(context, "Clicked ${movie.title}", Toast.LENGTH_LONG).show()
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("IDENTIFIER", "movie")
        intent.putExtra("MOVIE_TYPE", "movie_top_rated")
        intent.putExtra("MOVIE_TOP_RATED", movie)
        intent.putExtra("MOVIE_ID", movie.id)
        startActivity(intent)
    }

    override fun onNewsClick(news: Articles) {
        Toast.makeText(context, "Clicked ${news.title}", Toast.LENGTH_LONG).show()
        val intent = Intent(context, NewsDetailActivity::class.java)
        intent.putExtra("DATA_NEWS", news)
        startActivity(intent)
    }

}