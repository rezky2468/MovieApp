package com.example.movieapp.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.Toast
import androidx.activity.viewModels
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityWebViewBinding
import com.example.movieapp.utils.Resource
import com.example.movieapp.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Exception

@AndroidEntryPoint
class WebViewActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityWebViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val callback = intent.getStringExtra("authentication_id")
        callback?.let { binding.webView.loadUrl(it) }
        binding.webView.webViewClient = object : WebViewClient() {
            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                // Check if the current URL matches the callback URL or contains a specific query parameter indicating successful authentication
                if (url == callback || url?.contains("success=true") == true) {
                    // Authentication completed successfully
                    // You can extract any necessary data from the URL or take appropriate actions
                }
            }
        }

    }
}