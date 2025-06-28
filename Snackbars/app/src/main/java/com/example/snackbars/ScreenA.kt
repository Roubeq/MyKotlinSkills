package com.example.snackbars

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch


@Composable
fun ScreenA(
    onNavigate: () -> Unit,
    viewModel: ScreenAViewModel = viewModel<ScreenAViewModel>(),
    modifier: Modifier = Modifier
) {
    val scope = rememberCoroutineScope()
    Column(
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(
            onClick = {
                scope.launch {
                    SnackbarController.sendEvent(
                        event = SnackbarEvent(
                            message = "Ohohoho hello world!"
                        )
                    )
                }
            }
        ) {
            Text("Click me!")
        }
        Button(
            onClick = {
                viewModel.showSnackbar()
            }
        ) {
            Text("Click me!(ViewModel)")
        }
        Button(
            onClick = onNavigate
        ) {
            Text("Go to ScreenB")
        }

    }
}