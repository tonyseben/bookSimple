package com.example.booksimple.movies.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksimple.movies.domain.GetMoviesUseCase
import com.example.booksimple.movies.domain.model.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MoviesViewModel @Inject constructor(
    val getMoviesUseCase: GetMoviesUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    fun getMovies() = viewModelScope.launch {
        try {
            delay(3000)
            Log.d("TEST", "Starting ...")
            val movies = getMoviesUseCase()
            _state.value = ViewState.Success(movies)
        } catch (e: Exception) {
            e.printStackTrace()
            _state.value = ViewState.Error("Failed to fetch movies \n${e.message}")
        }
    }
}

sealed class ViewState {
    object Loading : ViewState()
    data class Success(val data: List<Movie>) : ViewState()
    data class Error(val message: String) : ViewState()
}

