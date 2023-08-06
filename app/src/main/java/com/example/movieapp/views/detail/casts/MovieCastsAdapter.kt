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

class MovieCastsAdapter(private val listener: OnItemClickListener, private val activity: String) :
    ListAdapter<MovieCast, MovieCastsAdapter.MovieCastsPreviewViewHolder>(
        MovieCastsPreviewComparator()
    ) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieCastsPreviewViewHolder {
        val binding = LayoutRecyclerViewCastBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return MovieCastsPreviewViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieCastsPreviewViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    override fun submitList(list: List<MovieCast>?) {
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

    class MovieCastsPreviewViewHolder(
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

    class MovieCastsPreviewComparator : DiffUtil.ItemCallback<MovieCast>() {
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