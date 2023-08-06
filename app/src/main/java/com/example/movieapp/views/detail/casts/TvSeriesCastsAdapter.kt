package com.example.movieapp.views.detail.casts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.BuildConfig
import com.example.movieapp.databinding.LayoutRecyclerViewCastBinding
import com.example.movieapp.models.tv.TvSeriesCast

class TvSeriesCastsAdapter(private val listener: OnItemClickListener, private val activity: String) :
    ListAdapter<TvSeriesCast, TvSeriesCastsAdapter.TvSeriesCastsViewHolder>(
        TvSeriesCastsComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TvSeriesCastsViewHolder {
        val binding = LayoutRecyclerViewCastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TvSeriesCastsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TvSeriesCastsViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    override fun submitList(list: List<TvSeriesCast>?) {
        if (list != null) {
            if (activity == "detail") {
                super.submitList(list.take(10))
            } else if (activity == "full") {
                submitList(list)
            }
        } else {
            super.submitList(null)
        }
    }

    class TvSeriesCastsViewHolder(
        private val binding: LayoutRecyclerViewCastBinding,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(cast: TvSeriesCast, listener: OnItemClickListener) {
            binding.apply {
                Glide.with(itemView).load(BuildConfig.TMDB_PHOTO_BASE_URL + cast.profilePath)
                    .into(imageView)
                charTextView.text = cast.character
                nameTextView.text = cast.name
                itemView.setOnClickListener {
                    listener.onTvSeriesCastsClick(cast)
                }
            }
        }


    }

    class TvSeriesCastsComparator : DiffUtil.ItemCallback<TvSeriesCast>() {
        override fun areItemsTheSame(oldItem: TvSeriesCast, newItem: TvSeriesCast): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvSeriesCast, newItem: TvSeriesCast): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onTvSeriesCastsClick(cast: TvSeriesCast)
    }

}