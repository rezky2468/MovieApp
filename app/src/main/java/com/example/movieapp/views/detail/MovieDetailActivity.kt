package com.example.movieapp.views.detail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.Window
import android.view.WindowManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMovieDetailBinding
import com.example.movieapp.models.movies.BackdropsItem
import com.example.movieapp.models.movies.MovieCast
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
import com.example.movieapp.viewmodels.MovieViewModel
import com.example.movieapp.views.detail.backdrops.BackdropsActivity
import com.example.movieapp.views.detail.backdrops.BackdropsAdapter
import com.example.movieapp.views.detail.backdrops.TvBackdropsAdapter
import com.example.movieapp.views.detail.casts.CastsActivity
import com.example.movieapp.views.detail.casts.CastsAdapter
import com.example.movieapp.views.detail.casts.TvCastsAdapter
import com.example.movieapp.views.detail.videos.MovieVideosPreviewAdapter
import com.example.movieapp.views.detail.videos.TvVideosAdapter
import com.example.movieapp.views.detail.videos.TvVideosPreviewAdapter
import com.example.movieapp.views.detail.videos.VideosActivity
import com.example.movieapp.views.detail.videos.VideosAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity(),
    BackdropsAdapter.OnItemClickListener,
    CastsAdapter.OnItemClickListener,
    MovieVideosPreviewAdapter.OnItemClickListener,
    TvBackdropsAdapter.OnItemClickListener,
    TvCastsAdapter.OnItemClickListener,
    TvVideosAdapter.OnItemClickListener,
    TvVideosPreviewAdapter.OnItemClickListener {

    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel: MovieViewModel by viewModels()

    private lateinit var data: TvAiringToday
    var id = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMovieDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Calculate the peek height based on 16:9 aspect ratio
        val displayMetrics = DisplayMetrics()
        windowManager.defaultDisplay.getMetrics(displayMetrics)
        val screenHeight = displayMetrics.heightPixels
        val peekHeight = (screenHeight.toFloat() / 16 * 9).toInt()

        // Set the calculated peekHeight to the BottomSheetBehavior
        val bottomSheetBehavior = BottomSheetBehavior.from(binding.sheet)
        bottomSheetBehavior.peekHeight = peekHeight

        // Adapters
        val backdropsAdapter = BackdropsAdapter(this)
        val castsAdapter = CastsAdapter(this)
        val videosAdapter = MovieVideosPreviewAdapter(this)
        val tvBackdropsAdapter = TvBackdropsAdapter(this)
        val tvCastsAdapter = TvCastsAdapter(this)
        val tvVideosPreviewAdapter = TvVideosPreviewAdapter(this)

        binding.apply {

            showBackdropsImageView.setOnClickListener {
                val intent = Intent(this@MovieDetailActivity, BackdropsActivity::class.java)
                intent.putExtra("ID", id)
                startActivity(intent)
            }

            showCastImageView.setOnClickListener {
                val intent = Intent(this@MovieDetailActivity, CastsActivity::class.java)
                intent.putExtra("ID", id)
                startActivity(intent)
            }

            showVideoImageView.setOnClickListener {
                val intent = Intent(this@MovieDetailActivity, VideosActivity::class.java)
                intent.putExtra("ID", id)
                startActivity(intent)
            }
        }


        val identifier = intent.getStringExtra("IDENTIFIER")
        if (identifier == "movie") {

            id = intent.getIntExtra("MOVIE_ID", -1)

            binding.apply {

                backdropsRecyclerView.adapter = backdropsAdapter
                backdropsRecyclerView.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

                castRecyclerView.adapter = castsAdapter
                castRecyclerView.layoutManager =
                    LinearLayoutManager(
                        this@MovieDetailActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )

                videoRecyclerView.adapter = videosAdapter
                videoRecyclerView.layoutManager =
                    GridLayoutManager(this@MovieDetailActivity, 2)
            }

            when (intent.getStringExtra("MOVIE_TYPE")) {
                "movie_now_playing" -> {
                    val data = intent.getParcelableExtra<MovieNowPlaying>("MOVIE_NOW_PLAYING")
                    binding.apply {
                        Glide.with(this@MovieDetailActivity)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + data?.posterPath)
                            .into(posterImageView)
                        titleTextView.text = data?.title
                        descriptionTextView.text = data?.overview
                        releaseDateTextView.text = data?.releaseDate
                    }
                }

                "movie_upcoming" -> {
                    val data = intent.getParcelableExtra<MovieUpcoming>("MOVIE_UPCOMING")
                    binding.apply {
                        Glide.with(this@MovieDetailActivity)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + data?.posterPath)
                            .into(posterImageView)
                        titleTextView.text = data?.title
                        descriptionTextView.text = data?.overview
                        releaseDateTextView.text = data?.releaseDate
                    }
                }

                "movie_popular" -> {
                    val data = intent.getParcelableExtra<MoviePopular>("MOVIE_POPULAR")
                    binding.apply {
                        Glide.with(this@MovieDetailActivity)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + data?.posterPath)
                            .into(posterImageView)
                        titleTextView.text = data?.title
                        descriptionTextView.text = data?.overview
                        releaseDateTextView.text = data?.releaseDate
                    }
                }

                "movie_top_rated" -> {
                    val data = intent.getParcelableExtra<MovieTopRated>("MOVIE_TOP_RATED")
                    binding.apply {
                        Glide.with(this@MovieDetailActivity)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + data?.posterPath)
                            .into(posterImageView)
                        titleTextView.text = data?.title
                        descriptionTextView.text = data?.overview
                        releaseDateTextView.text = data?.releaseDate
                    }
                }
            }

            viewModel.fetchMovieImages(id).observe(this@MovieDetailActivity) {
                backdropsAdapter.submitList(it.data)
            }

            viewModel.fetchMovieCasts(id).observe(this@MovieDetailActivity) {
                castsAdapter.submitList(it.data)
            }

            viewModel.fetchMovieVideos(id).observe(this@MovieDetailActivity) {
                videosAdapter.submitList(it.data)
            }

        } else if (identifier == "tv") {

            id = intent.getIntExtra("SERIES_ID", -1)

            binding.apply {

                backdropsRecyclerView.adapter = tvBackdropsAdapter
                backdropsRecyclerView.layoutManager =
                    StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

                castRecyclerView.adapter = tvCastsAdapter
                castRecyclerView.layoutManager =
                    LinearLayoutManager(
                        this@MovieDetailActivity,
                        LinearLayoutManager.HORIZONTAL,
                        false
                    )

                videoRecyclerView.adapter = tvVideosPreviewAdapter
                videoRecyclerView.layoutManager =
                    GridLayoutManager(this@MovieDetailActivity, 2)

            }

            when (intent.getStringExtra("TV_TYPE")) {
                "tv_airing_today" -> {
                    val data = intent.getParcelableExtra<TvAiringToday>("TV_AIRING_TODAY")
                    binding.apply {
                        Glide.with(this@MovieDetailActivity)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + data?.posterPath)
                            .into(posterImageView)
                        titleTextView.text = data?.name
                        descriptionTextView.text = data?.overview
                        releaseDateTextView.text = data?.firstAirDate
                    }
                }

                "tv_on_the_air" -> {
                    val data = intent.getParcelableExtra<TvOnTheAir>("TV_ON_THE_AIR")
                    binding.apply {
                        Glide.with(this@MovieDetailActivity)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + data?.posterPath)
                            .into(posterImageView)
                        titleTextView.text = data?.name
                        descriptionTextView.text = data?.overview
                        releaseDateTextView.text = data?.firstAirDate
                    }
                }

                "tv_popular" -> {
                    val data = intent.getParcelableExtra<TvPopular>("TV_POPULAR")
                    binding.apply {
                        Glide.with(this@MovieDetailActivity)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + data?.posterPath)
                            .into(posterImageView)
                        titleTextView.text = data?.name
                        descriptionTextView.text = data?.overview
                        releaseDateTextView.text = data?.firstAirDate
                    }
                }

                "tv_top_rated" -> {
                    val data = intent.getParcelableExtra<TvTopRated>("TV_TOP_RATED")
                    binding.apply {
                        Glide.with(this@MovieDetailActivity)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + data?.posterPath)
                            .into(posterImageView)
                        titleTextView.text = data?.name
                        descriptionTextView.text = data?.overview
                        releaseDateTextView.text = data?.firstAirDate
                    }
                }
            }

            viewModel.fetchTvBackdrops(id).observe(this@MovieDetailActivity) {
                tvBackdropsAdapter.submitList(it.data)
            }

            viewModel.fetchTvCasts(id).observe(this@MovieDetailActivity) { result ->
                tvCastsAdapter.submitList(result.data)
            }

            viewModel.fetchTvVideos(id).observe(this@MovieDetailActivity) {
                tvVideosPreviewAdapter.submitList(it.data)
            }

        }

    }

    override fun onBackdropsClick(backdrops: BackdropsItem) {
        Toast.makeText(this, "Clicked ${backdrops.filePath}", Toast.LENGTH_LONG).show()
    }

    override fun onCastsClick(cast: MovieCast) {
        Toast.makeText(this, "Clicked on ${cast.name} as ${cast.character}", Toast.LENGTH_LONG)
            .show()
    }

    override fun onVideosPreviewClick(video: Video) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show()
        val intent = Intent(this@MovieDetailActivity, VideosActivity::class.java)
        intent.putExtra("IDENTIFIER", "movie")
        intent.putExtra("MOVIE_VIDEOS", video)
        intent.putExtra("MOVIE_ID", video.id)
        startActivity(intent)
    }

    override fun onTvBackdropsClick(backdrops: TvBackdrops) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show()
    }

    override fun onTvCastsClick(cast: TvCast) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show()
    }

    override fun onTvVideosClick(video: TvVideo) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show()
    }

    override fun onTvVideosPreviewClick(video: TvVideo) {
        Toast.makeText(this, "Clicked", Toast.LENGTH_LONG).show()
    }

//    override fun onVideosClick(video: Video) {
//        Toast.makeText(this, "Clicked ${video.name}", Toast.LENGTH_LONG).show()
////        val intent = Intent(this@MovieDetailActivity, VideosActivity::class.java)
////        intent.putExtra("MOVIE_ID", movieId)
////        startActivity(intent)
//    }
}