package com.example.movieapp.views.nowplaying

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.movieapp.databinding.ActivityNowPlayingBinding
import com.example.movieapp.viewmodels.MovieViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NowPlayingActivity : AppCompatActivity() {

    private val viewModel: MovieViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityNowPlayingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nowPlayingAdapter = NowPlayingAdapter()
        binding.apply {
            recyclerView.apply {
                adapter = nowPlayingAdapter
                layoutManager = LinearLayoutManager(this@NowPlayingActivity)
            }

            viewModel.upcomingMovies.observe(this@NowPlayingActivity) { result ->
                nowPlayingAdapter.submitList(result.data)
            }

        }

//                progressBar.isVisible = result is Resource.Loading && result.data.isNullOrEmpty()
//                textViewError.isVisible = result is Resource.Error && result.data.isNullOrEmpty()
//                textViewError.text = result.error?.localizedMessage
    }
}

// Network call
//val retrofit = Retrofit.Builder()
//    .addConverterFactory(GsonConverterFactory.create())
//    .baseUrl("https://listen-api-test.listennotes.com/api/v2/")
//    .build()
//
//val apiService = retrofit.create(ApiService::class.java)
//val call = apiService.justListen2()
//
//call.enqueue(object : Callback<PodcastEpisode> {
//    override fun onResponse(
//        call: Call<PodcastEpisode>,
//        response: Response<PodcastEpisode>
//    ) {
//
//        if (response.isSuccessful) {
//            lifecycleScope.launch {
//                val responseBody = response.body()!!
//                val audioUrl = responseBody.audio
//                val imageUrl = responseBody.image
//                val directAudioLink =
//                    withContext(Dispatchers.IO) { getDirectMp3Link(audioUrl).toString() }
//                val dataSourceFactory = DefaultDataSourceFactory(
//                    requireContext(),
//                    Util.getUserAgent(requireContext(), "PodcastApp")
//                )
//                val mediaSource = ProgressiveMediaSource.Factory(dataSourceFactory)
//                    .createMediaSource(MediaItem.fromUri(directAudioLink))
//
//                Glide.with(this@HomeFragment)
//                    .load(imageUrl)
//                    .into(coverImageView)
//
//                myPlayer.setMediaSource(mediaSource)
//                myPlayer.prepare()
//
//                Toast.makeText(context, "Network call success!", Toast.LENGTH_LONG).show()
//
//            }
//        } else {
//            Toast.makeText(context, "Network call failed!", Toast.LENGTH_LONG).show()
//        }
//
//
//    }
//
//    override fun onFailure(call: Call<PodcastEpisode>, t: Throwable) {
//        TODO("Not yet implemented")
//    }
//})