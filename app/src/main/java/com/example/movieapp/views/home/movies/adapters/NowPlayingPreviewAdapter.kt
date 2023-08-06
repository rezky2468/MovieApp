package com.example.movieapp.views.home.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.databinding.LayoutRecyclerViewMoviePreviewWhiteBinding
import com.example.movieapp.models.movies.MovieNowPlaying
import com.facebook.shimmer.ShimmerFrameLayout

class NowPlayingPreviewAdapter(private val listener: OnItemClickListener) :
    ListAdapter<MovieNowPlaying, NowPlayingPreviewAdapter.NowPlayingPreviewViewHolder>(
        NowPlayingPreviewComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingPreviewViewHolder {
        val binding = LayoutRecyclerViewMoviePreviewWhiteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return NowPlayingPreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NowPlayingPreviewViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class NowPlayingPreviewViewHolder(
        private val binding: LayoutRecyclerViewMoviePreviewWhiteBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieNowPlaying, listener: OnItemClickListener) {
            binding.apply {
                Glide.with(itemView)
                    .load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                    .placeholder(R.drawable.bg_placeholder)
                    .into(imageView)
                textView.text = movie.title
                itemView.setOnClickListener {
                    listener.onNowPlayingClick(movie)
                }
            }
        }
    }

    class NowPlayingPreviewComparator : DiffUtil.ItemCallback<MovieNowPlaying>() {
        override fun areItemsTheSame(oldItem: MovieNowPlaying, newItem: MovieNowPlaying): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: MovieNowPlaying,
            newItem: MovieNowPlaying
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onNowPlayingClick(nowPlaying: MovieNowPlaying)
    }

}