package com.example.movieapp.views.home.news

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.LayoutRecyclerViewNewsBinding
import com.example.movieapp.models.news.Articles

class NewsAdapter(private val listener: OnItemClickListener, private val activity: String) :
    ListAdapter<Articles, NewsAdapter.NewsViewHolder>(NewsComparator()) {

    private val maxItemsToShow = 5

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val binding = LayoutRecyclerViewNewsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NewsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    override fun submitList(list: List<Articles>?) {
        if (list != null) {
            if (activity == "detail") {
                super.submitList(list.take(5))
            } else {
                super.submitList(list)
            }
        } else {
            super.submitList(null)
        }
    }

    class NewsViewHolder(
        private val binding: LayoutRecyclerViewNewsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(articles: Articles, listener: OnItemClickListener) {

            binding.apply {
                Glide.with(itemView).load(articles.urlToImage).into(imageView)
                titleTextView.text = articles.title
                authorTextView.text = "by ${articles.author}"
                dateTextView.text = articles.publishedAt?.take(10)
                itemView.setOnClickListener {
                    listener.onNewsClick(articles)
                }
            }
        }
    }

    class NewsComparator : DiffUtil.ItemCallback<Articles>() {
        override fun areItemsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Articles, newItem: Articles): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onNewsClick(news: Articles)
    }

}