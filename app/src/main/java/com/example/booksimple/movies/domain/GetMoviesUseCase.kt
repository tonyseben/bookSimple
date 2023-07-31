package com.example.booksimple.movies.domain

import com.example.booksimple.movies.data.repository.MovieRepository
import com.example.booksimple.movies.domain.model.Movie
import com.example.booksimple.movies.domain.model.toMovie
import javax.inject.Inject

interface GetMoviesUseCase {
    suspend operator fun invoke(): List<Movie>
}

class GetMoviesUseCaseImpl @Inject constructor(
    private val repo: MovieRepository
): GetMoviesUseCase {

    override suspend operator fun invoke(): List<Movie>{
        return repo.getFilms().map { film -> film.toMovie() }
    }
}