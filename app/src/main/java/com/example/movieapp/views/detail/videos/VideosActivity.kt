package com.example.movieapp.views.detail.videos

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityVideosBinding
import com.example.movieapp.models.movies.MovieDetailsResponse
import com.example.movieapp.models.movies.MovieVideo
import com.example.movieapp.models.tv.TvSeriesDetailsResponse
import com.example.movieapp.models.tv.TvSeriesVideo
import com.example.movieapp.viewmodels.MovieViewModel
import com.example.movieapp.views.detail.VideoPlayerActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideosActivity : AppCompatActivity(), MovieVideosAdapter.OnItemClickListener, TvSeriesVideosAdapter.OnItemClickListener {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityVideosBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val data = intent.getParcelableExtra<Parcelable>("DATA")

        when (data) {
            is MovieDetailsResponse -> {
                val moviesAdapter = MovieVideosAdapter(this@VideosActivity)
                binding.recyclerView.adapter = moviesAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(this@VideosActivity, LinearLayoutManager.VERTICAL, false)
                moviesAdapter.submitList(data.videos?.results)
            }
            is TvSeriesDetailsResponse -> {
                val tvAdapter = TvSeriesVideosAdapter(this@VideosActivity)
                binding.recyclerView.adapter = tvAdapter
                binding.recyclerView.layoutManager = LinearLayoutManager(this@VideosActivity, LinearLayoutManager.VERTICAL, false)
                tvAdapter.submitList(data.videos?.results)
            }
        }
        binding.backImageView.setOnClickListener {
            super.onBackPressed()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onVideosClick(video: MovieVideo) {
        val intent = Intent(this@VideosActivity, VideoPlayerActivity::class.java)
        intent.putExtra("YOUTUBE_KEY", video.key)
        val options = ActivityOptions.makeCustomAnimation(
            this,
            R.anim.fade_in,
            R.anim.fade_out
        )
        startActivity(intent, options.toBundle())
    }

    override fun onTvSeriesVideosClick(video: TvSeriesVideo) {
        val intent = Intent(this@VideosActivity, VideoPlayerActivity::class.java)
        intent.putExtra("YOUTUBE_KEY", video.key)
        val options = ActivityOptions.makeCustomAnimation(
            this,
            R.anim.fade_in,
            R.anim.fade_out
        )
        startActivity(intent, options.toBundle())
    }

}