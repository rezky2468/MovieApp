package com.example.movieapp.views.home.news

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.R
import com.example.movieapp.databinding.ActivityNewsBinding
import com.example.movieapp.models.news.Articles
import com.example.movieapp.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class NewsActivity : AppCompatActivity(), NewsAdapter.OnItemClickListener {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val adapter = NewsAdapter(this, "")

        binding.apply {
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this@NewsActivity, LinearLayoutManager.VERTICAL, false)
            backImageView.setOnClickListener {
                super.onBackPressed()
                overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
            }
        }

        viewModel.fetchNewsByRelevancy(100).observe(this@NewsActivity) {
            adapter.submitList(it.data)
        }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        overridePendingTransition(R.anim.fade_in, R.anim.fade_out)
    }

    override fun onNewsClick(news: Articles) {
        val bottomSheetFragment = news.url?.let { NewsBottomSheet.newInstance(it) }
        bottomSheetFragment?.show(supportFragmentManager, bottomSheetFragment.tag)
    }
}