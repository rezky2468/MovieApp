package com.example.movieapp.views.detail.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.LayoutRecyclerViewVideosBinding
import com.example.movieapp.models.tv.TvVideo

class TvVideosAdapter(
    private val listener: OnItemClickListener,
) : ListAdapter<TvVideo, TvVideosAdapter.TvVideosViewHolder>(
    TvVideosComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvVideosViewHolder {
        val binding = LayoutRecyclerViewVideosBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvVideosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvVideosViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class TvVideosViewHolder(
        private val binding: LayoutRecyclerViewVideosBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(video: TvVideo, listener: OnItemClickListener) {
            binding.apply {

                val thumbnail = "https://img.youtube.com/vi/" + video.key + "/0.jpg"

                titleTextView.text = video.name
                typeTextView.text = video.type + " · " + video.publishedAt?.take(10)

                itemView.setOnClickListener {
                    listener.onTvVideosClick(video)
                }

                Glide.with(itemView).load(thumbnail).into(thumbnailImageView)

            }
        }

    }

    class TvVideosComparator : DiffUtil.ItemCallback<TvVideo>() {
        override fun areItemsTheSame(oldItem: TvVideo, newItem: TvVideo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvVideo, newItem: TvVideo): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onTvVideosClick(video: TvVideo)
    }

}