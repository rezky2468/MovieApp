package com.example.movieapp.views.home.tv.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.databinding.LayoutRecyclerViewMoviePreviewWhiteBinding
import com.example.movieapp.models.tv.TvAiringToday

class TvAiringTodayAdapter(private val listener: OnItemClickListener) :
    ListAdapter<TvAiringToday, TvAiringTodayAdapter.TvTodayAiring>(TodayAiringComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvTodayAiring {
        val binding = LayoutRecyclerViewMoviePreviewWhiteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvTodayAiring(binding)
    }

    override fun onBindViewHolder(holder: TvTodayAiring, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class TvTodayAiring(private val binding: LayoutRecyclerViewMoviePreviewWhiteBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvAiringToday, listener: OnItemClickListener) {
            binding.apply {
                Glide.with(itemView).load(BuildConfig.TMDB_PHOTO_BASE_URL + tv.posterPath)
                    .placeholder(R.drawable.bg_placeholder)
                    .into(imageView)
                textView.text = tv.name
                itemView.setOnClickListener {
                    listener.onTvAiringTodayClick(tv)
                }
            }
        }
    }

    class TodayAiringComparator : DiffUtil.ItemCallback<TvAiringToday>() {
        override fun areItemsTheSame(oldItem: TvAiringToday, newItem: TvAiringToday): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvAiringToday, newItem: TvAiringToday): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onTvAiringTodayClick(tv: TvAiringToday)
    }

}