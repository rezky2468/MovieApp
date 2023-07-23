package com.example.movieapp.views.home

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieapp.views.home.movies.MoviesFragment
import com.example.movieapp.views.home.tv.TvSeriesFragment

class ViewPagerAdapter(fragmentManager: FragmentManager, lifecycle: Lifecycle) : FragmentStateAdapter(fragmentManager, lifecycle) {

    private val NUM_TAB = 2

    override fun getItemCount(): Int {
        return NUM_TAB
    }

    override fun createFragment(position: Int): Fragment {
        return when(position) {
            0 -> MoviesFragment()
            1 -> TvSeriesFragment()
            else -> throw IllegalArgumentException("Invalid position: $position")
        }
    }
}