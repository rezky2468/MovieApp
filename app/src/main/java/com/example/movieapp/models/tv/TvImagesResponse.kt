package com.example.movieapp.models.tv

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Parcelize
data class TvImagesResponse(

    @field:SerializedName("backdrops")
    val backdrops: List<TvBackdrops>? = null,

//	@field:SerializedName("posters")
//	val posters: List<PostersItem?>? = null,

    @field:SerializedName("id")
    val id: Int? = null,

//	@field:SerializedName("logos")
//	val logos: List<LogosItem?>? = null
) : Parcelable

//@Parcelize
//data class LogosItem(
//
//	@field:SerializedName("aspect_ratio")
//	val aspectRatio: Any? = null,
//
//	@field:SerializedName("file_path")
//	val filePath: String? = null,
//
//	@field:SerializedName("vote_average")
//	val voteAverage: Any? = null,
//
//	@field:SerializedName("width")
//	val width: Int? = null,
//
//	@field:SerializedName("iso_639_1")
//	val iso6391: String? = null,
//
//	@field:SerializedName("vote_count")
//	val voteCount: Int? = null,
//
//	@field:SerializedName("height")
//	val height: Int? = null
//) : Parcelable
//
//@Parcelize
//data class PostersItem(
//
//	@field:SerializedName("aspect_ratio")
//	val aspectRatio: Any? = null,
//
//	@field:SerializedName("file_path")
//	val filePath: String? = null,
//
//	@field:SerializedName("vote_average")
//	val voteAverage: Any? = null,
//
//	@field:SerializedName("width")
//	val width: Int? = null,
//
//	@field:SerializedName("iso_639_1")
//	val iso6391: String? = null,
//
//	@field:SerializedName("vote_count")
//	val voteCount: Int? = null,
//
//	@field:SerializedName("height")
//	val height: Int? = null
//) : Parcelable

@Parcelize
@Entity(tableName = "tv_backdrops")
data class TvBackdrops(

//	@field:SerializedName("aspect_ratio")
//	val aspectRatio: Any? = null,

    @field:SerializedName("file_path")
    @PrimaryKey val filePath: String,

//	@field:SerializedName("vote_average")
//	val voteAverage: Any? = null,
//
//	@field:SerializedName("width")
//	val width: Int? = null,
//
//	@field:SerializedName("iso_639_1")
//	val iso6391: Any? = null,
//
//	@field:SerializedName("vote_count")
//	val voteCount: Int? = null,
//
//	@field:SerializedName("height")
//	val height: Int? = null
) : Parcelable
