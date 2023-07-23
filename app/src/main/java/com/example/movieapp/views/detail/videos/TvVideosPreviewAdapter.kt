package com.example.movieapp.views.detail.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.LayoutRecyclerViewVideosPreviewBinding
import com.example.movieapp.databinding.LayoutRecyclerViewVideosBinding
import com.example.movieapp.models.tv.TvVideo

class TvVideosPreviewAdapter(
    private val listener: OnItemClickListener,
) : ListAdapter<TvVideo, TvVideosPreviewAdapter.TvVideosPreviewViewHolder>(
    TvVideosPreviewComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvVideosPreviewViewHolder {
        val binding = LayoutRecyclerViewVideosPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvVideosPreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvVideosPreviewViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class TvVideosPreviewViewHolder(
        private val binding: LayoutRecyclerViewVideosPreviewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(video: TvVideo, listener: OnItemClickListener) {
            binding.apply {

                val thumbnail = "https://img.youtube.com/vi/" + video.key + "/0.jpg"

                itemView.setOnClickListener {
                    listener.onTvVideosPreviewClick(video)
                }

                Glide.with(itemView).load(thumbnail).into(thumbnailImageView)

            }
        }

    }

    class TvVideosPreviewComparator : DiffUtil.ItemCallback<TvVideo>() {
        override fun areItemsTheSame(oldItem: TvVideo, newItem: TvVideo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvVideo, newItem: TvVideo): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onTvVideosPreviewClick(video: TvVideo)
    }

}