package com.example.movieapp.views.home.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityNewsDetailBinding
import com.example.movieapp.models.news.Articles

class NewsDetailActivity : AppCompatActivity() {

    private lateinit var binding: ActivityNewsDetailBinding
    private lateinit var slideUpAnimation: Animation
    private lateinit var slideDownAnimation: Animation

    var url = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNewsDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        val window: Window = window
//        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)

        // Load the slide-up animation from the XML resource
        slideUpAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_up)
        slideDownAnimation = AnimationUtils.loadAnimation(this, R.anim.slide_down)

        val news = intent.getParcelableExtra<Articles>("DATA_NEWS")
        if (news != null) {
            binding.apply {
                Glide.with(this@NewsDetailActivity).load(news.urlToImage).into(imageView)
                sourceTextView.text = "Source: " + (news.url ?: "-")
                titleTextView.text = news.title ?: "-"
                authorTextView.text = "by " + (news.author ?: "-")
                dateTextView.text = news.publishedAt?.take(10)
                contentTextView.text = news.content
                url = news.url
            }
        }

        binding.apply {
            backImageView.setOnClickListener {
                super.onBackPressed()
            }
            shareImageView.setOnClickListener {
                Toast.makeText(this@NewsDetailActivity, "Clicked", Toast.LENGTH_LONG).show()
            }
            showMoreTextView.setOnClickListener {
                if (linearLayout.visibility != View.VISIBLE) {
                    webView.loadUrl(url) // Replace with the URL you want to load
                    webView.visibility = View.VISIBLE
                    linearLayout.visibility = View.VISIBLE
                    linearLayout.startAnimation(slideUpAnimation)
                }
            }
        }
    }

    override fun onBackPressed() {
        if (binding.linearLayout.visibility == View.VISIBLE) {
            binding.linearLayout.visibility = View.INVISIBLE
            binding.webView.visibility = View.INVISIBLE
            binding.linearLayout.startAnimation(slideDownAnimation)
        } else {
            super.onBackPressed()
        }
    }
}