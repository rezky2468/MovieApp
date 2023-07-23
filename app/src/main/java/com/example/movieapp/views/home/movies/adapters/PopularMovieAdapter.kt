package com.example.movieapp.views.home.movies.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.databinding.LayoutRecyclerViewMoviePreviewBinding
import com.example.movieapp.models.movies.MoviePopular

class PopularMovieAdapter(private val listener: OnItemClickListener? = null) :
    ListAdapter<MoviePopular, PopularMovieAdapter.PopularMovieViewHolder>(PopularMovieComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PopularMovieViewHolder {
        val binding = LayoutRecyclerViewMoviePreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return PopularMovieViewHolder(binding, listener, this)
    }

    override fun onBindViewHolder(holder: PopularMovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class PopularMovieViewHolder(
        private val binding: LayoutRecyclerViewMoviePreviewBinding,
        private val listener: OnItemClickListener? = null,
        private val adapter: PopularMovieAdapter
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MoviePopular) {
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
                    movie?.let { listener?.onPopularClick(it) }
                }
            }
        }
    }

    class PopularMovieComparator : DiffUtil.ItemCallback<MoviePopular>() {
        override fun areItemsTheSame(oldItem: MoviePopular, newItem: MoviePopular): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MoviePopular, newItem: MoviePopular): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onPopularClick(movie: MoviePopular)
    }

}