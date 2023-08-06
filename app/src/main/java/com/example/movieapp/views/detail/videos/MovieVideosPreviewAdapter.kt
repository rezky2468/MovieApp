package com.example.movieapp.views.detail.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.LayoutRecyclerViewVideosPreviewBinding
import com.example.movieapp.models.movies.MovieVideo

class MovieVideosPreviewAdapter(
    private val listener: OnItemClickListener, private val activity: String
) : ListAdapter<MovieVideo, MovieVideosPreviewAdapter.MovieVideosPreviewViewHolder>(
    MovieVideosPreviewComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieVideosPreviewViewHolder {
        val binding = LayoutRecyclerViewVideosPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieVideosPreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieVideosPreviewViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    override fun submitList(list: List<MovieVideo>?) {
        if (list != null) {
            if (activity == "detail") {
                super.submitList(list.take(6))
            } else if (activity == "full") {
                submitList(list)
            }
        } else {
            super.submitList(null)
        }
    }

    class MovieVideosPreviewViewHolder(
        private val binding: LayoutRecyclerViewVideosPreviewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(video: MovieVideo, listener: OnItemClickListener) {
            binding.apply {

                val thumbnail = "https://img.youtube.com/vi/" + video.key + "/0.jpg"


                itemView.setOnClickListener {
                    listener.onVideosPreviewClick(video)
                }

                Glide.with(itemView).load(thumbnail)
                    .placeholder(R.drawable.bg_placeholder).into(thumbnailImageView)

            }
        }


    }

    class MovieVideosPreviewComparator : DiffUtil.ItemCallback<MovieVideo>() {
        override fun areItemsTheSame(oldItem: MovieVideo, newItem: MovieVideo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MovieVideo, newItem: MovieVideo): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onVideosPreviewClick(video: MovieVideo)
    }

}