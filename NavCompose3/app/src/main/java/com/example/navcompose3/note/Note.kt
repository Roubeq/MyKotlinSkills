package com.example.navcompose3.note

import android.graphics.RadialGradient
import androidx.compose.ui.graphics.Color
import kotlin.random.Random

data class Note(
    val id: Int,
    val title: String,
    val content: String,
    val color: Color
)

val sampleNotes = List(100) {
    Note(
        id = it,
        title = "Note $it",
        content = "Content $it",
        color = Color(Random.nextLong(0xFFFFFFFF)).copy(0.5f)
    )
}
