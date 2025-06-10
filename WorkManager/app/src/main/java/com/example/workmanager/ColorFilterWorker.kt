package com.example.workmanager

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.LightingColorFilter
import android.graphics.Paint
import androidx.core.net.toFile
import androidx.core.net.toUri
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext
import java.io.File
import java.io.FileOutputStream

class ColorFilterWorker(
    private val context: Context,
    private val workerParams: WorkerParameters
) : CoroutineWorker(
    context,
    workerParams
) {
    override suspend fun doWork(): Result {
        val imageFile = workerParams.inputData.getString(WorkerKeys.IMAGE_URI)
            ?.toUri()
            ?.toFile()
        delay(5000L)
        return imageFile?.let { file ->
            val bmp = BitmapFactory.decodeFile(file.absolutePath)
            val bmpConfig = Bitmap.Config.ALPHA_8

            val resultImage = bmp.copy(
                bmpConfig,
                true
            )
            val paint = Paint()
            paint.colorFilter = LightingColorFilter(0x08FF04, 1)
            val canvas = Canvas(
                resultImage
            )
            canvas.drawBitmap(resultImage, 0f, 0f, paint)
            withContext(Dispatchers.IO) {
                val file = File(
                    context.cacheDir, "greenFilterPhoto.jpg"
                )
                val outputStream = FileOutputStream(
                    file
                )
                val successful = resultImage.compress(
                    Bitmap.CompressFormat.JPEG,
                    90,
                    outputStream
                )
                if(successful) {
                    Result.success(
                        workDataOf(
                            WorkerKeys.FILTER_URI to file.toUri().toString()
                        )
                    )
                }
                else Result.failure()
            }
        }?: return Result.failure()
    }

}