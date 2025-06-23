package com.example.daggerhilt

import androidx.lifecycle.ViewModel
import com.example.daggerhilt.domain.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val myRepository: MyRepository
) : ViewModel() {
}