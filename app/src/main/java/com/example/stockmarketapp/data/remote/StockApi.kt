package com.example.stockmarketapp.data.remote

import okhttp3.ResponseBody
import retrofit2.http.GET
import retrofit2.http.Query

//for downloading CSV file
interface StockApi {

    @GET("query?=LISTING_STATUS")
    suspend fun getListings(
        @Query("apikey") apikey : String
    ):ResponseBody

    companion object{
        const val API_KEY = "SVIPOOQZEHUYSA0M"
        const val BASE_URL = "https://aplphavantage.co"
    }
}