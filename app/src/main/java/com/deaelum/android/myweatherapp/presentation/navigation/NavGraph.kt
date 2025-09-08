package com.deaelum.android.myweatherapp.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraph
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.deaelum.android.myweatherapp.presentation.ui.HomeScreen
import com.deaelum.android.myweatherapp.presentation.ui.SplashScreen
import com.deaelum.android.myweatherapp.presentation.ui.WeatherScreen
import com.deaelum.android.myweatherapp.utils.Screen

@Composable
fun NavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route,
        modifier = modifier
    ) {
        composable(Screen.Splash.route){
            SplashScreen(onNavigateToHome = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Splash.route) {
                        inclusive = true
                    }
                }
            })
        }
        composable(Screen.Home.route){
            HomeScreen(
                onNavigateToWeather = {
                    navController.navigate(Screen.WeatherDetails.route + "/$it")
                }
            )
        }
        composable(Screen.WeatherDetails.route + "/{cityName}") { backStackEntry ->
            val cityName = backStackEntry.arguments?.getString("cityName")
            if (cityName != null) {
                WeatherScreen(cityName = cityName,
                    onNavigateBack = {
                        navController.popBackStack()
                    })
            }
        }
    }

}