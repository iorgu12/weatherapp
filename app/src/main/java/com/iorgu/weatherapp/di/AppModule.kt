package com.iorgu.weatherapp.di

import com.iorgu.weatherapp.repository.WeatherRepository
import com.iorgu.weatherapp.service.WeatherAPI
import com.iorgu.weatherapp.util.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideWeatherRepository(api: WeatherAPI) = WeatherRepository(api)

    @Singleton
    @Provides
    fun provideWeatherApi(): WeatherAPI {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(WeatherAPI::class.java)
    }

}