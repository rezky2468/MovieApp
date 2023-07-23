package com.example.movieapp.views

import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.views.detail.videos.VideosActivity
import com.example.movieapp.views.home.ViewPagerAdapter
import com.example.movieapp.views.search.SearchActivity
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val viewPagerAdapter= ViewPagerAdapter(supportFragmentManager, lifecycle)

        binding.apply {
            viewPager.adapter = viewPagerAdapter
            searchButton.setOnClickListener {
                val intent = Intent(this@MainActivity, SearchActivity::class.java)
                val options = ActivityOptions.makeCustomAnimation(
                    this@MainActivity,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                startActivity(intent, options.toBundle())
            }
            darkModeImageView.setOnClickListener {
                val intent = Intent(this@MainActivity, VideosActivity::class.java)
                startActivity(intent)
            }
            // Connect the TabLayout with the ViewPager2
            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
                when (position) {
                    0 -> tab.text = "Movies"
                    1 -> tab.text = "TV Series"
                }
            }.attach()
        }




    }

}