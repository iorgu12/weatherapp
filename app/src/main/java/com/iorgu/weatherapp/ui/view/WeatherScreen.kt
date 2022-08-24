package com.iorgu.weatherapp.ui.view

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.iorgu.weatherapp.R
import com.iorgu.weatherapp.model.Weather
import com.iorgu.weatherapp.util.Resource
import com.iorgu.weatherapp.viewmodel.WeatherScreenViewModel

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun WeatherScreen(
    city: String?,
    viewModel: WeatherScreenViewModel = hiltViewModel()
) {

    val weatherData = produceState<Resource<Weather>>(initialValue = Resource.Loading()) {
        value = viewModel.getWeatherData(city!!)
    }.value

    val weather = weatherData.data

    Scaffold(

    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    top = 20.dp,
                    bottom = 20.dp,
                    start = 25.dp,
                    end = 25.dp
                ),
            contentAlignment = Alignment.Center
        ) {
            when (weatherData) {
                is Resource.Success -> {
                    WeatherCard(weather = weather!!)
                }
                is Resource.Error -> {
                    Text(text = weatherData.message!!)
                }
                is Resource.Loading -> {
                    CircularProgressIndicator()
                }
            }
        }
    }
}

@ExperimentalAnimationApi
@ExperimentalCoilApi
@Composable
fun WeatherCard(
    weather: Weather
) {
    val imageUrl = "https://openweathermap.org/img/wn/${weather.weather[0].icon}.png"
    var expanded by remember { mutableStateOf(false) }
    var cardHeight by remember { mutableStateOf(300) }

    Card(
        border = BorderStroke(3.dp, Color.White),
        shape = RoundedCornerShape(20.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(cardHeight.dp)
            .clip(RoundedCornerShape(20.dp))
            .padding(10.dp)
    ) {
        Column(
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.fillMaxSize()
        ) {
            WeatherStateImage(imagerUrl = imageUrl)
            Spacer(modifier = Modifier.height(3.dp))
            Text(text =weather.name.uppercase())
            Spacer(modifier = Modifier.height(3.dp))
            Text(text = weather.weather[0].description)

            Spacer(modifier = Modifier.height(15.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceEvenly,
                modifier = Modifier.fillMaxWidth()
            ) {
                WeatherDataInfoRow(
                    image = R.drawable.img2,
                    description = weather.main.humidity.toString()
                )
                WeatherDataInfoRow(
                    image = R.drawable.img_2,
                    description = weather.wind.speed.toString()
                )
                WeatherDataInfoRow(
                    image = R.drawable.img,
                    description = weather.main.temp.toString()
                )
            }
            Spacer(modifier = Modifier.height(10.dp))
            Icon(
                imageVector = if (expanded) Icons.Default.KeyboardArrowUp else Icons.Default.KeyboardArrowDown,
                contentDescription = "Arrow",
                modifier = Modifier
                    .size(50.dp)
                    .clickable {
                        expanded = !expanded
                        if (cardHeight == 300) cardHeight = 400 else cardHeight = 300
                    }
            )
            Spacer(modifier = Modifier.height(10.dp))
            AnimatedVisibility(visible = expanded) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 20.dp, end = 20.dp)
                ) {
                    WeatherDetailRow("Minimum Temperature: ", weather.main.tempMin.toString())
                    WeatherDetailRow("Maximum Temperature: ", weather.main.tempMax.toString())
                    WeatherDetailRow("Pressure: ", weather.main.pressure.toString())
                    WeatherDetailRow("Wind speed: ", weather.wind.speed.toString())
                }
            }
        }
    }
}

@ExperimentalCoilApi
@Composable
fun WeatherStateImage(
    imagerUrl: String
) {
    Image(
        painter = rememberImagePainter(imagerUrl),
        contentDescription = "Image",
        modifier = Modifier.size(80.dp)
    )
}

@Composable
fun WeatherDataInfoRow(
    image: Int,
    description: String
) {
    Column(
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = image),
            contentDescription = "image",
            modifier = Modifier.size(50.dp)
        )
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = description)
    }
}

@Composable
fun WeatherDetailRow(
    text: String,
    description: String
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = text)
        Text(text = description)
    }
}