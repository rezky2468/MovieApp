package com.example.movieapp.test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.example.movieapp.databinding.ActivityMovieListBinding
import com.example.movieapp.viewmodels.MovieViewModel

class MovieListActivity : AppCompatActivity() {

    private var _binding: ActivityMovieListBinding? = null
    private val binding get() = _binding!!
    private var adapter: MovieListAdapter? = null
    private var layoutManager: LayoutManager? = null
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMovieListBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        observeAnyChangePopularMovie()
//        setupRecyclerView()

    }

//    private fun observeAnyChangePopularMovie() {
//        viewModel.getPopularMovies().observe(this) {
//            if (it != null) {
//                when (it) {
//                    is RequestState.Loading -> showLoading()
//                    is RequestState.Success -> {
//                        hideLoading()
//                        it.data.results?.let { data -> adapter?.setMovie(data) }
//                    }
//                    is RequestState.Error -> {
//                        hideLoading()
//                        Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
//                    }
//                }
//            }
//        }
//    }
//
//    private fun setupRecyclerView() {
//        adapter = MovieListAdapter()
//        layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
//        binding.apply {
//            recyclerView.adapter = adapter
//            recyclerView.layoutManager = layoutManager
//        }
//    }
//
//    private fun showLoading() {
//        binding.progressBar.show()
//    }
//
//    private fun hideLoading() {
//        binding.progressBar.hide()
//    }
//
//    override fun onDestroy() {
//        super.onDestroy()
//        _binding = null
//    }
}