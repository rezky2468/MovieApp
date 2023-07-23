package com.example.movieapp.views.home.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.databinding.LayoutRecyclerViewMoviePreviewBinding
import com.example.movieapp.models.movies.MovieUpcoming

class UpcomingMoviePreviewAdapter(private val listener: OnItemClickListener? = null) :
    ListAdapter<MovieUpcoming, UpcomingMoviePreviewAdapter.MoviePreviewViewHolder>(
        MoviePreviewComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoviePreviewViewHolder {
        val binding = LayoutRecyclerViewMoviePreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MoviePreviewViewHolder(binding, listener, this)
    }

    override fun onBindViewHolder(holder: MoviePreviewViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class MoviePreviewViewHolder(
        private val binding: LayoutRecyclerViewMoviePreviewBinding,
        private val listener: OnItemClickListener?,
        private val adapter: UpcomingMoviePreviewAdapter
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieUpcoming) {
            binding.apply {
                Glide.with(itemView)
                    .load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                    .into(imageView)
                textView.text = movie.title
            }
        }

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val movie = adapter.getItem(position)
                    movie?.let { listener?.onUpcomingClick(it) }
                }
            }
        }
    }

    class MoviePreviewComparator : DiffUtil.ItemCallback<MovieUpcoming>() {
        override fun areItemsTheSame(oldItem: MovieUpcoming, newItem: MovieUpcoming): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieUpcoming, newItem: MovieUpcoming): Boolean {
            return oldItem == newItem
        }
    }

    interface OnItemClickListener {
        fun onUpcomingClick(movie: MovieUpcoming)
    }

}

