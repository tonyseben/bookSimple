package com.example.booksimple.seats.data.remote.response

data class SeatsResponse(
    val col: Int,
    val count: Int,
    val empty_col: Int,
    val empty_row: Int,
    val row: Int,
    val seating: List<List<Spot>>
)