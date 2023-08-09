package com.example.booksimple.movies.ui

import android.util.Log
import androidx.lifecycle.viewModelScope
import com.example.booksimple.base.BaseViewModel
import com.example.booksimple.movies.domain.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    val getMoviesUseCase: GetMoviesUseCase
) : BaseViewModel<
        MoviesContract.State,
        MoviesContract.Event,
        MoviesContract.SideEffect>(MoviesContract.State()) {

    init {
        getMovies()
    }

    fun getMovies() = viewModelScope.launch {
        try {
            Log.d("TEST", "Starting ...")
            setState { copy(isLoading = true) }
            val movies = getMoviesUseCase()
            setState { copy(movies = movies, isLoading = false) }
        } catch (e: Exception) {
            e.printStackTrace()
            setState { copy(message = "Failed to fetch movies \n${e.message}", isLoading = false) }
        }
    }

    override fun handleEvents(event: MoviesContract.Event) {
        when (event) {
            is MoviesContract.Event.OnMovieClicked -> {
                applySideEffect(MoviesContract.SideEffect.Navigation.ToMovieDetails(event.movieId))
            }
        }
    }
}


