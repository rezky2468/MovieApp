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
import com.example.movieapp.models.movies.MovieTopRated

class TopRatedMovieAdapter(private val listener: OnItemClickListener? = null) :
    ListAdapter<MovieTopRated, TopRatedMovieAdapter.TopRatedMovieViewHolder>(TopRatedComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopRatedMovieViewHolder {
        val binding = LayoutRecyclerViewMoviePreviewWhiteBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TopRatedMovieViewHolder(binding, listener, this)
    }

    override fun onBindViewHolder(holder: TopRatedMovieViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem)
        }
    }

    class TopRatedMovieViewHolder(
        private val binding: LayoutRecyclerViewMoviePreviewWhiteBinding,
        private val listener: OnItemClickListener? = null,
        private val adapter: TopRatedMovieAdapter
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MovieTopRated) {
            binding.apply {
                Glide.with(itemView).load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                    .placeholder(R.drawable.bg_placeholder)
                    .into(imageView)
                textView.text = movie.title
            }
        }
        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    val movie = adapter.getItem(position)
                    movie?.let { listener?.onTopRatedClick(it) }
                }
            }
        }
    }

    class TopRatedComparator : DiffUtil.ItemCallback<MovieTopRated>() {
        override fun areItemsTheSame(oldItem: MovieTopRated, newItem: MovieTopRated): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieTopRated, newItem: MovieTopRated): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onTopRatedClick(movie: MovieTopRated)
    }

}