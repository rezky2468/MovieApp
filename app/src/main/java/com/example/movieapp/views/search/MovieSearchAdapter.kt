package com.example.movieapp.views.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.databinding.LayoutRecyclerViewMovieSearchBinding
import com.example.movieapp.models.movies.MovieSearch

class MovieSearchAdapter(private val listener: OnItemClickListener? = null) :
    ListAdapter<MovieSearch, MovieSearchAdapter.MovieSearchViewHolder>(MovieSearchComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieSearchViewHolder {
        val binding = LayoutRecyclerViewMovieSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieSearchViewHolder(binding, listener, this)
    }

    override fun onBindViewHolder(holder: MovieSearchViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class MovieSearchViewHolder(
        private val binding: LayoutRecyclerViewMovieSearchBinding,
        private val listener: OnItemClickListener? = null,
        private val adapter: MovieSearchAdapter
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieSearch) {
            binding.apply {
                Glide.with(itemView).load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                    .into(imageView)
                textView.text = movie.title
            }
        }
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val movie = adapter.getItem(position)
                    movie?.let { listener?.onMovieSearchClick(it) }
                }
            }
        }
    }

    class MovieSearchComparator : DiffUtil.ItemCallback<MovieSearch>() {
        override fun areItemsTheSame(oldItem: MovieSearch, newItem: MovieSearch): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieSearch, newItem: MovieSearch): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onMovieSearchClick(movie: MovieSearch)
    }

}