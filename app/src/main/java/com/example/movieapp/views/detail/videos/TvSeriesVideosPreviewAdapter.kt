package com.example.movieapp.views.detail.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.R
import com.example.movieapp.databinding.LayoutRecyclerViewVideosPreviewBinding
import com.example.movieapp.models.tv.TvSeriesVideo

class TvSeriesVideosPreviewAdapter(
    private val listener: OnItemClickListener, private val activity: String
) : ListAdapter<TvSeriesVideo, TvSeriesVideosPreviewAdapter.TvSeriesVideosPreviewViewHolder>(
    TvSeriesVideosPreviewComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesVideosPreviewViewHolder {
        val binding = LayoutRecyclerViewVideosPreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvSeriesVideosPreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvSeriesVideosPreviewViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    override fun submitList(list: List<TvSeriesVideo>?) {
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

    class TvSeriesVideosPreviewViewHolder(
        private val binding: LayoutRecyclerViewVideosPreviewBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(video: TvSeriesVideo, listener: OnItemClickListener) {
            binding.apply {

                val thumbnail = "https://img.youtube.com/vi/" + video.key + "/0.jpg"

                itemView.setOnClickListener {
                    listener.onTvSeriesVideosPreviewClick(video)
                }

                Glide.with(itemView).load(thumbnail)
                    .placeholder(R.drawable.bg_placeholder).into(thumbnailImageView)

            }
        }

    }

    class TvSeriesVideosPreviewComparator : DiffUtil.ItemCallback<TvSeriesVideo>() {
        override fun areItemsTheSame(oldItem: TvSeriesVideo, newItem: TvSeriesVideo): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvSeriesVideo, newItem: TvSeriesVideo): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onTvSeriesVideosPreviewClick(video: TvSeriesVideo)
    }

}