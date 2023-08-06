package com.example.movieapp.views.detail.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.LayoutRecyclerViewVideosBinding
import com.example.movieapp.models.tv.TvSeriesVideo

class TvSeriesVideosAdapter(
    private val listener: OnItemClickListener,
) : ListAdapter<TvSeriesVideo, TvSeriesVideosAdapter.TvSeriesVideosViewHolder>(
    TvSeriesVideosComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesVideosViewHolder {
        val binding = LayoutRecyclerViewVideosBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvSeriesVideosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvSeriesVideosViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class TvSeriesVideosViewHolder(
        private val binding: LayoutRecyclerViewVideosBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(video: TvSeriesVideo, listener: OnItemClickListener) {
            binding.apply {

                val thumbnail = "https://img.youtube.com/vi/" + video.key + "/0.jpg"

                titleTextView.text = video.name
                typeTextView.text = video.type + " · " + video.publishedAt?.take(10)

                itemView.setOnClickListener {
                    listener.onTvSeriesVideosClick(video)
                }

                Glide.with(itemView).load(thumbnail)
                    .placeholder(R.drawable.bg_placeholder).into(thumbnailImageView)

            }
        }

    }

    class TvSeriesVideosComparator : DiffUtil.ItemCallback<TvSeriesVideo>() {
        override fun areItemsTheSame(oldItem: TvSeriesVideo, newItem: TvSeriesVideo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvSeriesVideo, newItem: TvSeriesVideo): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onTvSeriesVideosClick(video: TvSeriesVideo)
    }

}