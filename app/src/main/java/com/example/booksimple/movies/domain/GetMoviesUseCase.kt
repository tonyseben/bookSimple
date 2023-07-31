package com.example.booksimple.movies.domain

import com.example.booksimple.movies.data.repository.MovieRepository
import javax.inject.Inject

interface GetMoviesUseCase {
    suspend operator fun invoke(): List<String>
}

class GetMoviesUseCaseImpl @Inject constructor(
    private val repo: MovieRepository
): GetMoviesUseCase {

    override suspend operator fun invoke(): List<String>{
        return repo.getMovies()
    }
}