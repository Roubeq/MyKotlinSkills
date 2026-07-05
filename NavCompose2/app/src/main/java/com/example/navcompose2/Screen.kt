package com.example.navcompose2

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Gallery : Screen("gallery")
    object Settings : Screen("settings")
}
