package com.example.movieapp.views.detail.casts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.databinding.LayoutRecyclerViewCastBinding
import com.example.movieapp.models.tv.TvCast

class TvCastsAdapter(private val listener: OnItemClickListener) :
    ListAdapter<TvCast, TvCastsAdapter.TvCastsViewHolder>(
        TvCastsComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvCastsViewHolder {
        val binding = LayoutRecyclerViewCastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvCastsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvCastsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class TvCastsViewHolder(
        private val binding: LayoutRecyclerViewCastBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: TvCast, listener: OnItemClickListener) {
            binding.apply {
                Glide.with(itemView).load(BuildConfig.TMDB_PHOTO_BASE_URL + cast.profilePath)
                    .into(imageView)
                charTextView.text = cast.character
                nameTextView.text = cast.name
                itemView.setOnClickListener {
                    listener.onTvCastsClick(cast)
                }
            }
        }


    }

    class TvCastsComparator : DiffUtil.ItemCallback<TvCast>() {
        override fun areItemsTheSame(oldItem: TvCast, newItem: TvCast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvCast, newItem: TvCast): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onTvCastsClick(cast: TvCast)
    }

}