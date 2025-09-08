package com.deaelum.android.myweatherapp.presentation.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.deaelum.android.myweatherapp.utils.Config.getWeatherIcon
import com.deaelum.android.myweatherapp.domain.model.Weather
import com.deaelum.android.myweatherapp.presentation.viewModel.WeatherViewModel
import com.deaelum.android.myweatherapp.ui.theme.WeatherBlue
import com.deaelum.android.myweatherapp.ui.theme.WeatherLightBlue
import kotlin.math.roundToInt

@Composable
fun WeatherScreen(
    cityName: String,
    onNavigateBack: () -> Unit,
    viewModel: WeatherViewModel = hiltViewModel()
) {

    val state by viewModel.state.collectAsState()

    LaunchedEffect(cityName) {
        viewModel.getWeather(cityName)
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.verticalGradient(
                    colors = listOf(
                        WeatherBlue,
                        WeatherLightBlue,
                        Color.White
                    )
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(
                    onClick = onNavigateBack
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
                Text(
                    text = "Weather Details",
                    fontSize = 20.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Color.White,
                    modifier = Modifier.padding(start = 8.dp)
                )

                Spacer(modifier = Modifier.weight(1f))

                IconButton(
                    onClick = { viewModel.onFavIconClick(cityName) }
                ) {
                    Icon(
                        imageVector = Icons.Outlined.FavoriteBorder,
                        contentDescription = "Back",
                        tint = Color.White
                    )
                }
            }

            when {
                state.isLoading -> {
                    LoadingView()
                }

                state.weather != null -> {
                    ShowWeatherDetails(state.weather!!)
                }

                state.error != null -> {
                    ErrorView(state.error!!) {
                        viewModel.getWeather(cityName)
                    }
                }
            }
        }
    }
}

@Composable
fun ShowWeatherDetails(weather: Weather) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Main Weather Card
        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(defaultElevation = 12.dp)
        ) {
            Column(
                modifier = Modifier
                    .padding(32.dp)
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = weather.cityName,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.Bold,
                    color = WeatherBlue
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = getWeatherIcon(weather.description),
                    fontSize = 80.sp
                )

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = "${weather.temperature.roundToInt()}°C",
                    fontSize = 48.sp,
                    fontWeight = FontWeight.Bold,
                    color = WeatherBlue
                )

                Text(
                    text = weather.description,
                    fontSize = 18.sp,
                    color = Color.Gray,
                    textAlign = TextAlign.Center
                )

            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            WeatherDetailCard(
                modifier = Modifier.weight(1f),
                title = "Humidity",
                value = "${weather.humidity}%",
                icon = "💧"
            )
            WeatherDetailCard(
                modifier = Modifier.weight(1f),
                title = "Feels Like",
                value = "${weather.temperature.roundToInt()}°C",
                icon = "🌬️"
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            WeatherDetailCard(
                modifier = Modifier.weight(1f),
                title = "Min Temp",
                value = "${weather.tempMin.roundToInt()}°C",
                icon = "🔽"
            )

            WeatherDetailCard(
                modifier = Modifier.weight(1f),
                title = "Max Temp",
                value = "${weather.tempMax.roundToInt()}°C",
                icon = "🔼"
            )
        }
    }
}

@Composable
fun ErrorView(error: String, onClick: () -> Unit) {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(24.dp),
            shape = RoundedCornerShape(12.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            )
        ) {
            Column(
                modifier = Modifier.padding(24.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Row(
                    Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "❌",
                        style = MaterialTheme.typography.titleMedium,
                    )
                    Spacer(modifier = Modifier.width(5.dp))
                    Text(
                        text = "Error",
                        style = MaterialTheme.typography.titleLarge,
                        fontWeight = FontWeight.Bold,
                        color = Color.Red
                    )
                }
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = error,
                    style = MaterialTheme.typography.bodyMedium,
                    textAlign = TextAlign.Center,
                    color = Color.Gray
                )
                Spacer(modifier = Modifier.height(24.dp))
                Button(
                    onClick = onClick,
                    //viewModel.getWeather(cityName)
                    colors = ButtonDefaults.buttonColors(
                        containerColor = WeatherBlue
                    )
                ) {
                    Text("Try Again")
                }
            }
        }
    }
}

@Composable
fun LoadingView() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CircularProgressIndicator(
                color = Color.White,
                strokeWidth = 3.dp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Loading weather data...",
                color = Color.White,
                fontSize = 16.sp
            )
        }
    }
}

@Composable
fun WeatherDetailCard(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    icon: String
) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(8.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.White.copy(alpha = 0.9f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = icon,
                fontSize = 24.sp
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = title,
                fontSize = 14.sp,
                color = Color.Gray
            )
            Text(
                text = value,
                fontSize = 18.sp,
                fontWeight = FontWeight.SemiBold,
                color = WeatherBlue
            )
        }
    }
}