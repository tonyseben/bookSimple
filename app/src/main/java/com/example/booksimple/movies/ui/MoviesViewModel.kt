package com.example.booksimple.movies.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksimple.movies.domain.GetMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
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
            val movies = getMoviesUseCase()
            _state.value = ViewState.Success(movies)
        } catch (e: Exception) {
            _state.value = ViewState.Error("Failed to fetch movies \n${e.message}")
        }
    }
}

sealed class ViewState {
    object Loading : ViewState()
    data class Success(val data: List<String>) : ViewState()
    data class Error(val message: String) : ViewState()
}

