package com.example.movieapp.views.detail.videos

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebChromeClient
import android.webkit.WebViewClient
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.ActivityVideosBinding
import com.example.movieapp.models.movies.Video
import com.example.movieapp.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideosActivity : AppCompatActivity(), VideosAdapter.OnItemClickListener {

    private val viewModel: MovieViewModel by viewModels()
    var movieId = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityVideosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val youtubeUrl = "https://www.youtube.com/watch?v=xtav_zZuppU&t=591s"
        val video = "<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/xtav_zZuppU\" title=\"YouTube video player\" frameborder=\"0\" allow=\"accelerometer; autoplay; clipboard-write; encrypted-media; gyroscope; picture-in-picture; web-share\" allowfullscreen></iframe>"

        // Adapters
        val adapter = VideosAdapter(this@VideosActivity)

        // Get Movie ID
        movieId = intent.getIntExtra("MOVIE_ID", -1)

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@VideosActivity, LinearLayoutManager.VERTICAL, false)
        }

        viewModel.fetchMovieVideos(movieId).observe(this@VideosActivity) { result ->
            adapter.submitList(result.data)
        }

    }

    override fun onVideosClick(video: Video) {

    }
}