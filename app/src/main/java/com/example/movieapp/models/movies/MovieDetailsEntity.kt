package com.example.movieapp.models.movies

import androidx.room.Entity
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation

@Entity(tableName = "movie_details")
data class MovieDetailsEntity(

    @PrimaryKey val id: Int,
    val title: String,
    val overview: String?,
    val posterPath: String,
    val releaseDate: String,
    val runtime: Int,
    val originalLanguage: String,
    val backdropPath: String,
    val tagline: String,
    val homepage: String,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(MovieDetailsCrossRef::class)
    )
    val genres: List<MovieGenre>,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(MovieDetailsCrossRef::class)
    )
    val images: MovieImageResponse,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(MovieDetailsCrossRef::class)
    )
    val credits: MovieCreditResponse,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(MovieDetailsCrossRef::class)
    )
    val videos: MovieVideoResponse,

    )

@Entity(tableName = "movie_details_cross_ref", primaryKeys = ["movieId", "genreId"])
data class MovieDetailsCrossRef(
    val movieId: Int,
    val genreId: Int
)

@Entity(tableName = "movie_genres")
data class MovieGenreEntity(
    @PrimaryKey val id: Int,
    val name: String
)

data class MovieImageEntity(
    val backdrops: List<MovieBackdrop>? = null,
)

data class MovieCreditResponseEntity(
    val cast: List<MovieCast>? = null,
)

data class MovieVideoResponseEntity(
    val results: List<MovieVideo>? = null
)

@Entity(tableName = "movie_backdrops")
data class MovieBackdropEntity(
    val filePath: String? = null,
)

@Entity(tableName = "movie_casts")
data class MovieCastEntity(
    val id: Int? = null,
    val creditId: String? = null,
    val castId: Int? = null,
    val name: String? = null,
    val originalName: String? = null,
    val character: String? = null,
    val gender: Int? = null,
    val profilePath: String? = null,
    val knownForDepartment: String? = null,
)

@Entity(tableName = "movie_videos")
data class MovieVideoEntity(
    val site: String? = null,
    val name: String? = null,
    val id: String? = null,
    val type: String? = null,
    val publishedAt: String? = null,
    val key: String? = null
)

