package com.example.booksimple.seats.data.repository

import com.example.booksimple.seats.data.remote.SeatsApi
import com.example.booksimple.seats.data.remote.response.Spot
import javax.inject.Inject

interface SeatsRepository {

    suspend fun getSeatsForFilm(filmId: Int): List<List<Spot>>
}

class SeatsRepositoryImpl @Inject constructor(
    val seatsApi: SeatsApi
): SeatsRepository{
    override suspend fun getSeatsForFilm(filmId: Int): List<List<Spot>> {
        val seatsResponse = seatsApi.getSeatsForFilm(filmId)
        return seatsResponse.seating
    }

}