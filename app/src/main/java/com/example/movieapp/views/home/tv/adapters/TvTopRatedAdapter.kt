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
import com.example.movieapp.models.tv.TvTopRated

class TvTopRatedAdapter(private val listener: OnItemClickListener) :
    ListAdapter<TvTopRated, TvTopRatedAdapter.TvTopRatedViewHolder>(TvTopRatedComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvTopRatedViewHolder {
        val binding = LayoutRecyclerViewMoviePreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvTopRatedViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvTopRatedViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class TvTopRatedViewHolder(private val binding: LayoutRecyclerViewMoviePreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvTopRated, listener: OnItemClickListener) {
            binding.apply {
                Glide.with(itemView).load(BuildConfig.TMDB_PHOTO_BASE_URL + tv.posterPath)
                    .placeholder(R.drawable.bg_placeholder)
                    .into(imageView)
                textView.text = tv.name
                itemView.setOnClickListener {
                    listener.onTvTopRatedClick(tv)
                }
            }
        }
    }

    class TvTopRatedComparator : DiffUtil.ItemCallback<TvTopRated>() {
        override fun areItemsTheSame(oldItem: TvTopRated, newItem: TvTopRated): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvTopRated, newItem: TvTopRated): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onTvTopRatedClick(tv: TvTopRated)
    }

}