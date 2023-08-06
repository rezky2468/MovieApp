package com.example.movieapp.views.detail.casts

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import android.os.Parcelable
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityCastsBinding
import com.example.movieapp.models.movies.MovieCast
import com.example.movieapp.models.movies.MovieDetailsResponse
import com.example.movieapp.models.tv.TvSeriesCast
import com.example.movieapp.models.tv.TvSeriesDetailsResponse
import com.example.movieapp.viewmodels.MovieViewModel
import com.example.movieapp.views.detail.backdrops.PhotoViewActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class CastsActivity : AppCompatActivity(),
    CastsAdapter.OnItemClickListener<Any> {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCastsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get data
        val data = intent.getParcelableExtra<Parcelable>("DATA")
        val adapter = CastsAdapter<Any>(this)
        when (data) {
            is MovieDetailsResponse -> {
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = GridLayoutManager(this@CastsActivity, 3)
                adapter.submitList(data.credits?.cast)
            }
            is TvSeriesDetailsResponse -> {
                binding.recyclerView.adapter = adapter
                binding.recyclerView.layoutManager = GridLayoutManager(this@CastsActivity, 3)
                adapter.submitList(data.credits?.cast)
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

    override fun onCastsClick(cast: Any) { // Use 'Any' as the parameter type for the click listener
        val intent = Intent(this@CastsActivity, PhotoViewActivity::class.java)
        if (cast is MovieCast) {
            intent.putExtra("PHOTO_URL", cast.profilePath)
            intent.putExtra("TITLE", cast.name + " as " + cast.character)
        } else if (cast is TvSeriesCast) {
            intent.putExtra("PHOTO_URL", cast.profilePath)
            intent.putExtra("TITLE", cast.name + " as " + cast.character)
        }
        val options = ActivityOptions.makeCustomAnimation(
            this,
            R.anim.fade_in,
            R.anim.fade_out
        )
        startActivity(intent, options.toBundle())
        startActivity(intent)
    }


}