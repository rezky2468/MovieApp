package com.example.movieapp.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.MovieListAdapter
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMovieListBinding
import com.example.movieapp.models.movies.Movie
import com.example.movieapp.models.movies.MovieDetailsResponse
import com.example.movieapp.models.movies.MovieNowPlaying
import com.example.movieapp.models.movies.MoviePopular
import com.example.movieapp.models.movies.MovieTopRated
import com.example.movieapp.models.movies.MovieUpcoming
import com.example.movieapp.models.tv.TvAiringToday
import com.example.movieapp.models.tv.TvOnTheAir
import com.example.movieapp.models.tv.TvPopular
import com.example.movieapp.models.tv.TvSeries
import com.example.movieapp.models.tv.TvSeriesDetailsResponse
import com.example.movieapp.models.tv.TvTopRated
import com.example.movieapp.viewmodels.MovieViewModel
import com.example.movieapp.views.detail.CustomBottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieListActivity : AppCompatActivity(), MovieListAdapter.OnItemClickListener<Any> {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val type = intent.getStringExtra("TYPE")
        val title = intent.getStringExtra("TITLE")
        val adapter = MovieListAdapter(this@MovieListActivity)
//        val tvSeriesAdapter = TvSeriesListAdapter(this@MovieListActivity)

        binding.apply {

            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(this@MovieListActivity, 2)
            backImageView.setOnClickListener {
                super.onBackPressed()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }

            when (type) {
                "movie_now_playing" -> {
                    titleLabel.text = title
                    viewModel.movieNowPlaying.observe(this@MovieListActivity) {
                        adapter.submitList(it.data)
                    }
                }

                "movie_upcoming" -> {
                    titleLabel.text = title
                    viewModel.movieUpcoming.observe(this@MovieListActivity) {
                        adapter.submitList(it.data)
                    }
                }

                "movie_popular" -> {
                    titleLabel.text = title
                    viewModel.moviePopular.observe(this@MovieListActivity) {
                        adapter.submitList(it.data)
                    }
                }

                "movie_top_rated" -> {
                    titleLabel.text = title
                    viewModel.movieTopRated.observe(this@MovieListActivity) {
                        adapter.submitList(it.data)
                    }
                }

                "tv_airing_today" -> {
                    titleLabel.text = title
                    viewModel.tvAiringToday.observe(this@MovieListActivity) {
                        adapter.submitList(it.data)
                    }
                }

                "tv_on_the_air" -> {
                    titleLabel.text = title
                    viewModel.tvOnTheAir.observe(this@MovieListActivity) {
                        adapter.submitList(it.data)
                    }
                }

                "tv_popular" -> {
                    titleLabel.text = title
                    viewModel.tvPopular.observe(this@MovieListActivity) {
                        adapter.submitList(it.data)
                    }
                }

                "tv_top_rated" -> {
                    titleLabel.text = title
                    viewModel.tvTopRated.observe(this@MovieListActivity) {
                        adapter.submitList(it.data)
                    }
                }

                "similar_recommendation" -> {
                    titleLabel.text = title
                    val data = intent.getParcelableExtra<Parcelable>("DATA")
                    when (data) {
                        is MovieDetailsResponse -> {
                            adapter.submitList(data.similar?.results)
                            adapter.submitList(data.recommendations?.results)
                        }
                        is TvSeriesDetailsResponse -> {
                            adapter.submitList(data.similar?.results)
                            adapter.submitList(data.recommendations?.results)
                        }
                    }
                }

            }


        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onMovieNowPlayingClick(nowPlaying: Any) {
        when (nowPlaying) {
            is MovieNowPlaying -> {
                val bottomSheetFragment =
                    nowPlaying.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "movie") }
                bottomSheetFragment?.show(supportFragmentManager, bottomSheetFragment.tag)
            }

            is MovieUpcoming -> {
                val bottomSheetFragment =
                    nowPlaying.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "movie") }
                bottomSheetFragment?.show(supportFragmentManager, bottomSheetFragment.tag)
            }

            is MoviePopular -> {
                val bottomSheetFragment =
                    nowPlaying.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "movie") }
                bottomSheetFragment?.show(supportFragmentManager, bottomSheetFragment.tag)
            }

            is MovieTopRated -> {
                val bottomSheetFragment =
                    nowPlaying.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "movie") }
                bottomSheetFragment?.show(supportFragmentManager, bottomSheetFragment.tag)
            }

            is TvAiringToday -> {
                val bottomSheetFragment =
                    nowPlaying.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "tv") }
                bottomSheetFragment?.show(supportFragmentManager, bottomSheetFragment.tag)
            }

            is TvOnTheAir -> {
                val bottomSheetFragment =
                    nowPlaying.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "tv") }
                bottomSheetFragment?.show(supportFragmentManager, bottomSheetFragment.tag)
            }

            is TvPopular -> {
                val bottomSheetFragment =
                    nowPlaying.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "tv") }
                bottomSheetFragment?.show(supportFragmentManager, bottomSheetFragment.tag)
            }

            is TvTopRated -> {
                val bottomSheetFragment =
                    nowPlaying.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "tv") }
                bottomSheetFragment?.show(supportFragmentManager, bottomSheetFragment.tag)
            }

            is Movie -> {
                val bottomSheetFragment =
                    nowPlaying.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "movie") }
                bottomSheetFragment?.show(supportFragmentManager, bottomSheetFragment.tag)
            }

            is TvSeries -> {
                val bottomSheetFragment =
                    nowPlaying.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "tv") }
                bottomSheetFragment?.show(supportFragmentManager, bottomSheetFragment.tag)
            }
        }
    }

}