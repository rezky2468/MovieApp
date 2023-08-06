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
import com.example.movieapp.models.movies.Movie

class MoviePreviewAdapter(private val listener: OnItemClickListener) :
    ListAdapter<Movie, MoviePreviewAdapter.MoviePreviewViewHolder>(
        MoviePreviewComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePreviewViewHolder {
        val binding = LayoutRecyclerViewMoviePreviewWhiteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MoviePreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviePreviewViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class MoviePreviewViewHolder(
        private val binding: LayoutRecyclerViewMoviePreviewWhiteBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie, listener: OnItemClickListener) {
            binding.apply {
                Glide.with(itemView)
                    .load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                    .placeholder(R.drawable.bg_placeholder)
                    .into(imageView)
                textView.text = movie.title
                itemView.setOnClickListener {
                    listener.onMovieClick(movie)
                }
            }
        }
    }

    class MoviePreviewComparator : DiffUtil.ItemCallback<Movie>() {
        override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: Movie,
            newItem: Movie
        ): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onMovieClick(movie: Movie)
    }

}