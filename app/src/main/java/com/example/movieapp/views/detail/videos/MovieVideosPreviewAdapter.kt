package com.example.movieapp.views.detail.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.LayoutRecyclerViewVideosPreviewBinding
import com.example.movieapp.databinding.LayoutRecyclerViewVideosBinding
import com.example.movieapp.models.movies.Video
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer

class MovieVideosPreviewAdapter(
    private val listener: OnItemClickListener,
) : ListAdapter<Video, MovieVideosPreviewAdapter.VideosPreviewViewHolder>(
    VideosPreviewComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosPreviewViewHolder {
        val binding = LayoutRecyclerViewVideosPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VideosPreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideosPreviewViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class VideosPreviewViewHolder(
        private val binding: LayoutRecyclerViewVideosPreviewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(video: Video, listener: OnItemClickListener) {
            binding.apply {

                val thumbnail = "https://img.youtube.com/vi/" + video.key + "/0.jpg"


                itemView.setOnClickListener {
                    listener.onVideosPreviewClick(video)
                }

                Glide.with(itemView).load(thumbnail).into(thumbnailImageView)

            }
        }


    }

    class VideosPreviewComparator : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onVideosPreviewClick(video: Video)
    }

}