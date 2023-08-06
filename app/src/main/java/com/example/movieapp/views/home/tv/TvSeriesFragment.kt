package com.example.movieapp.views.home.tv

import android.app.ActivityOptions
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.FragmentMoviesBinding
import com.example.movieapp.databinding.FragmentTvSeriesBinding
import com.example.movieapp.models.tv.TvAiringToday
import com.example.movieapp.models.tv.TvOnTheAir
import com.example.movieapp.models.tv.TvPopular
import com.example.movieapp.models.tv.TvTopRated
import com.example.movieapp.test.MovieListActivity
import com.example.movieapp.viewmodels.MovieViewModel
import com.example.movieapp.views.detail.CustomBottomSheetDialogFragment
import com.example.movieapp.views.home.news.NewsActivity
import com.example.movieapp.views.home.tv.adapters.TvAiringTodayAdapter
import com.example.movieapp.views.home.tv.adapters.TvOnTheAirAdapter
import com.example.movieapp.views.home.tv.adapters.TvPopularAdapter
import com.example.movieapp.views.home.tv.adapters.TvTopRatedAdapter
import com.google.android.material.bottomsheet.BottomSheetBehavior
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

    private var scrollPosition = 0
    private val SCROLL_POSITION_KEY = "SCROLL_POSITION_KEY"

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(SCROLL_POSITION_KEY, scrollPosition)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        savedInstanceState?.let {
            scrollPosition = it.getInt(SCROLL_POSITION_KEY, 0)
        }
    }

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

            showTvAiringTodayImageView.setOnClickListener {
                val intent = Intent(context, MovieListActivity::class.java)
                intent.putExtra("TYPE", "tv_airing_today")
                intent.putExtra("TITLE", "Airing Today Tv Series")
                val options = ActivityOptions.makeCustomAnimation(
                    context,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                startActivity(intent, options.toBundle())
            }

            showTvOnTheAirImageView.setOnClickListener {
                val intent = Intent(context, MovieListActivity::class.java)
                intent.putExtra("TYPE", "tv_on_the_air")
                intent.putExtra("TITLE", "On The Air Tv Series")
                val options = ActivityOptions.makeCustomAnimation(
                    context,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                startActivity(intent, options.toBundle())
            }

            showTvPopularImageView.setOnClickListener {
                val intent = Intent(context, MovieListActivity::class.java)
                intent.putExtra("TYPE", "tv_popular")
                intent.putExtra("TITLE", "Popular Tv Series")
                val options = ActivityOptions.makeCustomAnimation(
                    context,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                startActivity(intent, options.toBundle())
            }

            showTvTopRatedImageView.setOnClickListener {
                val intent = Intent(context, MovieListActivity::class.java)
                intent.putExtra("TYPE", "tv_top_rated")
                intent.putExtra("TITLE", "Top Rated Tv Series")
                val options = ActivityOptions.makeCustomAnimation(
                    context,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                startActivity(intent, options.toBundle())
            }

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
        val bottomSheetFragment = tv.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "tv") }
        bottomSheetFragment?.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    override fun onTvOnTheAirClick(tv: TvOnTheAir) {
        val bottomSheetFragment = tv.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "tv") }
        bottomSheetFragment?.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    override fun onTvPopularClick(tv: TvPopular) {
        val bottomSheetFragment = tv.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "tv") }
        bottomSheetFragment?.show(parentFragmentManager, bottomSheetFragment.tag)
    }

    override fun onTvTopRatedClick(tv: TvTopRated) {
        val bottomSheetFragment = tv.id?.let { CustomBottomSheetDialogFragment.newInstance(it, "tv") }
        bottomSheetFragment?.show(parentFragmentManager, bottomSheetFragment.tag)
    }


}