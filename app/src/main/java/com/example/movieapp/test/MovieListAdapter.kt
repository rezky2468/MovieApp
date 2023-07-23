package com.example.movieapp.test

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.movieapp.databinding.LayoutRecyclerViewBinding
import com.example.movieapp.models.movies.MovieNowPlaying

class MovieListAdapter : RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder>() {

    private val nowPlayingList = ArrayList<MovieNowPlaying>()

    fun setMovie(list: List<MovieNowPlaying>) {
        this.nowPlayingList.clear()
        this.nowPlayingList.addAll(list)
        notifyDataSetChanged()
    }

    inner class MovieListViewHolder(val binding: LayoutRecyclerViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieListViewHolder {
        val binding = LayoutRecyclerViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieListViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return nowPlayingList.size
    }

    override fun onBindViewHolder(holder: MovieListViewHolder, position: Int) {
        with(holder) {
            with(nowPlayingList[position]) {
                binding.apply {
                    titleTextView.text = title
                    overviewTextView.text = overview
                    releaseDateTextView.text = releaseDate
                    Glide.with(itemView).load("" + posterPath).into(imageView)
                }
            }
        }
    }
}