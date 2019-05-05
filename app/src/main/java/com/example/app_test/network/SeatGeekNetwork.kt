package com.example.app_test.network

import com.example.app_test.data.SeatGeekData
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface SeatGeekNetwork{

    @GET("events")
    fun getSearchEvents(
        @Query("q") search: String,
        @Query("page") page: Int,
        @Query("client_id") clientId: String = "MTY1MTQ5Njl8MTU1NzA3NDAyNi40OA"
    ): Single<SeatGeekData>
}