package com.example.movieapp.views.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivitySearchBinding
import com.example.movieapp.models.MultiSearch
import com.example.movieapp.viewmodels.MovieViewModel
import com.example.movieapp.views.detail.CustomBottomSheetDialogFragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchActivity : AppCompatActivity(), MultiSearchAdapter.OnItemClickListener, TextWatcher {

    private var multiSearchAdapter: MultiSearchAdapter = MultiSearchAdapter(this) // Declare the adapter as a global variable
    private val viewModel: MovieViewModel by viewModels()
    var query = ""

    // Define a global Handler and a global Runnable
    private val handler = Handler()
    private var runnable: Runnable = Runnable {}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySearchBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Adapters
//        val movieSearchAdapter = MovieSearchAdapter(this@SearchActivity)

        binding.apply {
            searchEditText.requestFocus()

            recyclerView.adapter = multiSearchAdapter
            recyclerView.layoutManager = GridLayoutManager(this@SearchActivity, 3)

            searchEditText.addTextChangedListener(this@SearchActivity)

            backImageView.setOnClickListener {
                super.onBackPressed()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }

            filterImageView.setOnClickListener {
                Toast.makeText(this@SearchActivity, "Coming soon!", Toast.LENGTH_SHORT).show()
            }

        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
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
        viewModel.fetchMultiSearch(query).observe(this@SearchActivity) {
            multiSearchAdapter.submitList(it.data)
        }
    }

    override fun onMultiSearchClick(movie: MultiSearch) {
        val bottomSheetFragment = movie.id?.let { CustomBottomSheetDialogFragment.newInstance(it, movie.mediaType.toString()) }
        bottomSheetFragment?.show(supportFragmentManager, bottomSheetFragment.tag)
    }

}