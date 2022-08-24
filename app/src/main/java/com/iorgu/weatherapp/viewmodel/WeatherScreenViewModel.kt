package com.iorgu.weatherapp.viewmodel

import androidx.lifecycle.ViewModel
import com.iorgu.weatherapp.model.Weather
import com.iorgu.weatherapp.repository.WeatherRepository
import com.iorgu.weatherapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class WeatherScreenViewModel @Inject constructor(
    private val repository: WeatherRepository
) : ViewModel() {

    suspend fun getWeatherData(city: String): Resource<Weather> {
        return repository.getWeatherData(city)
    }
}