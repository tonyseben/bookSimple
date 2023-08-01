package com.example.booksimple.seats.data.remote

import android.util.Log
import com.example.booksimple.seats.data.remote.response.Spot
import com.example.booksimple.seats.data.remote.response.SeatsResponse
import kotlinx.coroutines.delay
import org.json.JSONObject
import javax.inject.Inject

interface SeatsApi {
    suspend fun getSeatsForFilm(filmId: Int): SeatsResponse
}

class SeatsApiMockImpl @Inject constructor() : SeatsApi {
    override suspend fun getSeatsForFilm(filmId: Int): SeatsResponse {
        delay(2000)
        val mockJsonStr: String = "{" +
                "count:24," +
                "row:6," +
                "col:8," +
                "empty_row:1," +
                "empty_col:1," +
                "seats:[" +
                "[{'A1':1},{'A2':1},{'A3':1},{'A4':1},  {'AY1':0},  {'A5':1},{'A6':1},{'A7':1},{'A8':1}]," +
                "[{'B1':1},{'B2':1},{'B3':1},{'B4':1},  {'BY1':0},  {'B5':1},{'B6':1},{'B7':1},{'B8':1}]," +
                "[{'C1':1},{'C2':1},{'C3':2},{'C4':2},  {'CY1':0},  {'C5':2},{'C6':2},{'C7':2},{'C8':2}]," +

                "[{'DX1':0},{'DX2':0},{'DX3':0},{'DX4':0},{'DXY1':0},{'DX5':0},{'DX6':0},{'DX7':0},{'DX8':0}]," +

                "[{'D1':1},{'D2':1},{'D3':1},{'D4':1},  {'DY1':0},  {'D5':1},{'D6':1},{'D7':1},{'D8':1}]," +
                "[{'E1':1},{'E2':1},{'E3':1},{'E4':1},  {'EY1':0},  {'E5':1},{'E6':1},{'E7':1},{'E8':1}]," +
                "[{'F1':1},{'F2':1},{'F3':1},{'F4':2},  {'FY1':0},  {'F5':2},{'F6':2},{'F7':2},{'F8':2}]" +
                "]" +
                "}"

        val joData = JSONObject(mockJsonStr)
        val jaSeats = joData.getJSONArray("seats")
        val seating = mutableListOf<List<Spot>>()

        for (i in 0 until jaSeats.length()) {
            val jaRows = jaSeats.getJSONArray(i)
            val rows = mutableListOf<Spot>()

            for (j in 0 until jaRows.length()) {
                val joSeat = jaRows.getJSONObject(j)
                val keysIter = joSeat.keys()
                val position = keysIter.next()
                Log.d("TEST", "getSeatsForFilm $joSeat")
                val spot = Spot(
                    position = position,
                    status = joSeat.getInt(position)
                )
                rows.add(spot)
            }
            seating.add(rows)
        }

        return SeatsResponse(
            count = joData.getInt("count"),
            row = joData.getInt("row"),
            col = joData.getInt("col"),
            empty_row = joData.getInt("empty_row"),
            empty_col = joData.getInt("empty_col"),
            seating = seating.toList()
        )
    }

}