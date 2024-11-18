package com.example.a6starter.ui.screens.main

import androidx.lifecycle.ViewModel
import com.example.a6starter.data.model.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

data class MainScreenViewState(
    val property1: Unit = TODO("Specify your Main Screen View State")
)

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val myRepository: MyRepository,
) : ViewModel() {
}