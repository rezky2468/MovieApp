package com.example.movieapp.views.detail.backdrops

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityBackdropsBinding
import com.example.movieapp.models.movies.MovieBackdrop
import com.example.movieapp.models.movies.MovieDetailsResponse
import com.example.movieapp.models.tv.TvSeriesBackdrop
import com.example.movieapp.models.tv.TvSeriesDetailsResponse
import com.example.movieapp.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BackdropsActivity : AppCompatActivity(), MovieBackdropsAdapter.OnItemClickListener<Any>{

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBackdropsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get data
        val data = intent.getParcelableExtra<Parcelable>("DATA")

        // Adapters
        val adapter = MovieBackdropsAdapter(this, "")

        when (data) {
            is MovieDetailsResponse -> {
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = GridLayoutManager(this@BackdropsActivity, 2)
                adapter.submitList(data.images?.backdrops)
            }
            is TvSeriesDetailsResponse -> {
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = GridLayoutManager(this@BackdropsActivity, 2)
                adapter.submitList(data.images?.backdrops)
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

    override fun onBackdropsClick(backdrops: Any) {

        val intent = Intent(this@BackdropsActivity, PhotoViewActivity::class.java)
        if (backdrops is MovieBackdrop) {
            intent.putExtra("PHOTO_URL", backdrops.filePath)
        } else if (backdrops is TvSeriesBackdrop) {
            intent.putExtra("PHOTO_URL", backdrops.filePath)
        }
        val options = ActivityOptions.makeCustomAnimation(
            this@BackdropsActivity,
            R.anim.fade_in,
            R.anim.fade_out
        )
        startActivity(intent, options.toBundle())
    }

}