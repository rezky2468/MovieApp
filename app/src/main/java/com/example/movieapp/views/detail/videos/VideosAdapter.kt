package com.example.movieapp.views.detail.videos

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.LayoutRecyclerViewVideosBinding
import com.example.movieapp.models.movies.Video
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer


class VideosAdapter(
    private val listener: OnItemClickListener,
) : ListAdapter<Video, VideosAdapter.VideosViewHolder>(
    VideosComparator()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VideosViewHolder {
        val binding = LayoutRecyclerViewVideosBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return VideosViewHolder(binding)
    }

    override fun onBindViewHolder(holder: VideosViewHolder, position: Int) {
        val currentItem = getItem(position)
        if (currentItem != null) {
            holder.bind(currentItem, listener)
        }
    }

    class VideosViewHolder(
        private val binding: LayoutRecyclerViewVideosBinding,
//        private val lifecycle: Lifecycle
    ) : RecyclerView.ViewHolder(binding.root) {
        private var youTubePlayer: YouTubePlayer? = null
        fun bind(video: Video, listener: OnItemClickListener) {
            binding.apply {

                val youtubeUrl = "https://www.youtube.com/embed/"
                val thumbnail = "https://img.youtube.com/vi/" + video.key + "/0.jpg"
//                lifecycle.addObserver(youtubePlayerView)

                titleTextView.text = video.name
                typeTextView.text = video.type + " · " + video.publishedAt?.take(10)

                itemView.setOnClickListener {
                    listener.onVideosClick(video)
                }

                Glide.with(itemView).load(thumbnail).into(thumbnailImageView)
//                youtubePlayerView.addYouTubePlayerListener(object :
//                    AbstractYouTubePlayerListener() {
//                    override fun onReady(youTubePlayer: YouTubePlayer) {
//                        super.onReady(youTubePlayer)
//                        this@VideosViewHolder.youTubePlayer = youTubePlayer
////                        val key = video.key
////                        if (key != null) {
////                            youTubePlayer.loadVideo(key, 0f)
////                        }
//                        video.key?.let { youTubePlayer.cueVideo(it, 0f) }
//                    }
//                })

            }
        }


    }

    class VideosComparator : DiffUtil.ItemCallback<Video>() {
        override fun areItemsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Video, newItem: Video): Boolean {
            return oldItem == newItem
        }

    }

    interface OnItemClickListener {
        fun onVideosClick(video: Video)
    }

}