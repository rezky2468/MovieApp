package com.example.movieapp.views.detail.casts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.databinding.LayoutRecyclerViewCreditsBinding
import com.example.movieapp.models.movies.MovieCast
import com.example.movieapp.models.tv.TvSeriesCast

class CastsAdapter<T>(private val listener: OnItemClickListener<T>) :
    ListAdapter<T, CastsAdapter<T>.CastsViewHolder>(
        CastsComparator<T>()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastsViewHolder {
        val binding = LayoutRecyclerViewCreditsBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return CastsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CastsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    inner class CastsViewHolder(
        private val binding: LayoutRecyclerViewCreditsBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: T, listener: OnItemClickListener<T>) {
            binding.apply {
                when(cast) {
                    is MovieCast -> {
                        Glide.with(itemView).load(BuildConfig.TMDB_PHOTO_BASE_URL + cast.profilePath)
                            .placeholder(R.drawable.bg_placeholder)
                            .into(imageView)
                        charTextView.text = cast.character
                        nameTextView.text = cast.name
                        itemView.setOnClickListener {
                            listener.onCastsClick(cast)
                        }
                    }
                    is TvSeriesCast -> {
                        Glide.with(itemView).load(BuildConfig.TMDB_PHOTO_BASE_URL + cast.profilePath)
                            .placeholder(R.drawable.bg_placeholder)
                            .into(imageView)
                        charTextView.text = cast.character
                        nameTextView.text = cast.name
                        itemView.setOnClickListener {
                            listener.onCastsClick(cast)
                        }
                    }
                    else -> {
                        // Handle any other cases or leave it empty if not applicable
                    }
                }

            }
        }


    }

    class CastsComparator<T> : DiffUtil.ItemCallback<T>() {
        override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
            if (oldItem is MovieCast && newItem is MovieCast) {
                return oldItem.castId == newItem.castId
            } else if (oldItem is TvSeriesCast && newItem is TvSeriesCast) {
                return oldItem.id == newItem.id
            }
            return false
        }

        override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
            if (oldItem is MovieCast && newItem is MovieCast) {
                return oldItem == newItem
            } else if (oldItem is TvSeriesCast && newItem is TvSeriesCast) {
                return oldItem == newItem
            }
            return false
        }

    }

    interface OnItemClickListener<T> {
        fun onCastsClick(cast: T)
    }

}