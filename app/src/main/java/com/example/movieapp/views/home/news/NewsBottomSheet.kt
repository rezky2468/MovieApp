package com.example.movieapp.views.home.news

import android.graphics.Bitmap
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import com.example.movieapp.R
import com.example.movieapp.databinding.BottomSheetNewsBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class NewsBottomSheet : BottomSheetDialogFragment() {

    private lateinit var binding: BottomSheetNewsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(STYLE_NORMAL, R.style.AppBottomSheetDialogTheme)
    }

    companion object {
        private val ARG_NEWS_URL = "newsUrl"
        fun newInstance(newsUrl: String): NewsBottomSheet {
            val fragment = NewsBottomSheet()
            val args = Bundle()
            args.putString(ARG_NEWS_URL, newsUrl)
            fragment.arguments = args
            return fragment
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetNewsBinding.inflate(inflater, container, false)

        val url = arguments?.getString(ARG_NEWS_URL).toString()

        binding.apply {
            webView.webViewClient = MyWebViewClient()
            webView.loadUrl(url)
        }

        return binding.root
    }

    private inner class MyWebViewClient : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)
            // Web page loading started
            // You can show loading indicators or perform other actions here
            binding.progressBar.visibility = View.VISIBLE
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
            // Web page loading finished
            // You can hide loading indicators or perform other actions here
            binding.progressBar.visibility = View.GONE
        }
    }

}