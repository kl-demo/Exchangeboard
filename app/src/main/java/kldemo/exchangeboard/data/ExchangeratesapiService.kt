package kldemo.exchangeboard.data

import retrofit2.Call
import retrofit2.http.GET

interface ExchangeratesapiService {
    @GET("latest")
    fun getLatestRates(): Call<ExchangeratesapiBase>
}