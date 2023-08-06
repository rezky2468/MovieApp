package com.example.movieapp.views

import android.animation.ArgbEvaluator
import android.animation.ValueAnimator
import android.app.ActivityOptions
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.PopupMenu
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.viewmodels.MovieViewModel
import com.example.movieapp.views.home.ViewPagerAdapter
import com.example.movieapp.views.home.movies.MoviesFragment
import com.example.movieapp.views.home.tv.TvSeriesFragment
import com.example.movieapp.views.search.SearchActivity
import com.google.android.material.chip.Chip
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var isDarkMode = false
    private val viewModel: MovieViewModel by viewModels()

    private var currentFragment: Fragment? = null

    private val fragment1: Fragment = MoviesFragment()
    private val fragment2: Fragment = TvSeriesFragment()
    private val fragmentManager: FragmentManager = supportFragmentManager
    private var active: Fragment = fragment1
    private var isChip: Boolean = false
    private var isMovie: Boolean = false
    private var isTv: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(R.style.Theme_MovieApp)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        fragmentManager.beginTransaction().apply {
            add(R.id.fragment_container, fragment2, "2").hide(fragment2)
            add(R.id.fragment_container, fragment1, "1").commit()
        }

        binding.moviesChip.isChecked = true
        binding.chipGroup.setOnCheckedChangeListener { group, checkedId ->
            val selectedChip: Chip? = group.findViewById(checkedId)
            selectedChip?.let {
                when (it.id) {
                    R.id.movies_chip -> {
                        fragmentManager.beginTransaction().hide(active).show(fragment1).commit()
                        active = fragment1
                    }
                    R.id.tv_series_chip -> {
                        fragmentManager.beginTransaction().hide(active).show(fragment2).commit()
                        active = fragment2
                    }
                }
            }
        }

        binding.apply {
            profileImageView.setOnClickListener {
                showPopupMenu(it)
            }
            darkModeImageView.setOnClickListener {
//            toggleNightMode()
                Toast.makeText(this@MainActivity, "Coming soon!", Toast.LENGTH_SHORT).show()
            }
            btnn1.setOnClickListener {
                Toast.makeText(this@MainActivity, "Coming soon!", Toast.LENGTH_SHORT).show()
            }
            btnn2.setOnClickListener {
                Toast.makeText(this@MainActivity, "Coming soon!", Toast.LENGTH_SHORT).show()
            }
            btnn3.setOnClickListener {
                Toast.makeText(this@MainActivity, "Coming soon!", Toast.LENGTH_SHORT).show()
            }
        }

        val viewPagerAdapter = ViewPagerAdapter(supportFragmentManager, lifecycle)

        binding.apply {
//            viewPager.adapter = viewPagerAdapter
            searchButton.setOnClickListener {
                val intent = Intent(this@MainActivity, SearchActivity::class.java)
                val options = ActivityOptions.makeCustomAnimation(
                    this@MainActivity,
                    R.anim.fade_in,
                    R.anim.fade_out
                )
                startActivity(intent, options.toBundle())
            }

//            val fragments = listOf(MoviesFragment(), TvSeriesFragment())
//            viewPager.adapter = ViewPagerAdapter(this@MainActivity, fragments)
//            viewPager.isUserInputEnabled = false // Disable swipe
//            TabLayoutMediator(tabLayout, viewPager) { tab, position ->
//                when (position) {
//                    0 -> tab.text = "Movies"
//                    1 -> tab.text = "TV Series"
//                }
//            }.attach()

//            // Create the custom tab layout for each tab
//            val tabTitles = listOf("Movies", "TV Series") // Replace with your tab titles
//            val tabIcons = listOf(R.drawable.ic_share, R.drawable.ic_filter_list) // Replace with your tab icons
//
//            for (i in 0 until tabTitles.size) {
//                val tab = binding.tabLayout.newTab()
//                val customTabView = layoutInflater.inflate(R.layout.layout_tab_item, null)
//
//                val tabIcon = customTabView.findViewById<ImageView>(R.id.tabIcon)
//                val tabTitle = customTabView.findViewById<TextView>(R.id.tabTitle)
//
//                tabIcon.setImageResource(tabIcons[i])
//                tabTitle.text = tabTitles[i]
//
//                tab.customView = customTabView
//                binding.tabLayout.addTab(tab)
//            }


        }

    }

    private fun showPopupMenu(view: View) {
        val popupMenu = PopupMenu(this, view)
        popupMenu.inflate(R.menu.menu_options)

        popupMenu.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.action_profile -> {
                    Toast.makeText(this@MainActivity, "Coming soon!", Toast.LENGTH_SHORT).show()
//                    viewModel.requestToken.observe(this@MainActivity) {
//                        Toast.makeText(this, "Success!\nRequest Token: " + it.data?.requestToken + "\nExpires At: " + it.data?.expiresAt, Toast.LENGTH_LONG).show()
//                    }
                    true
                }

                R.id.action_settings -> {
                    Toast.makeText(this@MainActivity, "Coming soon!", Toast.LENGTH_SHORT).show()
                    true
                }

                R.id.action_about -> {
                    Toast.makeText(this@MainActivity, "Coming soon!", Toast.LENGTH_SHORT).show()
                    true
                }
                // Add more cases if needed
                else -> false
            }
        }

        popupMenu.show()
    }

    private fun toggleNightMode() {
        isDarkMode = !isDarkMode
        if (isDarkMode) {
            animateTransition(
                R.color.red,
                R.color.black
            ) // Replace with your desired dark mode colors
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
        } else {
            animateTransition(
                R.color.white,
                R.color.orange
            ) // Replace with your desired light mode colors
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }
    }

    private fun animateTransition(startColorResId: Int, endColorResId: Int) {
        val startColor = ContextCompat.getColor(this, startColorResId)
        val endColor = ContextCompat.getColor(this, endColorResId)

        val animator = ValueAnimator.ofObject(ArgbEvaluator(), startColor, endColor)
        animator.duration = 300 // Set the duration of the transition (in milliseconds)
        animator.addUpdateListener { valueAnimator ->
            val color = valueAnimator.animatedValue as Int
            val drawable = ContextCompat.getDrawable(this, R.drawable.circle_gradient)
            drawable?.setTint(color)
            // Set the drawable as the background of the view you want to transition
            // For example, if you have a View called `circleView`, you can set its background like this:
            // circleView.background = drawable
        }
        animator.start()
    }


    private class ViewPagerAdapter(
        fragmentActivity: FragmentActivity,
        private val fragments: List<Fragment>
    ) : FragmentStateAdapter(fragmentActivity) {

        override fun getItemCount(): Int = fragments.size

        override fun createFragment(position: Int): Fragment = fragments[position]

    }

}