package com.example.movieapp.views.detail.backdrops

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.databinding.LayoutRecyclerViewBackdropsBinding
import com.example.movieapp.models.tv.TvBackdrops

class TvBackdropsAdapter(private val listener: OnItemClickListener) :
    ListAdapter<TvBackdrops, TvBackdropsAdapter.TvBackdropsViewHolder>(
        TvBackdropsComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvBackdropsViewHolder {
        val binding = LayoutRecyclerViewBackdropsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvBackdropsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvBackdropsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    override fun submitList(list: List<TvBackdrops>?) {
        if (list != null) {
            super.submitList(list.take(6))
        } else {
            super.submitList(null)
        }
    }

    class TvBackdropsViewHolder(
        private val binding: LayoutRecyclerViewBackdropsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(backdrops: TvBackdrops, listener: OnItemClickListener) {
            binding.apply {
                Glide.with(itemView).load(BuildConfig.TMDB_PHOTO_BASE_URL + backdrops.filePath)
                    .into(imageView)
                itemView.setOnClickListener {
                    listener.onTvBackdropsClick(backdrops)
                }
            }
        }

    }

    class TvBackdropsComparator : DiffUtil.ItemCallback<TvBackdrops>() {
        override fun areItemsTheSame(oldItem: TvBackdrops, newItem: TvBackdrops): Boolean {
            return oldItem.filePath == newItem.filePath
        }

        override fun areContentsTheSame(oldItem: TvBackdrops, newItem: TvBackdrops): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onTvBackdropsClick(backdrops: TvBackdrops)
    }

}