package com.example.movieapp.views.detail.backdrops

import android.os.Bundle
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityPhotoViewBinding


class PhotoViewActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityPhotoViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.navigationBarColor = resources.getColor(R.color.black)

        val title = intent.getStringExtra("TITLE") ?: ""

        val url = BuildConfig.TMDB_PHOTO_BASE_URL + intent.getStringExtra("PHOTO_URL")
        Glide.with(this).load(url).into(binding.photoView)
        binding.titleLabel.text = title

        binding.backImageView.setOnClickListener {
            super.onBackPressed()
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    private fun getStatusBarHeight(): Int {
        var result = 0
        val resourceId = resources.getIdentifier("status_bar_height", "dimen", "android")
        if (resourceId > 0) {
            result = resources.getDimensionPixelSize(resourceId)
        }
        return result
    }

}