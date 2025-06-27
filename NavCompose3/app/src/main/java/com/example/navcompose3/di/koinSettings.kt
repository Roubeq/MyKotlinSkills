package com.example.navcompose3.di

import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.navcompose3.note.NoteDetailViewModel
import org.koin.core.module.dsl.viewModelOf
import org.koin.dsl.module

val appModule = module {
    viewModelOf(::NoteDetailViewModel)
}