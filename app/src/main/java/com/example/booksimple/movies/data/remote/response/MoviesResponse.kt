package com.example.booksimple.movies.data.remote.response

data class MoviesResponse(
    val films: List<Film>,
    val status: Status
)