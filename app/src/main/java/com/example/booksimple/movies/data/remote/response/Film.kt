package com.example.booksimple.movies.data.remote.response

data class Film(
    val age_rating: List<AgeRating>,
    val film_id: Int,
    val film_name: String,
    val film_trailer: String,
    val images: Images,
    val imdb_id: Int,
    val imdb_title_id: String,
    val other_titles: Any,
    val release_dates: List<ReleaseDate>,
    val synopsis_long: String
)