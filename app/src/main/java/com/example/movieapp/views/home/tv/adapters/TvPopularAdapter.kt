package com.example.movieapp.views.home.tv.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.databinding.LayoutRecyclerViewMoviePreviewBinding
import com.example.movieapp.databinding.LayoutRecyclerViewMoviePreviewWhiteBinding
import com.example.movieapp.models.tv.TvPopular

class TvPopularAdapter(private val listener: OnItemClickListener) :
    ListAdapter<TvPopular, TvPopularAdapter.TvPopularViewHolder>(TvPopularComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvPopularViewHolder {
        val binding = LayoutRecyclerViewMoviePreviewWhiteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvPopularViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvPopularViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class TvPopularViewHolder(private val binding: LayoutRecyclerViewMoviePreviewWhiteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvPopular, listener: OnItemClickListener) {
            binding.apply {
                Glide.with(itemView).load(BuildConfig.TMDB_PHOTO_BASE_URL + tv.posterPath)
                    .placeholder(R.drawable.bg_placeholder)
                    .into(imageView)
                textView.text = tv.name
                itemView.setOnClickListener {
                    listener.onTvPopularClick(tv)
                }
            }
        }
    }

    class TvPopularComparator : DiffUtil.ItemCallback<TvPopular>() {
        override fun areItemsTheSame(oldItem: TvPopular, newItem: TvPopular): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvPopular, newItem: TvPopular): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onTvPopularClick(tv: TvPopular)
    }

}