package com.example.movieapp.views.home.tv.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.R
import com.example.movieapp.databinding.LayoutRecyclerViewMoviePreviewBinding
import com.example.movieapp.models.tv.TvOnTheAir

class TvOnTheAirAdapter(private val listener: OnItemClickListener) :
    ListAdapter<TvOnTheAir, TvOnTheAirAdapter.TvOnTheAirViewHolder>(TvOnTheAirComparator()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvOnTheAirViewHolder {
        val binding = LayoutRecyclerViewMoviePreviewBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvOnTheAirViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvOnTheAirViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class TvOnTheAirViewHolder(private val binding: LayoutRecyclerViewMoviePreviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(tv: TvOnTheAir, listener: OnItemClickListener) {
            binding.apply {
                Glide.with(itemView).load(BuildConfig.TMDB_PHOTO_BASE_URL + tv.posterPath)
                    .placeholder(R.drawable.bg_placeholder)
                    .into(imageView)
                textView.text = tv.name
                itemView.setOnClickListener {
                    listener.onTvOnTheAirClick(tv)
                }
            }
        }
    }

    class TvOnTheAirComparator : DiffUtil.ItemCallback<TvOnTheAir>() {
        override fun areItemsTheSame(oldItem: TvOnTheAir, newItem: TvOnTheAir): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvOnTheAir, newItem: TvOnTheAir): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onTvOnTheAirClick(tv: TvOnTheAir)
    }

}