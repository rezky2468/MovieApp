package com.example.movieapp.models.movies

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class VideosResponse(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("results")
	val results: List<Video>? = null
) : Parcelable

@Parcelize
@Entity(tableName = "videos")
data class Video(

	@field:SerializedName("site")
	val site: String? = null,

	@field:SerializedName("size")
	val size: Int? = null,

//	@field:SerializedName("iso_3166_1")
//	val iso31661: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

//	@field:SerializedName("official")
//	val official: Boolean? = null,

	@field:SerializedName("id")
    @PrimaryKey	val id: String,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("published_at")
	val publishedAt: String? = null,

//	@field:SerializedName("iso_639_1")
//	val iso6391: String? = null,

	@field:SerializedName("key")
	val key: String? = null
) : Parcelable
