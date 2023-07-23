package com.example.movieapp.views.home.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityNewsBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity() {



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}