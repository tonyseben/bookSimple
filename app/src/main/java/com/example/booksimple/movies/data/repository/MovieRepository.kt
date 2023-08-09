package com.example.booksimple.movies.data.repository

import android.util.Log
import com.example.booksimple.movies.data.remote.MoviesApi
import com.example.booksimple.movies.data.remote.response.Film
import javax.inject.Inject

interface MovieRepository {
    suspend fun getFilms(): List<Film>
}

class MovieRepositoryImpl @Inject constructor(
    private val moviesApi: MoviesApi
): MovieRepository {

    override suspend fun getFilms(): List<Film> {
        Log.d("TEST", "Movies request")
        val moviesResponse = moviesApi.getMovies(9)
        return moviesResponse.films
    }

}