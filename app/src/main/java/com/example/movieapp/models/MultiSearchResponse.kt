package com.example.movieapp.models

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Parcelize
data class MultiSearchResponse(

//	@field:SerializedName("page")
//	val page: Int? = null,
//
//	@field:SerializedName("total_pages")
//	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<MultiSearch>? = null,

//	@field:SerializedName("total_results")
//	val totalResults: Int? = null

) : Parcelable

@Parcelize
@Entity(tableName="multi_search")
data class MultiSearch(

	@field:SerializedName("first_air_date")
	val firstAirDate: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

	@field:SerializedName("original_language")
	val originalLanguage: String? = null,

//	@field:SerializedName("genre_ids")
//	val genreIds: List<Int?>? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

//	@field:SerializedName("origin_country")
//	val originCountry: List<String?>? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = null,

	@field:SerializedName("media_type")
	val mediaType: String? = null,

//	@field:SerializedName("original_name")
//	val originalName: String? = null,
//
//	@field:SerializedName("popularity")
//	val popularity: Any? = null,
//
//	@field:SerializedName("vote_average")
//	val voteAverage: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("id")
	@PrimaryKey val id: Int,

//	@field:SerializedName("adult")
//	val adult: Boolean? = null,
//
//	@field:SerializedName("vote_count")
//	val voteCount: Int? = null

	@field:SerializedName("title")
	val title: String? = null,

	@field:SerializedName("release_date")
	val releaseDate: String? = null,

	@field:SerializedName("original_title")
	val originalTitle: String? = null

) : Parcelable
