package com.example.movieapp.views.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.databinding.LayoutRecyclerViewBinding
import com.example.movieapp.databinding.LayoutRecyclerViewNowShowingBinding
import com.example.movieapp.models.Movie

class NowPlayingPreviewAdapter : ListAdapter<Movie, NowPlayingPreviewAdapter.NowPlayingPreviewViewHolder>(NowPlayingPreviewComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NowPlayingPreviewViewHolder {
        val binding = LayoutRecyclerViewNowShowingBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return NowPlayingPreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: NowPlayingPreviewViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class NowPlayingPreviewViewHolder(private val binding: LayoutRecyclerViewNowShowingBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.apply {
                Glide.with(itemView)
                    .load(BuildConfig.PHOTO_BASE_URL + movie.posterPath)
                    .into(imageView)
            }
        }
    }

    class NowPlayingPreviewComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem == newItem
        }

    }



}