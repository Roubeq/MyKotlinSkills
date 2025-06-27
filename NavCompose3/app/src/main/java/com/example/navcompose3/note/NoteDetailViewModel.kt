package com.example.navcompose3.note

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class NoteDetailViewModel(
    private val noteId: Int
): ViewModel() {

    private val _noteState = MutableStateFlow(
        sampleNotes.first { it.id == noteId}
    )
    val noteState = _noteState.asStateFlow()
}