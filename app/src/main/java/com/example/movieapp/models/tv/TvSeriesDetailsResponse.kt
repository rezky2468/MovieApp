package com.example.movieapp.models.tv

import kotlinx.parcelize.Parcelize
import android.os.Parcelable
import com.example.movieapp.views.detail.casts.CastsAdapter
import com.google.gson.annotations.SerializedName

@Parcelize
data class TvSeriesDetailsResponse(

	@field:SerializedName("images")
	val images: TvSeriesImagesResponse? = null,

	@field:SerializedName("videos")
	val videos: TvSeriesVideosResponse? = null,

	@field:SerializedName("credits")
	val credits: TvSeriesCreditsResponse? = null,

	@field:SerializedName("original_language")
	val originalLanguage: String? = null,

	@field:SerializedName("number_of_episodes")
	val numberOfEpisodes: Int? = null,

//	@field:SerializedName("networks")
//	val networks: List<NetworksItem?>? = null,

//	@field:SerializedName("type")
//	val type: String? = null,

//	@field:SerializedName("backdrop_path")
//	val backdropPath: String? = null,

	@field:SerializedName("genres")
	val genres: List<TvSeriesGenre>? = null,

//	@field:SerializedName("popularity")
//	val popularity: Any? = null,

//	@field:SerializedName("production_countries")
//	val productionCountries: List<ProductionCountriesItem?>? = null,

	@field:SerializedName("id")
	val id: Int? = null,

//	@field:SerializedName("number_of_seasons")
//	val numberOfSeasons: Int? = null,

//	@field:SerializedName("vote_count")
//	val voteCount: Int? = null,

	@field:SerializedName("first_air_date")
	val firstAirDate: String? = null,

	@field:SerializedName("overview")
	val overview: String? = null,

//	@field:SerializedName("seasons")
//	val seasons: List<SeasonsItem?>? = null,

//	@field:SerializedName("languages")
//	val languages: List<String?>? = null,

//	@field:SerializedName("created_by")
//	val createdBy: List<Any?>? = null,

//	@field:SerializedName("last_episode_to_air")
//	val lastEpisodeToAir: LastEpisodeToAir? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = null,

//	@field:SerializedName("origin_country")
//	val originCountry: List<String?>? = null,

//	@field:SerializedName("spoken_languages")
//	val spokenLanguages: List<SpokenLanguagesItem?>? = null,

//	@field:SerializedName("production_companies")
//	val productionCompanies: List<ProductionCompaniesItem?>? = null,

//	@field:SerializedName("original_name")
//	val originalName: String? = null,

//	@field:SerializedName("vote_average")
//	val voteAverage: Any? = null,

	@field:SerializedName("name")
	val name: String? = null,

//	@field:SerializedName("tagline")
//	val tagline: String? = null,

//	@field:SerializedName("episode_run_time")
//	val episodeRunTime: List<Int?>? = null,

//	@field:SerializedName("adult")
//	val adult: Boolean? = null,

//	@field:SerializedName("next_episode_to_air")
//	val nextEpisodeToAir: Any? = null,

//	@field:SerializedName("in_production")
//	val inProduction: Boolean? = null,

	@field:SerializedName("last_air_date")
	val lastAirDate: String? = null,

	@field:SerializedName("homepage")
	val homepage: String? = null,

	@field:SerializedName("status")
	val status: String? = null,

	@field:SerializedName("similar")
	val similar: TvSeriesSimilar? = null,

	@field:SerializedName("recommendations")
	val recommendations: TvSeriesRecommendation? = null

) : Parcelable

@Parcelize
data class TvSeriesImagesResponse(

	@field:SerializedName("backdrops")
	val backdrops: List<TvSeriesBackdrop>? = null,

//	@field:SerializedName("posters")
//	val posters: List<PostersItem?>? = null,

//	@field:SerializedName("logos")
//	val logos: List<LogosItem?>? = null

) : Parcelable

@Parcelize
data class TvSeriesBackdrop(

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
data class TvSeriesVideosResponse(

	@field:SerializedName("results")
	val results: List<TvSeriesVideo>? = null

) : Parcelable

@Parcelize
data class TvSeriesVideo(

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
data class TvSeriesCreditsResponse(

	@field:SerializedName("cast")
	val cast: List<TvSeriesCast>? = null,

//	@field:SerializedName("crew")
//	val crew: List<CrewItem>? = null

) : Parcelable

@Parcelize
data class TvSeriesCast(

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

//	@field:SerializedName("order")
//	val order: Int? = null

) : Parcelable

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
//	val profilePath: Any? = null,
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
//data class NetworksItem(
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

@Parcelize
data class TvSeriesGenre(

	@field:SerializedName("id")
	val id: Int? = null,

	@field:SerializedName("name")
	val name: String? = null

) : Parcelable

//@Parcelize
//data class SeasonsItem(
//
//	@field:SerializedName("air_date")
//	val airDate: String? = null,
//
//	@field:SerializedName("overview")
//	val overview: String? = null,
//
//	@field:SerializedName("episode_count")
//	val episodeCount: Int? = null,
//
//	@field:SerializedName("vote_average")
//	val voteAverage: Int? = null,
//
//	@field:SerializedName("name")
//	val name: String? = null,
//
//	@field:SerializedName("season_number")
//	val seasonNumber: Int? = null,
//
//	@field:SerializedName("id")
//	val id: Int? = null,
//
//	@field:SerializedName("poster_path")
//	val posterPath: Any? = null
//) : Parcelable

//@Parcelize
//data class LastEpisodeToAir(
//
//	@field:SerializedName("production_code")
//	val productionCode: String? = null,
//
//	@field:SerializedName("overview")
//	val overview: String? = null,
//
//	@field:SerializedName("air_date")
//	val airDate: String? = null,
//
//	@field:SerializedName("episode_number")
//	val episodeNumber: Int? = null,
//
//	@field:SerializedName("show_id")
//	val showId: Int? = null,
//
//	@field:SerializedName("vote_average")
//	val voteAverage: Any? = null,
//
//	@field:SerializedName("name")
//	val name: String? = null,
//
//	@field:SerializedName("season_number")
//	val seasonNumber: Int? = null,
//
//	@field:SerializedName("runtime")
//	val runtime: Int? = null,
//
//	@field:SerializedName("id")
//	val id: Int? = null,
//
//	@field:SerializedName("still_path")
//	val stillPath: String? = null,
//
//	@field:SerializedName("vote_count")
//	val voteCount: Int? = null
//) : Parcelable

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
data class TvSeriesSimilar(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<TvSeries>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null

) : Parcelable

@Parcelize
data class TvSeriesRecommendation(

	@field:SerializedName("page")
	val page: Int? = null,

	@field:SerializedName("total_pages")
	val totalPages: Int? = null,

	@field:SerializedName("results")
	val results: List<TvSeries>? = null,

	@field:SerializedName("total_results")
	val totalResults: Int? = null

) : Parcelable

@Parcelize
data class TvSeries(

	@field:SerializedName("id")
	val id: Int? = 0,

	@field:SerializedName("first_air_date")
	val firstAirDate: String? = "",

	@field:SerializedName("overview")
	val overview: String? = "",

	@field:SerializedName("original_language")
	val originalLanguage: String? = "",

	@field:SerializedName("genre_ids")
	val genreIds: List<Int>? = null,

	@field:SerializedName("poster_path")
	val posterPath: String? = "",

	@field:SerializedName("origin_country")
	val originCountry: List<String?>? = null,

	@field:SerializedName("backdrop_path")
	val backdropPath: String? = "",

	@field:SerializedName("media_type")
	val mediaType: String? = "",

	@field:SerializedName("original_name")
	val originalName: String? = "",

	@field:SerializedName("popularity")
	val popularity: Float? = 0f,

	@field:SerializedName("vote_average")
	val voteAverage: Float? = 0f,

	@field:SerializedName("name")
	val name: String? = "",

	@field:SerializedName("adult")
	val adult: Boolean? = false,

	@field:SerializedName("vote_count")
	val voteCount: Int? = 0

) : Parcelable