package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream
import java.io.IOException
import kotlin.random.Random

class DownloadWorker(
    private val context: Context,
    private val workerParams: WorkerParameters
) : CoroutineWorker(
    context,
    workerParams
) {
    override suspend fun doWork(): Result {
        Log.d("DownloadWorker","Началась загрузка")
        showNotification()
        delay(5000L)
        val response = FileApi.instance.getDownloadPhoto()
        response.body()?.let { body ->
            return withContext(Dispatchers.IO) {
                val file = File(
                    context.cacheDir,
                    "cartinka.jpg"
                )
                val outputStream = FileOutputStream(file)
                outputStream.use { stream ->
                    try {
                        stream.write(body.bytes())
                    } catch (e: IOException) {
                        return@withContext Result.failure(
                            workDataOf(
                                WorkerKeys.ERROR_MSG to e.localizedMessage
                            )
                        )
                    }
                }
                Result.success(
                    workDataOf(
                        WorkerKeys.IMAGE_URI to file.toUri().toString()
                    )
                )
            }
        }
        if (!response.isSuccessful) {
            if(response.code().toString().startsWith("5")) {
                return Result.retry()
            }
            return Result.failure(
                workDataOf(
                    WorkerKeys.ERROR_MSG to "ERRORRRRRRR"
                )
            )
        }
        return Result.failure(
            workDataOf(
                WorkerKeys.ERROR_MSG to "Unknown error"
            )
        )
    }

    private suspend fun showNotification() {
        setForeground(
            ForegroundInfo(
                Random.nextInt(),
                NotificationCompat.Builder(
                    context, DownloadApplication.NOTIFICATION_CHANNEL_ID
                )
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setContentText("Downloading...")
                    .setContentTitle("Downloading in progress")
                    .build()
            )
        )
    }
}