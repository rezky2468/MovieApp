package com.example.movieapp.views.search

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.databinding.LayoutRecyclerViewMovieSearchBinding
import com.example.movieapp.models.MultiSearch

class MultiSearchAdapter(private val listener: OnItemClickListener) :
    ListAdapter<MultiSearch, MultiSearchAdapter.MultiSearchViewHolder>(MultiSearchComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MultiSearchViewHolder {
        val binding = LayoutRecyclerViewMovieSearchBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MultiSearchViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MultiSearchViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class MultiSearchViewHolder(
        private val binding: LayoutRecyclerViewMovieSearchBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: MultiSearch, listener: OnItemClickListener) {
            binding.apply {
                Glide.with(itemView).load(BuildConfig.TMDB_PHOTO_BASE_URL + movie.posterPath)
                    .into(imageView)
                textView.text = movie.name?: movie.title
                itemView.setOnClickListener {
                    listener.onMultiSearchClick(movie)
                }
            }
        }
    }

    class MultiSearchComparator : DiffUtil.ItemCallback<MultiSearch>() {
        override fun areItemsTheSame(oldItem: MultiSearch, newItem: MultiSearch): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MultiSearch, newItem: MultiSearch): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onMultiSearchClick(movie: MultiSearch)
    }

}