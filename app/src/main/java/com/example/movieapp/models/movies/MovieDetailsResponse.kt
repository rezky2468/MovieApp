package com.example.movieapp.models.movies

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.google.gson.annotations.SerializedName

@Parcelize
data class MovieDetailsResponse(

    @field:SerializedName("original_language")
    val originalLanguage: String? = null,

//	@field:SerializedName("imdb_id")
//	val imdbId: String? = null,

    @field:SerializedName("videos")
    val videos: MovieVideoResponse? = null,

//	@field:SerializedName("video")
//	val video: Boolean? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = null,

//	@field:SerializedName("revenue")
//	val revenue: Int? = null,

    @field:SerializedName("credits")
    val credits: MovieCreditResponse? = null,

    @field:SerializedName("genres")
    val genres: List<MovieGenre>? = null,

//	@field:SerializedName("popularity")
//	val popularity: Any? = null,

//	@field:SerializedName("production_countries")
//	val productionCountries: List<ProductionCountriesItem?>? = null,

    @field:SerializedName("id")
    val id: Int? = null,

//	@field:SerializedName("vote_count")
//	val voteCount: Int? = null,

//	@field:SerializedName("budget")
//	val budget: Int? = null,

    @field:SerializedName("overview")
    val overview: String? = null,

    @field:SerializedName("images")
    val images: MovieImageResponse? = null,

//	@field:SerializedName("original_title")
//	val originalTitle: String? = null,

    @field:SerializedName("runtime")
    val runtime: Int? = null,

    @field:SerializedName("poster_path")
    val posterPath: String? = null,

//	@field:SerializedName("spoken_languages")
//	val spokenLanguages: List<SpokenLanguagesItem?>? = null,

//	@field:SerializedName("production_companies")
//	val productionCompanies: List<ProductionCompaniesItem?>? = null,

    @field:SerializedName("release_date")
    val releaseDate: String? = null,

//	@field:SerializedName("vote_average")
//	val voteAverage: Any? = null,

//	@field:SerializedName("belongs_to_collection")
//	val belongsToCollection: BelongsToCollection? = null,

    @field:SerializedName("tagline")
    val tagline: String? = null,

//	@field:SerializedName("adult")
//	val adult: Boolean? = null,

    @field:SerializedName("homepage")
    val homepage: String? = null,

//	@field:SerializedName("status")
//	val status: String? = null

    @field:SerializedName("similar")
    val similar: Similar? = null,

    @field:SerializedName("recommendations")
    val recommendations: Recommendation? = null

) : Parcelable

//@Parcelize
//data class ProductionCountriesItem(
//
//	@field:SerializedName("iso_3166_1")
//	val iso31661: String? = null,
//
//	@field:SerializedName("name")
//	val name: String? = null
//) : Parcelable

//@Parcelize
//data class SpokenLanguagesItem(
//
//	@field:SerializedName("name")
//	val name: String? = null,
//
//	@field:SerializedName("iso_639_1")
//	val iso6391: String? = null,
//
//	@field:SerializedName("english_name")
//	val englishName: String? = null
//) : Parcelable

@Parcelize
data class MovieGenre(

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("name")
    val name: String? = null

) : Parcelable

//@Parcelize
//data class ProductionCompaniesItem(
//
//	@field:SerializedName("logo_path")
//	val logoPath: String? = null,
//
//	@field:SerializedName("name")
//	val name: String? = null,
//
//	@field:SerializedName("id")
//	val id: Int? = null,
//
//	@field:SerializedName("origin_country")
//	val originCountry: String? = null
//) : Parcelable

//@Parcelize
//data class BelongsToCollection(
//
//	@field:SerializedName("backdrop_path")
//	val backdropPath: Any? = null,
//
//	@field:SerializedName("name")
//	val name: String? = null,
//
//	@field:SerializedName("id")
//	val id: Int? = null,
//
//	@field:SerializedName("poster_path")
//	val posterPath: String? = null
//) : Parcelable

@Parcelize
data class MovieImageResponse(

    @field:SerializedName("backdrops")
    val backdrops: List<MovieBackdrop>? = null,

//	@field:SerializedName("posters")
//	val posters: List<PostersItem?>? = null,

//	@field:SerializedName("logos")
//	val logos: List<LogosItem?>? = null

) : Parcelable

@Parcelize
data class MovieBackdrop(

//	@field:SerializedName("aspect_ratio")
//	val aspectRatio: Any? = null,

    @field:SerializedName("file_path")
    val filePath: String? = null,

//	@field:SerializedName("vote_average")
//	val voteAverage: Any? = null,

//	@field:SerializedName("width")
//	val width: Int? = null,

//	@field:SerializedName("iso_639_1")
//	val iso6391: Any? = null,

//	@field:SerializedName("vote_count")
//	val voteCount: Int? = null,

//	@field:SerializedName("height")
//	val height: Int? = null

) : Parcelable

@Parcelize
data class MovieVideoResponse(

    @field:SerializedName("results")
    val results: List<MovieVideo>? = null

) : Parcelable

@Parcelize
data class MovieVideo(

    @field:SerializedName("site")
    val site: String? = null,

//	@field:SerializedName("size")
//	val size: Int? = null,

//	@field:SerializedName("iso_3166_1")
//	val iso31661: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

//	@field:SerializedName("official")
//	val official: Boolean? = null,

    @field:SerializedName("id")
    val id: String? = null,

    @field:SerializedName("type")
    val type: String? = null,

    @field:SerializedName("published_at")
    val publishedAt: String? = null,

//	@field:SerializedName("iso_639_1")
//	val iso6391: String? = null,

    @field:SerializedName("key")
    val key: String? = null

) : Parcelable

@Parcelize
data class MovieCreditResponse(

    @field:SerializedName("cast")
    val cast: List<MovieCast>? = null,

//	@field:SerializedName("crew")
//	val crew: List<CrewItem?>? = null

) : Parcelable

@Parcelize
data class MovieCast(

    @field:SerializedName("cast_id")
    val castId: Int? = null,

    @field:SerializedName("character")
    val character: String? = null,

    @field:SerializedName("gender")
    val gender: Int? = null,

    @field:SerializedName("credit_id")
    val creditId: String? = null,

    @field:SerializedName("known_for_department")
    val knownForDepartment: String? = null,

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

//	@field:SerializedName("order")
//	val order: Int? = null

) : Parcelable

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

//@Parcelize
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
//) : Parcelable


@Parcelize
data class Similar(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("results")
    val results: List<Movie>? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null

) : Parcelable

@Parcelize
data class Recommendation(

    @field:SerializedName("page")
    val page: Int? = null,

    @field:SerializedName("results")
    val results: List<Movie>? = null,

    @field:SerializedName("total_pages")
    val totalPages: Int? = null,

    @field:SerializedName("total_results")
    val totalResults: Int? = null

) : Parcelable

@Parcelize
data class Movie(

    @field:SerializedName("id")
    val id: Int? = 0,

    @field:SerializedName("adult")
    val adult: Boolean? = true,

    @field:SerializedName("title")
    val title: String? = "",

    @field:SerializedName("original_title")
    val originalTitle: String? = "",

    @field:SerializedName("overview")
    val overview: String? = "",

    @field:SerializedName("release_date")
    val releaseDate: String? = "",

    @field:SerializedName("poster_path")
    val posterPath: String? = "",

    @field:SerializedName("original_language")
    val originalLanguage: String? = "",

    @field:SerializedName("backdrop_path")
    val backdropPath: String? = "",

    @field:SerializedName("media_type")
    val mediaType: String? = "",

    @field:SerializedName("video")
    val video: Boolean? = false,

    @field:SerializedName("popularity")
    val popularity: Float? = 0f,

    @field:SerializedName("vote_average")
    val voteAverage: Float? = 0f,

    @field:SerializedName("vote_count")
    val voteCount: Int? = 0,

    @field:SerializedName("genre_ids")
    val genreIds: List<Int?>? = null,

) : Parcelable


