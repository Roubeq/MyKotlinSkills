package com.example.navcompose2

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

@Composable
fun NavGraph(
    navHostController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navHostController,
        startDestination = Screen.Home.route,
        modifier = modifier
    ) {
        composable(Screen.Home.route) {
            Text("Homeeeeeeeeeee")
        }
        composable(Screen.Gallery.route) {
            Text("Galleryyyyyyyy")
        }
        composable(Screen.Settings.route) {
            Text("Settingsssssss")
        }
    }
}