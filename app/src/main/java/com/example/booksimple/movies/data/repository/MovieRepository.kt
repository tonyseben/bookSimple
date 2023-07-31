package com.example.booksimple.movies.data.repository

interface MovieRepository {
    suspend fun getMovies(): List<String>
}

class MovieRepositoryImpl: MovieRepository {
    override suspend fun getMovies(): List<String> {
        return listOf("MovieRepositoryImpl")
    }

}