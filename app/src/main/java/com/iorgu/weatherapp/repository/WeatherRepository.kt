package com.iorgu.weatherapp.repository

import com.iorgu.weatherapp.model.Weather
import com.iorgu.weatherapp.service.WeatherAPI
import com.iorgu.weatherapp.util.Resource
import dagger.hilt.android.scopes.ActivityScoped
import java.lang.Exception
import javax.inject.Inject

@ActivityScoped
class WeatherRepository @Inject constructor(
    private val api: WeatherAPI
) {
    suspend fun getWeatherData(city: String): Resource<Weather> {
        val response = try {
            api.getWeatherData(city = city)
        } catch (e: Exception) {
            return Resource.Error("Try Again.")
        }
        return Resource.Success(response)
    }

}