package com.example.movieapp.views.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.databinding.LayoutRecyclerViewMoviePreviewBinding
import com.example.movieapp.models.UpcomingMovie

class UpcomingMoviePreviewAdapter :
    ListAdapter<UpcomingMovie, UpcomingMoviePreviewAdapter.MoviePreviewViewHolder>(MoviePreviewComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePreviewViewHolder {
        val binding = LayoutRecyclerViewMoviePreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MoviePreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviePreviewViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class MoviePreviewViewHolder(private val binding: LayoutRecyclerViewMoviePreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: UpcomingMovie) {
            binding.apply {
                Glide.with(itemView)
                    .load(BuildConfig.PHOTO_BASE_URL + movie.posterPath)
                    .into(imageView)
                textView.text = movie.title
            }
        }
    }

    class MoviePreviewComparator : DiffUtil.ItemCallback<UpcomingMovie>() {
        override fun areItemsTheSame(oldItem: UpcomingMovie, newItem: UpcomingMovie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: UpcomingMovie, newItem: UpcomingMovie): Boolean {
            return oldItem == newItem
        }
    }

}