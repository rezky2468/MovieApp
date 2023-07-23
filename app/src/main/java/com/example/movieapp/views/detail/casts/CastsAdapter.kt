package com.example.movieapp.views.detail.casts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.databinding.LayoutRecyclerViewCastBinding
import com.example.movieapp.models.movies.MovieCast

class CastsAdapter(private val listener: OnItemClickListener) :
    ListAdapter<MovieCast, CastsAdapter.CastsViewHolder>(
        CastsComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastsViewHolder {
        val binding = LayoutRecyclerViewCastBinding.inflate(
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

    class CastsViewHolder(
        private val binding: LayoutRecyclerViewCastBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: MovieCast, listener: OnItemClickListener) {
            binding.apply {
                Glide.with(itemView).load(BuildConfig.TMDB_PHOTO_BASE_URL + cast.profilePath)
                    .into(imageView)
                charTextView.text = cast.character
                nameTextView.text = cast.name
                itemView.setOnClickListener {
                    listener.onCastsClick(cast)
                }
            }
        }


    }

    class CastsComparator : DiffUtil.ItemCallback<MovieCast>() {
        override fun areItemsTheSame(oldItem: MovieCast, newItem: MovieCast): Boolean {
            return oldItem.castId == newItem.castId
        }

        override fun areContentsTheSame(oldItem: MovieCast, newItem: MovieCast): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onCastsClick(cast: MovieCast)
    }

}