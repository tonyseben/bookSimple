package com.example.booksimple.movies.data.remote.model

data class MoviesResponse(
    val films: List<Film>,
    val status: Status
)