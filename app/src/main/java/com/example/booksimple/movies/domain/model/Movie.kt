package com.example.booksimple.movies.domain.model

import com.example.booksimple.movies.data.remote.response.Film

data class Movie(
    val id: Int,
    val title: String,
    val image: String?
)

fun Film.toMovie() = Movie(
    id = film_id,
    title = film_name,
    image = images.poster.`1`?.medium?.film_image
)


