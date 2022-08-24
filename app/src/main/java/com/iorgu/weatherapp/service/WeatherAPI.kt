package com.iorgu.weatherapp.service

import com.iorgu.weatherapp.model.Weather
import com.iorgu.weatherapp.util.Constants.API_KEY
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherAPI {

    @GET("data/2.5/weather")
    suspend fun getWeatherData(
        @Query("q") city: String,
        @Query("units") units: String = "metric",
        @Query("appid") appId: String = API_KEY // your api key
    ): Weather

}