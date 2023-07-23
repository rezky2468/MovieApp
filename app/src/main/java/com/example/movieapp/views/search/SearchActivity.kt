package com.example.movieapp.views.search

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivitySearchBinding
import com.example.movieapp.models.movies.MovieSearch
import com.example.movieapp.viewmodels.MovieViewModel
import com.example.movieapp.views.detail.MovieDetailActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), MovieSearchAdapter.OnItemClickListener, TextWatcher {

    private var movieSearchAdapter: MovieSearchAdapter = MovieSearchAdapter(this) // Declare the adapter as a global variable
    private val viewModel: MovieViewModel by viewModels()
    var query = ""

    // Define a global Handler and a global Runnable
    private val handler = Handler()
    private var runnable: Runnable = Runnable {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // Set the status bar color
//        val window: Window = window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
//        window.statusBarColor = getColor(R.color.white)


        // Adapters
//        val movieSearchAdapter = MovieSearchAdapter(this@SearchActivity)

        binding.apply {
            searchEditText.requestFocus()

            recyclerView.adapter = movieSearchAdapter
            recyclerView.layoutManager = GridLayoutManager(this@SearchActivity, 3)

            // Show the soft keyboard automatically
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY)

            searchEditText.addTextChangedListener(this@SearchActivity)

            backImageView.setOnClickListener {
                super.onBackPressed()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onMovieSearchClick(movie: MovieSearch) {
        Toast.makeText(this@SearchActivity, "Clicked ${movie.id}", Toast.LENGTH_SHORT).show()
        val intent = Intent(this@SearchActivity, MovieDetailActivity::class.java)
        intent.putExtra("movie_id", movie.id)
        startActivity(intent)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        //
    }

    override fun afterTextChanged(p0: Editable?) {
        // Remove any previous pending updates
        handler.removeCallbacks(runnable)

        // Schedule a new update after the specified delay
        runnable = Runnable {
            updateRecyclerView(p0)
        }
        handler.postDelayed(runnable, 500)
    }

    private fun updateRecyclerView(p0: Editable?) {
        // This method will be called after the specified delay (300 ms)
        query = p0.toString().trim()
        viewModel.fetchMovieSearch(query).observe(this@SearchActivity) {
            movieSearchAdapter.submitList(it.data)
        }
    }

}