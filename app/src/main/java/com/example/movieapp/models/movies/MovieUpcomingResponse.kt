package com.example.movieapp.models.movies

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Parcelize
data class MovieUpcomingResponse(

    @field:SerializedName("page")
    val page: Int? = 0,

    @field:SerializedName("total_pages")
    val totalPages: Int? = 0,

    @field:SerializedName("results")
    val results: List<MovieUpcoming>,

    @field:SerializedName("total_results")
    val totalResults: Int? = 0

) : Parcelable

@Parcelize
@Entity(tableName = "movie_upcoming")
data class MovieUpcoming(

    @field:SerializedName("overview")
    val overview: String? = null,

//	@field:SerializedName("original_language")
//	val originalLanguage: String? = null,
//
//	@field:SerializedName("original_title")
//	val originalTitle: String? = null,
//
//	@field:SerializedName("video")
//	val video: Boolean? = false,

    @field:SerializedName("title")
    val title: String? = null,

//	@field:SerializedName("genre_ids")
//	val genreIds: List<Int>? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

//	@field:SerializedName("backdrop_path")
//	val backdropPath: String? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

//	@field:SerializedName("popularity")
//	val popularity: Float? = 0f,
//
//	@field:SerializedName("vote_average")
//	val voteAverage: Float? = 0f,

    @field:SerializedName("id")
    @PrimaryKey val id: Int? = 0,

//	@field:SerializedName("adult")
//	val adult: Boolean? = false,
//
//	@field:SerializedName("vote_count")
//	val voteCount: Int? = 0
) : Parcelable