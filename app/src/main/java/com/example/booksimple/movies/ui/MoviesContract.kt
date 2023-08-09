package com.example.booksimple.movies.ui

import com.example.booksimple.base.UiEvent
import com.example.booksimple.base.UiSideEffect
import com.example.booksimple.base.UiState
import com.example.booksimple.movies.domain.model.Movie

class MoviesContract {

    sealed class Event: UiEvent{
        data class OnMovieClicked(val movieId: Int): Event()
    }

    data class State(
        val isLoading: Boolean = false,
        val movies: List<Movie> = emptyList(),
        val message: String = ""
    ): UiState

    sealed class SideEffect: UiSideEffect{
        sealed class Navigation: SideEffect(){
            data class ToMovieDetails(val movieId: Int): Navigation()
        }
    }
}