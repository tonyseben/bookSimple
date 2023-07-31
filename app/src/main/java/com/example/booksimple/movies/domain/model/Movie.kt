package com.example.booksimple.movies.domain.model

import com.example.booksimple.movies.data.remote.response.Film

data class Movie(
    val ageRating: String?,
    val filmId: Int,
    val filmName: String,
    val image: String?,
    val synopsis: String
)

fun Film.toMovie() = Movie(
    ageRating = age_rating.firstOrNull()?.rating,
    filmId = film_id,
    filmName = film_name,
    image = images.poster.`1`?.medium?.film_image,
    synopsis = synopsis_long
)


