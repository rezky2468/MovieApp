package com.example.movieapp.models.movies

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

data class CreditsResponse(

    @field:SerializedName("cast")
	val cast: List<MovieCast>? = null,

    @field:SerializedName("id")
	val id: Int? = null,

//	@field:SerializedName("crew")
//	val crew: List<CrewItem?>? = null
)

@Entity("casts")
data class MovieCast(

	@field:SerializedName("cast_id")
	@PrimaryKey val castId: Int,

	@field:SerializedName("character")
	val character: String? = null,

	@field:SerializedName("gender")
	val gender: Int? = null,

	@field:SerializedName("credit_id")
	val creditId: String? = null,

//	@field:SerializedName("known_for_department")
//	val knownForDepartment: String? = null,

	@field:SerializedName("original_name")
	val originalName: String? = null,

//	@field:SerializedName("popularity")
//	val popularity: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

	@field:SerializedName("profile_path")
	val profilePath: String? = null,

	@field:SerializedName("id")
	val id: Int? = null,

//	@field:SerializedName("adult")
//	val adult: Boolean? = null,
//
//	@field:SerializedName("order")
//	val order: Int? = null
)

//data class CrewItem(
//
//	@field:SerializedName("gender")
//	val gender: Int? = null,
//
//	@field:SerializedName("credit_id")
//	val creditId: String? = null,
//
//	@field:SerializedName("known_for_department")
//	val knownForDepartment: String? = null,
//
//	@field:SerializedName("original_name")
//	val originalName: String? = null,
//
//	@field:SerializedName("popularity")
//	val popularity: Any? = null,
//
//	@field:SerializedName("name")
//	val name: String? = null,
//
//	@field:SerializedName("profile_path")
//	val profilePath: String? = null,
//
//	@field:SerializedName("id")
//	val id: Int? = null,
//
//	@field:SerializedName("adult")
//	val adult: Boolean? = null,
//
//	@field:SerializedName("department")
//	val department: String? = null,
//
//	@field:SerializedName("job")
//	val job: String? = null
//)
