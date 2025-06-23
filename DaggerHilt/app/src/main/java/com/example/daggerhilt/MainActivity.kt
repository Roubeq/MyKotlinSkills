package com.example.daggerhilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.Text
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.daggerhilt.ui.theme.DaggerHiltTheme
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            DaggerHiltTheme {
                val viewModel = hiltViewModel<MainViewModel>()
            }
        }
    }
}
