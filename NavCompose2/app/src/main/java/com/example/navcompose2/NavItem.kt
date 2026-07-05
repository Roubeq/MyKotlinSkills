package com.example.navcompose2


import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Collections

sealed class NavItem(
    val title : String,
    val icon: ImageVector,
    val screen: Screen
) {
    object Home : NavItem(title = "Главная", Icons.Default.Home, Screen.Home)
    object Gallery : NavItem(title = "Галерея", Icons.Default.Collections, Screen.Gallery)
    object Settings : NavItem(title = "Настройки", Icons.Default.Settings, Screen.Settings)
}

val bottomNavList = listOf(
    NavItem.Home,
    NavItem.Gallery,
    NavItem.Settings
)