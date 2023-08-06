package com.example.movieapp.views.home.movies

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.movieapp.models.news.Articles
import com.example.movieapp.models.movies.MovieNowPlaying
import com.example.movieapp.models.movies.MoviePopular
import com.example.movieapp.models.movies.MovieTopRated
import com.example.movieapp.models.movies.MovieUpcoming
import com.example.movieapp.test.MovieListActivity
import com.example.movieapp.viewmodels.MovieViewModel
import com.example.movieapp.views.detail.CustomBottomSheetDialogFragment
import com.example.movieapp.views.home.movies.adapters.NowPlayingPreviewAdapter
import com.example.movieapp.views.home.movies.adapters.PopularMovieAdapter
import com.example.movieapp.views.home.movies.adapters.TopRatedMovieAdapter
import com.example.movieapp.views.home.movies.adapters.UpcomingMoviePreviewAdapter
import com.example.movieapp.views.home.news.NewsActivity
import com.example.movieapp.views.home.news.NewsAdapter
import com.example.movieapp.views.home.news.NewsBottomSheet
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesFragment : Fragment(),
    NowPlayingPreviewAdapter.OnItemClickListener,
    UpcomingMoviePreviewAdapter.OnItemClickListener,
    PopularMovieAdapter.OnItemClickListener,
    TopRatedMovieAdapter.OnItemClickListener,
    NewsAdapter.OnItemClickListener {


    private lateinit var binding: FragmentMoviesBinding
    private val viewModel: MovieViewModel by viewModels()
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<View>

    private var scrollPosition = 0
    private val SCROLL_POSITION_KEY = "SCROLL_POSITION_KEY"

    fun setScrollPosition(position: Int) {
        scrollPosition = position
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SCROLL_POSITION_KEY, scrollPosition)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.let {
            scrollPosition = it.getInt(SCROLL_POSITION_KEY, 0)
        }
    }

    // Now, you can use the 'scrollPosition' variable to restore the scroll position
    // of any scrollable view inside your Fragment when it is re-created.

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        binding = FragmentMoviesBinding.inflate(inflater, container, false)




        // Adapters
        val nowPlayingAdapter = NowPlayingPreviewAdapter(this)
        val upcomingPreviewAdapter = UpcomingMoviePreviewAdapter(this)
        val popularMovieAdapter = PopularMovieAdapter(this)
        val topRatedMovieAdapter = TopRatedMovieAdapter(this)
        val newsAdapter = NewsAdapter(this, "detail")

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

            showNowPlayingImageView.setOnClickListener {
                val intent = Intent(context, MovieListActivity::class.java)
                intent.putExtra("TYPE", "movie_now_playing")
                intent.putExtra("TITLE", "Now Playing Movies")
                val options = ActivityOptions.makeCustomAnimation(
                    context,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                startActivity(intent, options.toBundle())
            }

            showUpcomingImageView.setOnClickListener {
                val intent = Intent(context, MovieListActivity::class.java)
                intent.putExtra("TYPE", "movie_upcoming")
                intent.putExtra("TITLE", "Upcoming Movies")
                val options = ActivityOptions.makeCustomAnimation(
                    context,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                startActivity(intent, options.toBundle())
            }

            showNewsImageView.setOnClickListener {
                val intent = Intent(context, NewsActivity::class.java)
                val options = ActivityOptions.makeCustomAnimation(
                    context,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                startActivity(intent, options.toBundle())
            }

            showPopularImageView.setOnClickListener {
                val intent = Intent(context, MovieListActivity::class.java)
                intent.putExtra("TYPE", "movie_popular")
                intent.putExtra("TITLE", "Popular Movies")
                val options = ActivityOptions.makeCustomAnimation(
                    context,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                startActivity(intent, options.toBundle())
            }

            showTopRatedImageView.setOnClickListener {
                val intent = Intent(context, MovieListActivity::class.java)
                intent.putExtra("TYPE", "movie_top_rated")
                intent.putExtra("TITLE", "Top Rated Movies")
                val options = ActivityOptions.makeCustomAnimation(
                    context,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                startActivity(intent, options.toBundle())
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

        viewModel.fetchNewsByRelevancy(100).observe(viewLifecycleOwner) { result ->
            newsAdapter.submitList(result.data)
        }

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onNowPlayingClick(movie: MovieNowPlaying) {
        val bottomSheetFragment = movie.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "movie") }
        bottomSheetFragment?.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    override fun onUpcomingClick(movie: MovieUpcoming) {
        val bottomSheetFragment = movie.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "movie") }
        bottomSheetFragment?.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    override fun onPopularClick(movie: MoviePopular) {
        val bottomSheetFragment = movie.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "movie") }
        bottomSheetFragment?.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    override fun onTopRatedClick(movie: MovieTopRated) {
        val bottomSheetFragment = movie.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "movie") }
        bottomSheetFragment?.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    override fun onNewsClick(news: Articles) {
        val bottomSheetFragment = news.url?.let { NewsBottomSheet.newInstance(it) }
        bottomSheetFragment?.show(parentFragmentManager, bottomSheetFragment.tag)
    }

}