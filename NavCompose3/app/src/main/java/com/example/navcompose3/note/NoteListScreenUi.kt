package com.example.navcompose3.note

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.sp

@Composable
fun NoteListScreenUi(
    onNoteClick: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
    ) {
        items(sampleNotes) { note ->
            Column(
                modifier = Modifier
                    .fillParentMaxWidth()
                    .background(note.color)
                    .clickable {
                        onNoteClick(note.id)
                    }
            ) {
                Text(
                    text = note.title,
                    fontSize = 18.sp
                )
                Text(
                    text = note.content,
                )
            }
        }
    }
}