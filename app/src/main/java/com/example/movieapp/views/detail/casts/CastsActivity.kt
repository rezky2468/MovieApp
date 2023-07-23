package com.example.movieapp.views.detail.casts

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.movieapp.databinding.ActivityCastsBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView


class CastsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityCastsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
//
//            getLifecycle().addObserver(youtubePlayerView)

            youtubePlayerView.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    super.onReady(youTubePlayer)
                    val key = "RzkD_rTEBYs"
                    youTubePlayer.loadVideo(key,0f)
                }
            })

        }
    }
}