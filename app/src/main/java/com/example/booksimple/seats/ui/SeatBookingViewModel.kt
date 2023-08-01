package com.example.booksimple.seats.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.booksimple.seats.domain.GetSeatsUseCase
import com.example.booksimple.seats.domain.model.Seat
import com.example.booksimple.seats.domain.model.Status
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SeatBookingViewModel @Inject constructor(
    private val getSeatsUseCase: GetSeatsUseCase
) : ViewModel() {

    private val _state: MutableStateFlow<ViewState> = MutableStateFlow(ViewState.Loading)
    val state: StateFlow<ViewState> = _state

    fun getSeatsForMovie(movieId: Int) = viewModelScope.launch {
        try {
            Log.d("TEST", "Starting ...")
            val seats = getSeatsUseCase(movieId)
            _state.value = ViewState.Success(seats)
        } catch (e: Exception) {
            e.printStackTrace()
            _state.value = ViewState.Error("Failed to fetch seats \n${e.message}")
        }
    }

    fun seatStatusChange(position: Int) {
        if (_state.value is ViewState.Success) {
            val seats = (_state.value as ViewState.Success).data.toMutableList()
            when(seats[position].status) {
                Status.Selected -> seats[position] = seats[position].copy(status = Status.Vacant)
                Status.Vacant -> seats[position] = seats[position].copy(status = Status.Selected)
                else -> {}
            }
            _state.update {
                (_state.value as ViewState.Success).copy(data = seats)
            }
        }
    }

}


sealed class ViewState {
    object Loading : ViewState()
    data class Success(val data: List<Seat>) : ViewState()
    data class Error(val message: String) : ViewState()
}