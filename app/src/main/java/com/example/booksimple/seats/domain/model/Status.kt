package com.example.booksimple.seats.domain.model

sealed class Status{
    object Booked : Status()
    object Selected : Status()
    object Vacant : Status()
    object Empty : Status()
}
