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
import com.example.movieapp.models.tv.TvSeries

class TvSeriesPreviewAdapter(private val listener: OnItemClickListener) :
    ListAdapter<TvSeries, TvSeriesPreviewAdapter.TvSeriesPreviewViewHolder>(
        TvSeriesPreviewComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesPreviewViewHolder {
        val binding = LayoutRecyclerViewMoviePreviewWhiteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvSeriesPreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvSeriesPreviewViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class TvSeriesPreviewViewHolder(
        private val binding: LayoutRecyclerViewMoviePreviewWhiteBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvSeries, listener: OnItemClickListener) {
            binding.apply {
                Glide.with(itemView)
                    .load(BuildConfig.TMDB_PHOTO_BASE_URL + tv.posterPath)
                    .placeholder(R.drawable.bg_placeholder)
                    .into(imageView)
                textView.text = tv.name
                itemView.setOnClickListener {
                    listener.onTvSeriesClick(tv)
                }
            }
        }
    }

    class TvSeriesPreviewComparator : DiffUtil.ItemCallback<TvSeries>() {
        override fun areItemsTheSame(oldItem: TvSeries, newItem: TvSeries): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: TvSeries,
            newItem: TvSeries
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onTvSeriesClick(tv: TvSeries)
    }

}