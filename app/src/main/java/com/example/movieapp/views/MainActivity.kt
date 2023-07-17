package com.example.movieapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityMainBinding
import com.example.movieapp.viewmodels.MovieViewModel
import com.example.movieapp.views.home.UpcomingMoviePreviewAdapter
import com.example.movieapp.views.home.NowPlayingPreviewAdapter
import com.example.movieapp.views.nowplaying.NowPlayingActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set the status bar color
        val window: Window = window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = getColor(R.color.red)

        val nowPlayingAdapter = NowPlayingPreviewAdapter()
        val upcomingPreviewAdapter = UpcomingMoviePreviewAdapter()

        binding.apply {

            recyclerView.adapter = nowPlayingAdapter
            recyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)

            upcomingRecyclerView.adapter = upcomingPreviewAdapter
            upcomingRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false)

            val snapHelper = LinearSnapHelper()
            snapHelper.attachToRecyclerView(recyclerView)

//            val snapOnScrollListener = SnapOnScrollListener(snapHelper, SnapOnScrollListener.Behavior.NOTIFY_ON_SCROLL_STATE_IDLE)
//            recyclerView.addOnScrollListener(snapOnScrollListener)

            nowShowingButton.setOnClickListener {
                val intent = Intent(this@MainActivity, NowPlayingActivity::class.java)
                startActivity(intent)
            }

        }

        viewModel.nowPlayingMovies.observe(this@MainActivity) { result ->
            nowPlayingAdapter.submitList(result.data)
        }

        viewModel.upcomingMovies.observe(this@MainActivity) { result ->
            upcomingPreviewAdapter.submitList(result.data)
        }

    }

//    // Snap RecyclerView
//
//    class SnapOnScrollListener(
//        private val snapHelper: SnapHelper,
//        private val behavior: Behavior = Behavior.NOTIFY_ON_SCROLL
//    ) : RecyclerView.OnScrollListener() {
//
//        enum class Behavior {
//            NOTIFY_ON_SCROLL,
//            NOTIFY_ON_SCROLL_STATE_IDLE
//        }
//
//        private var snapPosition = RecyclerView.NO_POSITION
//
//        override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//            if (behavior == Behavior.NOTIFY_ON_SCROLL) {
//                snapPosition = findSnapPosition(recyclerView.layoutManager as LinearLayoutManager, snapHelper)
//            }
//        }
//
//        override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
//            if (behavior == Behavior.NOTIFY_ON_SCROLL_STATE_IDLE && newState == RecyclerView.SCROLL_STATE_IDLE) {
//                snapPosition = findSnapPosition(recyclerView.layoutManager as LinearLayoutManager, snapHelper)
//            }
//        }
//
//        private fun findSnapPosition(layoutManager: LinearLayoutManager, snapHelper: SnapHelper): Int {
//            val snapView = snapHelper.findSnapView(layoutManager) ?: return RecyclerView.NO_POSITION
//            return layoutManager.getPosition(snapView)
//        }
//
//        fun getSnapPosition(): Int = snapPosition
//    }
}