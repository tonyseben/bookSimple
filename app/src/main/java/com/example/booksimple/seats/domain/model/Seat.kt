package com.example.booksimple.seats.domain.model

import com.example.booksimple.seats.data.remote.response.Spot

data class Seat(
    val position: String,
    val status: Status
)

fun Spot.toSeat() = Seat(
    position = position,
    status = when (status) {
        0 -> Status.Empty
        2 -> Status.Booked
        else -> Status.Vacant
    }
)
