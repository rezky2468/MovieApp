package com.example.movieapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.LayoutRecyclerViewMovieListBinding
import com.example.movieapp.databinding.LayoutRecyclerViewMovieSearchBinding
import com.example.movieapp.models.movies.Movie
import com.example.movieapp.models.movies.MovieBackdrop
import com.example.movieapp.models.movies.MovieNowPlaying
import com.example.movieapp.models.movies.MoviePopular
import com.example.movieapp.models.movies.MovieTopRated
import com.example.movieapp.models.movies.MovieUpcoming
import com.example.movieapp.models.tv.TvAiringToday
import com.example.movieapp.models.tv.TvOnTheAir
import com.example.movieapp.models.tv.TvPopular
import com.example.movieapp.models.tv.TvSeries
import com.example.movieapp.models.tv.TvSeriesBackdrop
import com.example.movieapp.models.tv.TvTopRated

class MovieListAdapter<T : Any>(private val listener: OnItemClickListener<T>) :
    ListAdapter<T, MovieListAdapter<T>.MovieListViewHolder>(
        MovieListComparator<T>()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding = LayoutRecyclerViewMovieListBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieListViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    inner class MovieListViewHolder(
        private val binding: LayoutRecyclerViewMovieListBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: T, listener: OnItemClickListener<T>) {
            binding.apply {
                when (movie) {
                    is MovieNowPlaying -> {
                        Glide.with(itemView)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                            .placeholder(R.drawable.bg_placeholder)
                            .into(imageView)
                        textView.text = movie.title
                        itemView.setOnClickListener {
                            listener.onMovieNowPlayingClick(movie)
                        }
                    }
                    is MovieUpcoming -> {
                        Glide.with(itemView)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                            .placeholder(R.drawable.bg_placeholder)
                            .into(imageView)
                        textView.text = movie.title
                        itemView.setOnClickListener {
                            listener.onMovieNowPlayingClick(movie)
                        }
                    }
                    is MoviePopular -> {
                        Glide.with(itemView)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                            .placeholder(R.drawable.bg_placeholder)
                            .into(imageView)
                        textView.text = movie.title
                        itemView.setOnClickListener {
                            listener.onMovieNowPlayingClick(movie)
                        }
                    }
                    is MovieTopRated -> {
                        Glide.with(itemView)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                            .placeholder(R.drawable.bg_placeholder)
                            .into(imageView)
                        textView.text = movie.title
                        itemView.setOnClickListener {
                            listener.onMovieNowPlayingClick(movie)
                        }
                    }
                    is TvAiringToday -> {
                        Glide.with(itemView)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                            .placeholder(R.drawable.bg_placeholder)
                            .into(imageView)
                        textView.text = movie.name
                        itemView.setOnClickListener {
                            listener.onMovieNowPlayingClick(movie)
                        }
                    }
                    is TvOnTheAir -> {
                        Glide.with(itemView)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                            .placeholder(R.drawable.bg_placeholder)
                            .into(imageView)
                        textView.text = movie.name
                        itemView.setOnClickListener {
                            listener.onMovieNowPlayingClick(movie)
                        }
                    }
                    is TvPopular -> {
                        Glide.with(itemView)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                            .placeholder(R.drawable.bg_placeholder)
                            .into(imageView)
                        textView.text = movie.name
                        itemView.setOnClickListener {
                            listener.onMovieNowPlayingClick(movie)
                        }
                    }
                    is TvTopRated -> {
                        Glide.with(itemView)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                            .placeholder(R.drawable.bg_placeholder)
                            .into(imageView)
                        textView.text = movie.name
                        itemView.setOnClickListener {
                            listener.onMovieNowPlayingClick(movie)
                        }
                    }
                    is Movie -> {
                        Glide.with(itemView)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                            .placeholder(R.drawable.bg_placeholder)
                            .into(imageView)
                        textView.text = movie.title
                        itemView.setOnClickListener {
                            listener.onMovieNowPlayingClick(movie)
                        }
                    }
                    is TvSeries -> {
                        Glide.with(itemView)
                            .load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                            .placeholder(R.drawable.bg_placeholder)
                            .into(imageView)
                        textView.text = movie.name
                        itemView.setOnClickListener {
                            listener.onMovieNowPlayingClick(movie)
                        }
                    }
                }

            }
        }
    }

    class MovieListComparator<T : Any> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            if (oldItem is MovieNowPlaying && newItem is MovieNowPlaying) {
                return oldItem.id == newItem.id
            } else if (oldItem is MovieUpcoming && newItem is MovieUpcoming) {
                return oldItem.id == newItem.id
            } else if (oldItem is MoviePopular && newItem is MoviePopular) {
                return oldItem.id == newItem.id
            } else if (oldItem is MovieTopRated && newItem is MovieTopRated) {
                return oldItem.id == newItem.id
            } else if (oldItem is TvAiringToday && newItem is TvAiringToday) {
                return oldItem.id == newItem.id
            } else if (oldItem is TvOnTheAir && newItem is TvOnTheAir) {
                return oldItem.id == newItem.id
            } else if (oldItem is TvPopular && newItem is TvPopular) {
                return oldItem.id == newItem.id
            } else if (oldItem is TvTopRated && newItem is TvTopRated) {
                return oldItem.id == newItem.id
            } else if (oldItem is Movie && newItem is Movie) {
                return oldItem.id == newItem.id
            } else if (oldItem is TvSeries && newItem is TvSeries) {
                return oldItem.id == newItem.id
            }
            return false
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            if (oldItem is MovieNowPlaying && newItem is MovieNowPlaying) {
                return oldItem == newItem
            } else if (oldItem is MovieUpcoming && newItem is MovieUpcoming) {
                return oldItem == newItem
            } else if (oldItem is MoviePopular && newItem is MoviePopular) {
                return oldItem == newItem
            } else if (oldItem is MovieTopRated && newItem is MovieTopRated) {
                return oldItem == newItem
            } else if (oldItem is TvAiringToday && newItem is TvAiringToday) {
                return oldItem == newItem
            } else if (oldItem is TvOnTheAir && newItem is TvOnTheAir) {
                return oldItem == newItem
            } else if (oldItem is TvPopular && newItem is TvPopular) {
                return oldItem == newItem
            } else if (oldItem is TvTopRated && newItem is TvTopRated) {
                return oldItem == newItem
            } else if (oldItem is Movie && newItem is Movie) {
                return oldItem == newItem
            } else if (oldItem is TvSeries && newItem is TvSeries) {
                return oldItem == newItem
            }
            return false
        }
    }

    interface OnItemClickListener<T> {
        fun onMovieNowPlayingClick(nowPlaying: T)
    }

}