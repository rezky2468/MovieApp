package com.example.movieapp.models.tv

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Parcelize
data class TvVideosResponse(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("results")
	val results: List<TvVideo>? = null

) : Parcelable

@Parcelize
@Entity(tableName = "tv_videos")
data class TvVideo(

	@field:SerializedName("site")
	val site: String? = null,

//	@field:SerializedName("size")
//	val size: Int? = null,
//
//	@field:SerializedName("iso_3166_1")
//	val iso31661: String? = null,

	@field:SerializedName("name")
	val name: String? = null,

//	@field:SerializedName("official")
//	val official: Boolean? = null,

	@field:SerializedName("id")
	@PrimaryKey val id: String,

	@field:SerializedName("type")
	val type: String? = null,

	@field:SerializedName("published_at")
	val publishedAt: String? = null,

//	@field:SerializedName("iso_639_1")
//	val iso6391: String? = null,

	@field:SerializedName("key")
	val key: String? = null

) : Parcelable
