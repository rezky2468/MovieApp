package com.example.movieapp.views.detail.backdrops

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.databinding.LayoutRecyclerViewBackdropsBinding
import com.example.movieapp.models.movies.MovieBackdrop
import com.example.movieapp.models.tv.TvSeriesBackdrop
import com.example.movieapp.utils.Resource
import com.facebook.shimmer.Shimmer
import com.facebook.shimmer.ShimmerDrawable
import com.facebook.shimmer.ShimmerFrameLayout

class MovieBackdropsAdapter<T>(
    private val listener: OnItemClickListener<T>,
    private val activity: String,
) :
    ListAdapter<T, MovieBackdropsAdapter<T>.MovieBackdropsViewHolder>(
        MovieBackdropsComparator<T>()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieBackdropsViewHolder {
        val binding = LayoutRecyclerViewBackdropsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieBackdropsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieBackdropsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    override fun submitList(list: List<T>?) {
        if (list != null) {
            if (activity == "detail") {
                super.submitList(list.take(6))
            } else {
                super.submitList(list)
            }
        } else {
            super.submitList(null)
        }
    }

    inner class MovieBackdropsViewHolder(
        private val binding: LayoutRecyclerViewBackdropsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(backdrops: T, listener: OnItemClickListener<T>) {
            binding.apply {
                when (backdrops) {
                    is MovieBackdrop -> {
                        Glide.with(itemView)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + backdrops.filePath)
                            .placeholder(R.drawable.bg_placeholder)
                            .into(imageView)
                        itemView.setOnClickListener {
                            listener.onBackdropsClick(backdrops)
                        }
                    }

                    is TvSeriesBackdrop -> {
                        Glide.with(itemView)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + backdrops.filePath)
                            .placeholder(R.drawable.bg_placeholder)
                            .into(imageView)
                        itemView.setOnClickListener {
                            listener.onBackdropsClick(backdrops)
                        }
                    }

                    else -> {

                    }
                }
            }
        }
    }

    class MovieBackdropsComparator<T> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            if (oldItem is MovieBackdrop && newItem is MovieBackdrop) {
                return oldItem.filePath == newItem.filePath
            } else if (oldItem is TvSeriesBackdrop && newItem is TvSeriesBackdrop) {
                return oldItem.filePath == newItem.filePath
            }
            return false
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            if (oldItem is MovieBackdrop && newItem is MovieBackdrop) {
                return oldItem == newItem
            } else if (oldItem is TvSeriesBackdrop && newItem is TvSeriesBackdrop) {
                return oldItem == newItem
            }
            return false
        }

    }

    interface OnItemClickListener<T> {
        fun onBackdropsClick(backdrops: T)
    }

}