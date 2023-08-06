package com.example.movieapp.views.detail

import android.app.ActivityOptions
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.MovieListAdapter
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentCustomBottomSheetDialogBinding
import com.example.movieapp.models.movies.Movie
import com.example.movieapp.models.movies.MovieBackdrop
import com.example.movieapp.models.movies.MovieCast
import com.example.movieapp.models.movies.MovieDetailsResponse
import com.example.movieapp.models.movies.MovieVideo
import com.example.movieapp.models.tv.TvSeries
import com.example.movieapp.models.tv.TvSeriesBackdrop
import com.example.movieapp.models.tv.TvSeriesCast
import com.example.movieapp.models.tv.TvSeriesDetailsResponse
import com.example.movieapp.models.tv.TvSeriesVideo
import com.example.movieapp.test.MovieListActivity
import com.example.movieapp.utils.Resource
import com.example.movieapp.viewmodels.MovieViewModel
import com.example.movieapp.views.detail.backdrops.BackdropsActivity
import com.example.movieapp.views.detail.backdrops.MovieBackdropsAdapter
import com.example.movieapp.views.detail.backdrops.PhotoViewActivity
import com.example.movieapp.views.detail.backdrops.TvSeriesBackdropsAdapter
import com.example.movieapp.views.detail.casts.CastsActivity
import com.example.movieapp.views.detail.casts.MovieCastsAdapter
import com.example.movieapp.views.detail.casts.TvSeriesCastsAdapter
import com.example.movieapp.views.detail.videos.MovieVideosPreviewAdapter
import com.example.movieapp.views.detail.videos.TvSeriesVideosAdapter
import com.example.movieapp.views.detail.videos.TvSeriesVideosPreviewAdapter
import com.example.movieapp.views.detail.videos.VideosActivity
import com.example.movieapp.views.home.movies.adapters.MoviePreviewAdapter
import com.example.movieapp.views.home.tv.adapters.TvSeriesPreviewAdapter
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CustomBottomSheetDialogFragment : BottomSheetDialogFragment(),
    MovieBackdropsAdapter.OnItemClickListener<Any>,
    MovieVideosPreviewAdapter.OnItemClickListener,
    MovieCastsAdapter.OnItemClickListener,
    TvSeriesVideosPreviewAdapter.OnItemClickListener,
    TvSeriesCastsAdapter.OnItemClickListener,
    MoviePreviewAdapter.OnItemClickListener,
    TvSeriesPreviewAdapter.OnItemClickListener {

    private lateinit var binding: FragmentCustomBottomSheetDialogBinding
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(BottomSheetDialogFragment.STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    companion object {
        private const val ARG_MOVIE_ID = "movie_id"
        private const val ARG_MOVIE_TYPE = "series_id"
        fun newInstance(movieId: Int, movieType: String): CustomBottomSheetDialogFragment {
            val fragment = CustomBottomSheetDialogFragment()
            val args = Bundle()
            args.putInt(ARG_MOVIE_ID, movieId)
            args.putString(ARG_MOVIE_TYPE, movieType)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentCustomBottomSheetDialogBinding.inflate(inflater, container, false)

        // Adapters
        val backdropsAdapter = MovieBackdropsAdapter(this, "detail")
        val movieCastsAdapter = MovieCastsAdapter(this, "detail")
        val movieVideosAdapter = MovieVideosPreviewAdapter(this, "detail")
//        val tvSeriesBackdropsAdapter = TvSeriesBackdropsAdapter(this, "detail")
        val tvSeriesCastsAdapter = TvSeriesCastsAdapter(this, "detail")
        val tvSeriesVideosAdapter = TvSeriesVideosPreviewAdapter(this, "detail")

        val movieSimilarAdapter = MoviePreviewAdapter(this)
        val movieRecommendationAdapter = MoviePreviewAdapter(this)
        val tvSeriesSimilarAdapter = TvSeriesPreviewAdapter(this)
        val tvSeriesRecommendationAdapter = TvSeriesPreviewAdapter(this)


        // Retrieve the movieId from arguments
        val movieId = arguments?.getInt(ARG_MOVIE_ID) ?: -1
        val movieType = arguments?.getString(ARG_MOVIE_TYPE)

        binding.apply {
            val isConnected = isInternetAvailable(context)
            if (isConnected) {
                if (movieType == "movie") {

                    // RecyclerView Adapters
                    backdropsRecyclerView.adapter = backdropsAdapter
                    castRecyclerView.adapter = movieCastsAdapter
                    videoRecyclerView.adapter = movieVideosAdapter
                    similarRecyclerView.adapter = movieSimilarAdapter
                    recommendationRecyclerView.adapter = movieRecommendationAdapter

                    backdropsRecyclerView.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    castRecyclerView.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    videoRecyclerView.layoutManager = GridLayoutManager(context, 2)
                    similarRecyclerView.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
                    recommendationRecyclerView.layoutManager =
                        LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

                    viewModel.fetchMovieDetails(movieId).observe(viewLifecycleOwner) { result ->

                        progressBar.visibility =
                            if (result is Resource.Loading) View.VISIBLE else View.GONE

                        when (result) {
                            is Resource.Success -> {
                                nestedScrollView.visibility = View.VISIBLE
                                errorTextView.visibility = View.GONE

                                prepareHideShowMoreButton(
                                    result.data,
                                    result.data?.images?.backdrops?.size,
                                    result.data?.credits?.cast?.size,
                                    result.data?.videos?.results?.size,
                                    result.data?.similar?.results?.size,
                                    result.data?.recommendations?.results?.size
                                )
                                prepareShowMoreButtonClick(
                                    "movie",
                                    result.data?.id,
                                    result?.data
                                )

                                // Adapters
                                backdropsAdapter.submitList(result.data?.images?.backdrops)
                                movieVideosAdapter.submitList(result.data?.videos?.results)
                                movieCastsAdapter.submitList(result.data?.credits?.cast)
                                movieSimilarAdapter.submitList(result.data?.similar?.results)
                                movieRecommendationAdapter.submitList(result.data?.recommendations?.results)

                                val genres = mutableListOf<String>()
                                result.data?.genres?.forEach { genre ->
                                    genre.name?.let { genreName ->
                                        genres.add(genreName)
                                    }
                                }

                                Glide.with(this@CustomBottomSheetDialogFragment)
                                    .load(BuildConfig.TMDB_PHOTO_BASE_URL + result.data?.posterPath)
                                    .into(posterImageView)
                                titleTextView.text = result.data?.title
                                descriptionTextView.text = result.data?.overview
                                releaseDateTextView.text = result.data?.releaseDate
                                durationTextView.text =
                                    result.data?.runtime.toString() + " minutes"
                                genreTextView.text = genres.joinToString(", ")

                                posterImageView.setOnClickListener {
                                    val intent = Intent(context, PhotoViewActivity::class.java)
                                    intent.putExtra("PHOTO_URL", result.data?.posterPath)
                                    val options = ActivityOptions.makeCustomAnimation(
                                        context,
                                        R.anim.fade_in,
                                        R.anim.fade_out
                                    )
                                    startActivity(intent, options.toBundle())
                                }

                                progressBar.visibility = View.GONE
                                nestedScrollView.visibility = View.VISIBLE
                            }

                            is Resource.Error -> {
                                progressBar.visibility = View.GONE
                                errorTextView.visibility = View.VISIBLE
                                Toast.makeText(context, "Error loading data", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            else -> {

                            }
                        }

                    }
                } else if (movieType == "tv") {

                    backdropsRecyclerView.adapter = backdropsAdapter
                    backdropsRecyclerView.layoutManager =
                        StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
                    castRecyclerView.adapter = tvSeriesCastsAdapter
                    castRecyclerView.layoutManager =
                        LinearLayoutManager(
                            context,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                    videoRecyclerView.adapter = tvSeriesVideosAdapter
                    videoRecyclerView.layoutManager =
                        GridLayoutManager(context, 2)
                    similarRecyclerView.adapter = tvSeriesSimilarAdapter
                    similarRecyclerView.layoutManager =
                        LinearLayoutManager(
                            context,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )
                    recommendationRecyclerView.adapter = tvSeriesRecommendationAdapter
                    recommendationRecyclerView.layoutManager =
                        LinearLayoutManager(
                            context,
                            LinearLayoutManager.HORIZONTAL,
                            false
                        )


                    viewModel.fetchTvSeriesDetails(movieId).observe(viewLifecycleOwner) { result ->

                        progressBar.visibility =
                            if (result is Resource.Loading) View.VISIBLE else View.GONE

                        when (result) {
                            is Resource.Success -> {

                                nestedScrollView.visibility = View.VISIBLE
                                errorTextView.visibility = View.GONE

                                prepareShowMoreButtonClick("tv", result.data?.id, result.data)
                                prepareHideShowMoreButton(
                                    result.data,
                                    result.data?.images?.backdrops?.size,
                                    result.data?.credits?.cast?.size,
                                    result.data?.videos?.results?.size,
                                    result.data?.similar?.results?.size,
                                    result.data?.recommendations?.results?.size
                                )

                                // Adapters submit list
                                backdropsAdapter.submitList(result.data?.images?.backdrops)
                                tvSeriesVideosAdapter.submitList(result.data?.videos?.results)
                                tvSeriesCastsAdapter.submitList(result.data?.credits?.cast)
                                tvSeriesSimilarAdapter.submitList(result.data?.similar?.results)
                                tvSeriesRecommendationAdapter.submitList(result.data?.recommendations?.results)

                                val genres = mutableListOf<String>()
                                result.data?.genres?.forEach { genre ->
                                    genre.name?.let { genreName ->
                                        genres.add(genreName)
                                    }
                                }

                                Glide.with(this@CustomBottomSheetDialogFragment)
                                    .load(BuildConfig.TMDB_PHOTO_BASE_URL + result.data?.posterPath)
                                    .into(posterImageView)
                                titleTextView.text = result.data?.name
                                descriptionTextView.text = result.data?.overview
                                releaseDateLabel.text = "First air date:"
                                releaseDateTextView.text = result.data?.firstAirDate
                                genreTextView.text = genres.joinToString(", ")
                                posterImageView.setOnClickListener {
                                    val intent = Intent(context, PhotoViewActivity::class.java)
                                    intent.putExtra("PHOTO_URL", result.data?.posterPath)
                                    val options = ActivityOptions.makeCustomAnimation(
                                        context,
                                        R.anim.fade_in,
                                        R.anim.fade_out
                                    )
                                    startActivity(intent, options.toBundle())
                                }

                                progressBar.visibility = View.GONE
                                nestedScrollView.visibility = View.VISIBLE

                            }

                            is Resource.Error -> {
                                progressBar.visibility = View.GONE
                                errorTextView.visibility = View.VISIBLE
                                Toast.makeText(context, "Error loading data", Toast.LENGTH_SHORT)
                                    .show()
                            }

                            else -> {

                            }
                        }

                    }
                }


            } else {
                errorTextView.visibility = View.VISIBLE
                errorTextView.text = "No internet connection!"
                progressBar.visibility = View.INVISIBLE
            }

        }
        return binding.root

    }



    private fun <T> prepareShowMoreButtonClick(identifier: String, id: Int?, data: T) {
        binding.showBackdropsImageView.setOnClickListener {
            val intent = Intent(context, BackdropsActivity::class.java)
            intent.putExtra("IDENTIFIER", identifier)
            intent.putExtra("ID", id)
            when (data) {
                is MovieDetailsResponse -> {
                    intent.putExtra("DATA", data)
                }

                is TvSeriesDetailsResponse -> {
                    intent.putExtra("DATA", data)
                }
            }
            val options = ActivityOptions.makeCustomAnimation(
                context,
                R.anim.fade_in,
                R.anim.fade_out
            )
            startActivity(intent, options.toBundle())
        }
        binding.showCastImageView.setOnClickListener {
            val intent = Intent(context, CastsActivity::class.java)
            intent.putExtra("IDENTIFIER", identifier)
            intent.putExtra("ID", id)
            when (data) {
                is MovieDetailsResponse -> {
                    intent.putExtra("DATA", data)
                }

                is TvSeriesDetailsResponse -> {
                    intent.putExtra("DATA", data)
                }
            }
            val options = ActivityOptions.makeCustomAnimation(
                context,
                R.anim.fade_in,
                R.anim.fade_out
            )
            startActivity(intent, options.toBundle())
        }
        binding.showVideoImageView.setOnClickListener {
            val intent = Intent(context, VideosActivity::class.java)
            intent.putExtra("IDENTIFIER", identifier)
            intent.putExtra("ID", id)
            when (data) {
                is MovieDetailsResponse -> {
                    intent.putExtra("DATA", data)
                }

                is TvSeriesDetailsResponse -> {
                    intent.putExtra("DATA", data)
                }
            }
            val options = ActivityOptions.makeCustomAnimation(
                context,
                R.anim.fade_in,
                R.anim.fade_out
            )
            startActivity(intent, options.toBundle())
        }
        binding.showSimilarImageView.setOnClickListener {
            val intent = Intent(context, MovieListActivity::class.java)
            intent.putExtra("TITLE", "Similar Movies")
            intent.putExtra("TYPE", "similar_recommendation")
            when (data) {
                is MovieDetailsResponse -> {
                    intent.putExtra("DATA", data)
                }

                is TvSeriesDetailsResponse -> {
                    intent.putExtra("DATA", data)
                }
            }
            val options = ActivityOptions.makeCustomAnimation(
                context,
                R.anim.fade_in,
                R.anim.fade_out
            )
            startActivity(intent, options.toBundle())
        }
        binding.showRecommendationImageView.setOnClickListener {
            val intent = Intent(context, MovieListActivity::class.java)
            intent.putExtra("TITLE", "Recommendations")
            intent.putExtra("TYPE", "similar_recommendation")
            when (data) {
                is MovieDetailsResponse -> {
                    intent.putExtra("DATA", data)
                }

                is TvSeriesDetailsResponse -> {
                    intent.putExtra("DATA", data)
                }
            }
            val options = ActivityOptions.makeCustomAnimation(
                context,
                R.anim.fade_in,
                R.anim.fade_out
            )
            startActivity(intent, options.toBundle())
        }
    }

    private fun <T> prepareHideShowMoreButton(
        result: T?,
        backdrops: Int?,
        casts: Int?,
        videos: Int?,
        similar: Int?,
        recommendation: Int?
    ) {
        binding.apply {
            if (result != null) {
                showBackdropsImageView.visibility = if (backdrops!! > 6) View.VISIBLE else View.GONE
                showCastImageView.visibility = if (casts!! > 10) View.VISIBLE else View.GONE
                showVideoImageView.visibility = if (videos!! > 6) View.VISIBLE else View.GONE
                showSimilarImageView.visibility = if (similar!! > 10) View.VISIBLE else View.GONE
                showRecommendationImageView.visibility =
                    if (recommendation!! > 10) View.VISIBLE else View.GONE
            } else {
                showBackdropsImageView.visibility = View.GONE
                showCastImageView.visibility = View.GONE
                showVideoImageView.visibility = View.GONE
                showSimilarImageView.visibility = View.GONE
                showRecommendationImageView.visibility = View.GONE
            }
        }
    }

    override fun onBackdropsClick(backdrops: Any) {
        val intent = Intent(context, PhotoViewActivity::class.java)
        var filePath = ""
        if (backdrops is MovieBackdrop) {
            intent.putExtra("PHOTO_URL", backdrops.filePath)
            filePath = backdrops.filePath.toString()
        } else if (backdrops is TvSeriesBackdrop) {
            intent.putExtra("PHOTO_URL", backdrops.filePath)
        }
        val options = ActivityOptions.makeCustomAnimation(
            context,
            R.anim.fade_in,
            R.anim.fade_out
        )
        startActivity(intent, options.toBundle())
    }

    override fun onCastsClick(cast: MovieCast) {
        val intent = Intent(context, PhotoViewActivity::class.java)
        intent.putExtra("PHOTO_URL", cast.profilePath)
        intent.putExtra("TITLE", cast.name + " as " + cast.character)
        val options = ActivityOptions.makeCustomAnimation(
            context,
            R.anim.fade_in,
            R.anim.fade_out
        )
        startActivity(intent, options.toBundle())
    }

    override fun onTvSeriesCastsClick(cast: TvSeriesCast) {
        val intent = Intent(context, PhotoViewActivity::class.java)
        intent.putExtra("PHOTO_URL", cast.profilePath)
        intent.putExtra("TITLE", cast.name + " as " + cast.character)
        val options = ActivityOptions.makeCustomAnimation(
            context,
            R.anim.fade_in,
            R.anim.fade_out
        )
        startActivity(intent, options.toBundle())
    }

    override fun onVideosPreviewClick(video: MovieVideo) {
        val intent = Intent(context, VideoPlayerActivity::class.java)
        intent.putExtra("YOUTUBE_KEY", video.key)
        val options = ActivityOptions.makeCustomAnimation(
            context,
            R.anim.fade_in,
            R.anim.fade_out
        )
        startActivity(intent, options.toBundle())
    }

    override fun onTvSeriesVideosPreviewClick(video: TvSeriesVideo) {
        val intent = Intent(context, VideoPlayerActivity::class.java)
        intent.putExtra("YOUTUBE_KEY", video.key)
        val options = ActivityOptions.makeCustomAnimation(
            context,
            R.anim.fade_in,
            R.anim.fade_out
        )
        startActivity(intent, options.toBundle())
    }

    override fun onMovieClick(movie: Movie) {
        val bottomSheetFragment = movie.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "movie") }
        bottomSheetFragment?.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    override fun onTvSeriesClick(tv: TvSeries) {
        val bottomSheetFragment = tv.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "tv") }
        bottomSheetFragment?.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    private fun isInternetAvailable(context: Context?): Boolean {
        val connectivityManager =
            context?.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

        val networkCapabilities = connectivityManager.activeNetwork ?: return false
        val actNw =
            connectivityManager.getNetworkCapabilities(networkCapabilities) ?: return false

        return when {
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true
            // For other device how are able to connect with Ethernet
            actNw.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET) -> true
            else -> false
        }
    }

}