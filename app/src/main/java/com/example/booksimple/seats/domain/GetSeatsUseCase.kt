package com.example.booksimple.seats.domain

import com.example.booksimple.seats.data.repository.SeatsRepository
import com.example.booksimple.seats.domain.model.Seat
import com.example.booksimple.seats.domain.model.Status
import com.example.booksimple.seats.domain.model.toSeat
import javax.inject.Inject

interface GetSeatsUseCase {
    suspend operator fun invoke(filmId: Int): List<Seat>
}

class GetSeatsUseCaseImpl @Inject constructor(
    private val repo: SeatsRepository
): GetSeatsUseCase {

    override suspend operator fun invoke(filmId: Int): List<Seat>{
        val spots = repo.getSeatsForFilm(filmId)
        return spots.flatten().map {spot ->
                spot.toSeat()
            }
    }
}