package com.example.movieapp.views.detail.backdrops

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.databinding.LayoutRecyclerViewBackdropsBinding
import com.example.movieapp.models.movies.BackdropsItem
import com.example.movieapp.views.home.tv.adapters.TvAiringTodayAdapter

class BackdropsAdapter(private val listener: OnItemClickListener) :
    ListAdapter<BackdropsItem, BackdropsAdapter.BackdropsViewHolder>(
        BackdropsComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BackdropsViewHolder {
        val binding = LayoutRecyclerViewBackdropsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return BackdropsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BackdropsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class BackdropsViewHolder(
        private val binding: LayoutRecyclerViewBackdropsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(backdrops: BackdropsItem, listener: OnItemClickListener) {
            binding.apply {
                Glide.with(itemView).load(BuildConfig.TMDB_PHOTO_BASE_URL + backdrops.filePath)
                    .into(imageView)
                itemView.setOnClickListener {
                    listener.onBackdropsClick(backdrops)
                }
            }
        }

    }

    class BackdropsComparator : DiffUtil.ItemCallback<BackdropsItem>() {
        override fun areItemsTheSame(oldItem: BackdropsItem, newItem: BackdropsItem): Boolean {
            return oldItem.filePath == newItem.filePath
        }

        override fun areContentsTheSame(oldItem: BackdropsItem, newItem: BackdropsItem): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onBackdropsClick(backdrops: BackdropsItem)
    }

}