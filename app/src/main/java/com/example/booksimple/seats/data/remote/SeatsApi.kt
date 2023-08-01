package com.example.booksimple.seats.data.remote

import com.example.booksimple.seats.data.remote.response.Spot
import com.example.booksimple.seats.data.remote.response.SeatsResponse
import kotlinx.coroutines.delay
import org.json.JSONObject

interface SeatsApi {
    suspend fun getSeatsForFilm(filmId: Int): SeatsResponse
}

class SeatsApiMockImpl : SeatsApi {
    override suspend fun getSeatsForFilm(filmId: Int): SeatsResponse {
        delay(2000)
        val mockJsonStr: String = "{" +
                "count:24," +
                "row:6," +
                "col:4," +
                "empty_row:1," +
                "empty_col:1," +
                "seats:[" +
                "[{'A1':1},{'A2':1},{'AY1':0},{'A3':1},{'A4':1}]," +
                "[{'B1':1},{'B2':1},{'BY1':0},{'B3':1},{'B4':1}]," +
                "[{'C1':1},{'C2':1},{'CY1':0},{'C3':1},{'C4':1}]," +
                "[{'DX1':1},{'DX2':1},{'DXY1':0},{'DX3':1},{'DX4':1}]," +
                "[{'D1':1},{'D2':1},{'DY1':0},{'D3':1},{'D4':1}]," +
                "[{'E1':1},{'E2':1},{'EY1':0},{'E3':1},{'E4':1}]," +
                "[{'F1':1},{'F2':1},{'FY1':0},{'F3':1},{'F4':1}]" +
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
                val spot = Spot(
                    position = joSeat.getString(keysIter.next()),
                    status = joSeat.getInt(keysIter.next())
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