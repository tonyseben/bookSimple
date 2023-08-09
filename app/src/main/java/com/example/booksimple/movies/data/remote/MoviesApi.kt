package com.example.booksimple.movies.data.remote

import com.example.booksimple.movies.data.remote.response.MoviesResponse
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface MoviesApi {

    @Headers(
        "client: NONE_123",
        "x-api-key: Li0HhlSC2e1mP8yqpm0QQ953aM8yQIvX1f5jpS54",
        "authorization: Basic Tk9ORV8xMjM6aE1xYzdLekJrQ2c4",
        "territory: US",
        "api-version: v200",
        "geolocation: 33.7675434;-84.5849409",
        "device-datetime: 2023-08-06T11:04:03+0000"
    )
    @GET("filmsNowShowing/")
    suspend fun getMovies(@Query("n") count: Int): MoviesResponse
}