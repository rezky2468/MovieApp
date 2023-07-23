package com.example.movieapp.views.home.tv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.FragmentTvSeriesBinding
import com.example.movieapp.models.tv.TvAiringToday
import com.example.movieapp.models.tv.TvOnTheAir
import com.example.movieapp.models.tv.TvPopular
import com.example.movieapp.models.tv.TvTopRated
import com.example.movieapp.viewmodels.MovieViewModel
import com.example.movieapp.views.detail.MovieDetailActivity
import com.example.movieapp.views.home.tv.adapters.TvAiringTodayAdapter
import com.example.movieapp.views.home.tv.adapters.TvOnTheAirAdapter
import com.example.movieapp.views.home.tv.adapters.TvPopularAdapter
import com.example.movieapp.views.home.tv.adapters.TvTopRatedAdapter
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class TvSeriesFragment : Fragment(),
    TvAiringTodayAdapter.OnItemClickListener,
    TvOnTheAirAdapter.OnItemClickListener,
    TvPopularAdapter.OnItemClickListener,
    TvTopRatedAdapter.OnItemClickListener
{

    private lateinit var binding: FragmentTvSeriesBinding
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentTvSeriesBinding.inflate(inflater, container, false)

        // Adapters
        val tvAiringTodayAdapter = TvAiringTodayAdapter(this)
        val tvOnTheAirAdapter = TvOnTheAirAdapter(this)
        val tvPopularAdapter = TvPopularAdapter(this)
        val tvTopRatedAdapter = TvTopRatedAdapter(this)

        binding.apply {

            tvAiringTodayRecyclerView.adapter = tvAiringTodayAdapter
            tvOnTheAirRecyclerView.adapter = tvOnTheAirAdapter
            tvPopularRecyclerView.adapter = tvPopularAdapter
            tvTopRatedRecyclerView.adapter = tvTopRatedAdapter

            tvAiringTodayRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            tvOnTheAirRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            tvPopularRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            tvTopRatedRecyclerView.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        }

        // ViewModel observe
        viewModel.tvAiringToday.observe(viewLifecycleOwner) {
            tvAiringTodayAdapter.submitList(it.data)
        }
        viewModel.tvOnTheAir.observe(viewLifecycleOwner) {
            tvOnTheAirAdapter.submitList(it.data)
        }
        viewModel.tvPopular.observe(viewLifecycleOwner) {
            tvPopularAdapter.submitList(it.data)
        }
        viewModel.tvTopRated.observe(viewLifecycleOwner) {
            tvTopRatedAdapter.submitList(it.data)
        }

        return binding.root
    }

    override fun onTvAiringTodayClick(tv: TvAiringToday) {
        Toast.makeText(context, "Clicked ${tv.name}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("IDENTIFIER", "tv")
        intent.putExtra("TV_TYPE", "tv_airing_today")
        intent.putExtra("SERIES_ID", tv.id)
        intent.putExtra("TV_AIRING_TODAY", tv)
        startActivity(intent)
    }

    override fun onTvOnTheAirClick(tv: TvOnTheAir) {
        Toast.makeText(context, "Clicked ${tv.name}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("IDENTIFIER", "tv")
        intent.putExtra("TV_TYPE", "tv_on_the_air")
        intent.putExtra("SERIES_ID", tv.id)
        intent.putExtra("TV_ON_THE_AIR", tv)
        startActivity(intent)
    }

    override fun onTvPopularClick(tv: TvPopular) {
        Toast.makeText(context, "Clicked ${tv.name}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("IDENTIFIER", "tv")
        intent.putExtra("TV_TYPE", "tv_popular")
        intent.putExtra("SERIES_ID", tv.id)
        intent.putExtra("TV_POPULAR", tv)
        startActivity(intent)
    }

    override fun onTvTopRatedClick(tv: TvTopRated) {
        Toast.makeText(context, "Clicked ${tv.name}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, MovieDetailActivity::class.java)
        intent.putExtra("IDENTIFIER", "tv")
        intent.putExtra("TV_TYPE", "tv_top_rated")
        intent.putExtra("SERIES_ID", tv.id)
        intent.putExtra("TV_TOP_RATED", tv)
        startActivity(intent)
    }


}