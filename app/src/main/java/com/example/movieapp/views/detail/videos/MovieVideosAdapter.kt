package com.example.movieapp.views.detail.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.LayoutRecyclerViewVideosBinding
import com.example.movieapp.models.movies.MovieVideo


class MovieVideosAdapter(
    private val listener: OnItemClickListener,
) : ListAdapter<MovieVideo, MovieVideosAdapter.MovieVideosViewHolder>(
    MovieVideosComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVideosViewHolder {
        val binding = LayoutRecyclerViewVideosBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieVideosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieVideosViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class MovieVideosViewHolder(
        private val binding: LayoutRecyclerViewVideosBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(video: MovieVideo, listener: OnItemClickListener) {
            binding.apply {

                val thumbnail = "https://img.youtube.com/vi/" + video.key + "/0.jpg"

                Glide.with(itemView).load(thumbnail)
                    .placeholder(R.drawable.bg_placeholder).into(thumbnailImageView)
                titleTextView.text = video.name
                typeTextView.text = video.type + " · " + video.publishedAt?.take(10)

                itemView.setOnClickListener {
                    listener.onVideosClick(video)
                }

            }
        }

    }

    class MovieVideosComparator : DiffUtil.ItemCallback<MovieVideo>() {
        override fun areItemsTheSame(oldItem: MovieVideo, newItem: MovieVideo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieVideo, newItem: MovieVideo): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onVideosClick(video: MovieVideo)
    }

}