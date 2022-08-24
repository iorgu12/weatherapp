package com.iorgu.weatherapp.ui.view

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.google.firebase.firestore.FirebaseFirestore
import com.iorgu.weatherapp.ui.theme.Purple500

@Composable
fun MainScreen(navController: NavController) {

    var city by remember { mutableStateOf("") }

    Scaffold(

    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top,
            modifier = Modifier
                .fillMaxSize()
                .padding(20.dp)
        ) {
            CommonTextField(
                text = city,
                placeholder = "Write Your City",
                onValueChange = { city = it }
            )
            Spacer(modifier = Modifier.height(20.dp))
            CommonButton {
                if (city.isNotBlank()) {
                    navController.navigate(
                        route = "weather_screen/$city"
                    )
                }
            }
            Spacer(modifier = Modifier.height(30.dp))
            Text(text = "Favourites")
            Spacer(modifier = Modifier.height(15.dp))
            CommonButton1 {

                    navController.navigate(
                        route = "weather_screen/Barcelona"
                    )

            }
            CommonButton2 {

                    navController.navigate(
                        route = "weather_screen/Bucharest"
                    )

            }
        }
    }
    lateinit var firestore : FirebaseFirestore
}

@Composable
fun CommonTextField(
    text: String,
    placeholder: String,
    onValueChange: (String) -> Unit
) {
    OutlinedTextField(
        value = text,
        onValueChange = { onValueChange(it) },
        label = { Text(text = placeholder) },
        maxLines = 1,
        singleLine = true,
        colors = TextFieldDefaults.outlinedTextFieldColors(
            focusedBorderColor = Purple500,
            cursorColor = Color.Black
        ),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 10.dp, end = 10.dp)
    )
}

@Composable
fun CommonButton(
    onClickListener: () -> Unit
) {
    Button(
        onClick = {
            onClickListener()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(
                start = 10.dp,
                end = 10.dp
            )
    ) {
        Text(text = "Show Weather")
    }
}

@Composable
fun CommonButton1(
    onClickListener: () -> Unit
) {
    Button(
        onClick = {
            onClickListener()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(
                start = 10.dp,
                end = 10.dp
            )
    ) {
        Text(text = "Barcelona")
    }}
@Composable
fun CommonButton2(
    onClickListener: () -> Unit
) {
    Button(
        onClick = {
            onClickListener()
        },
        colors = ButtonDefaults.buttonColors(backgroundColor = Color.White),
        shape = RoundedCornerShape(15.dp),
        modifier = Modifier
            .fillMaxWidth()
            .height(54.dp)
            .padding(
                start = 10.dp,
                end = 10.dp
            )
    ) {
        Text(text = "Bucharest",
        color = Color.Black)
    }}