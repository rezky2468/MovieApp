package com.example.movieapp.views.detail.backdrops

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.databinding.LayoutRecyclerViewBackdropsBinding
import com.example.movieapp.models.tv.TvSeriesBackdrop

class TvSeriesBackdropsAdapter(private val listener: OnItemClickListener, private val activity: String) :
    ListAdapter<TvSeriesBackdrop, TvSeriesBackdropsAdapter.TvSeriesBackdropsViewHolder>(
        TvSeriesBackdropsComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesBackdropsViewHolder {
        val binding = LayoutRecyclerViewBackdropsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvSeriesBackdropsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvSeriesBackdropsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    override fun submitList(list: List<TvSeriesBackdrop>?) {
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

    class TvSeriesBackdropsViewHolder(
        private val binding: LayoutRecyclerViewBackdropsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(backdrops: TvSeriesBackdrop, listener: OnItemClickListener) {
            binding.apply {
                Glide.with(itemView).load(BuildConfig.TMDB_PHOTO_BASE_URL + backdrops.filePath)
                    .into(imageView)
                itemView.setOnClickListener {
                    listener.onTvSeriesBackdropsClick(backdrops)
                }
            }
        }

    }

    class TvSeriesBackdropsComparator : DiffUtil.ItemCallback<TvSeriesBackdrop>() {
        override fun areItemsTheSame(oldItem: TvSeriesBackdrop, newItem: TvSeriesBackdrop): Boolean {
            return oldItem.filePath == newItem.filePath
        }

        override fun areContentsTheSame(oldItem: TvSeriesBackdrop, newItem: TvSeriesBackdrop): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onTvSeriesBackdropsClick(backdrops: TvSeriesBackdrop)
    }

}