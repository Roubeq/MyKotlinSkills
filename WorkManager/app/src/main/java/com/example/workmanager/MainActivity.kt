package com.example.workmanager

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.net.toUri
import androidx.work.Constraints
import androidx.work.ExistingWorkPolicy
import androidx.work.NetworkType
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkInfo
import androidx.work.WorkManager
import androidx.work.WorkerParameters
import coil3.compose.rememberAsyncImagePainter
import com.example.workmanager.ui.theme.WorkManagerTheme

class MainActivity : ComponentActivity() {
    @SuppressLint("UnrememberedMutableState")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val downloadRequest = OneTimeWorkRequestBuilder<DownloadWorker>()
            .setConstraints(
                Constraints.Builder()
                    .setRequiredNetworkType(
                        NetworkType.CONNECTED
                    )
                    .build()
            )
            .build()
        val colorFilter = OneTimeWorkRequestBuilder<ColorFilterWorker>()
            .build()
        val workManager = WorkManager.getInstance(applicationContext)

        setContent {
            WorkManagerTheme {
                val workInfos = workManager
                    .getWorkInfosForUniqueWorkLiveData(
                        "download"
                    )
                    .observeAsState()
                    .value
                val downloadInfo = remember(key1 = workInfos) {
                    workInfos?.find { it.id == downloadRequest.id }
                }
                val filterInfo = remember(key1 = workInfos) {
                    workInfos?.find { it.id == colorFilter.id }
                }
                val imageUri by derivedStateOf {
                    val downloadUri =
                        downloadInfo?.outputData?.getString(WorkerKeys.IMAGE_URI)?.toUri()
                    val filterUri =
                        filterInfo?.outputData?.getString(WorkerKeys.FILTER_URI)?.toUri()
                    filterUri ?: downloadUri
                }
                // Определяем текущее состояние
                val currentState by derivedStateOf {
                    when {
                        workInfos.isNullOrEmpty() -> "Ready to start"
                        workInfos.any { it.state == WorkInfo.State.RUNNING } -> "Processing..."
                        workInfos.any { it.state == WorkInfo.State.FAILED } -> "Error occurred"
                        workInfos.all { it.state == WorkInfo.State.SUCCEEDED } -> "Completed!"
                        else -> "Pending..."
                    }
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    imageUri?.let { image ->
                        Image(
                            painter = rememberAsyncImagePainter(imageUri),
                            contentDescription = null,
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                        Spacer(Modifier.height(16.dp))
                    }
                    Button(
                        onClick = {
                            workManager.beginUniqueWork(
                                "download",
                                ExistingWorkPolicy.KEEP,
                                downloadRequest
                            )
                                .then(colorFilter)
                                .enqueue()
                        },
                        enabled = downloadInfo?.state != WorkInfo.State.RUNNING
                    ) {
                        Text("Start download")
                    }
                    Spacer(Modifier.height(16.dp))
                    when (downloadInfo?.state) {
                        WorkInfo.State.RUNNING -> Text("Downloading...")
                        WorkInfo.State.FAILED -> Text("Error")
                        WorkInfo.State.CANCELLED -> Text("Cancelled")
                        WorkInfo.State.SUCCEEDED -> Text("Downloaded")
                        WorkInfo.State.ENQUEUED -> Text("enqueued")
                        WorkInfo.State.BLOCKED -> Text("Blocked")
                        null -> Text("null")
                    }
                    Spacer(Modifier.height(16.dp))
                    when (filterInfo?.state) {
                        WorkInfo.State.RUNNING -> Text("Applying filter...")
                        WorkInfo.State.FAILED -> Text("Error")
                        WorkInfo.State.CANCELLED -> Text("Cancelled")
                        WorkInfo.State.SUCCEEDED -> Text("Applied")
                        WorkInfo.State.ENQUEUED -> Text("enqueued")
                        WorkInfo.State.BLOCKED -> Text("Blocked")
                        null -> Text("null")
                    }
                }
            }
        }
    }
}