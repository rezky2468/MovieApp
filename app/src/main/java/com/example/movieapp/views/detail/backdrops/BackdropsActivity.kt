package com.example.movieapp.views.detail.backdrops

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityBackdropsBinding
import com.example.movieapp.models.movies.BackdropsItem
import com.example.movieapp.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import dagger.hilt.android.HiltAndroidApp

@AndroidEntryPoint
class BackdropsActivity : AppCompatActivity(), BackdropsAdapter.OnItemClickListener {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityBackdropsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adapters
        val adapter = BackdropsAdapter(this)

        val movieId = intent.getIntExtra("ID", -1)
        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = GridLayoutManager(this@BackdropsActivity, 2)
        }

        viewModel.fetchMovieImages(movieId).observe(this@BackdropsActivity) { result ->
            adapter.submitList(result.data)
        }

    }

    override fun onBackdropsClick(backdrops: BackdropsItem) {
        Toast.makeText(this@BackdropsActivity, "Clicked", Toast.LENGTH_LONG).show()
    }
}