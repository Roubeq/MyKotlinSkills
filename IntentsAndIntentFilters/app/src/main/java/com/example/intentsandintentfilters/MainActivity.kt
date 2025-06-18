package com.example.intentsandintentfilters

import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import coil3.Uri
import coil3.compose.AsyncImage
import com.example.intentsandintentfilters.ui.theme.IntentsAndIntentFiltersTheme

class MainActivity : ComponentActivity() {
    val viewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            IntentsAndIntentFiltersTheme {
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    viewModel.uri?.let {
                        AsyncImage(
                            model = viewModel.uri,
                            contentDescription = null
                        )
                    }
                    Button(
                        onClick = {
//                            Intent(this@MainActivity, SecondActivity::class.java).also {
//                                startActivity(it)
//                            }
//                            Intent(Intent.ACTION_MAIN).also {
//                                it.`package` = "com.google.android.youtube"
//                                startActivity(it)
//                            }
//                            val intent = Intent(Intent.ACTION_SEND).apply {
//                                type = "text/plain"
//                                putExtra(Intent.EXTRA_EMAIL, arrayOf("arteem.blinov.2005@gmail.com"))
//                                putExtra(Intent.EXTRA_SUBJECT, "Subject")
//                                putExtra(Intent.EXTRA_TEXT, "Уважаемый артём сергеевич")
//                            }
//                            if(intent.resolveActivity(packageManager) != null) {
//                                startActivity(intent)
//                            }
                        }
                    ) {
                        Text(text = "Button")
                    }
                }
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM, android.net.Uri::class.java)
        } else {
            intent?.getParcelableExtra(Intent.EXTRA_STREAM)
        }
        viewModel.updateUri(uri)
    }
}
