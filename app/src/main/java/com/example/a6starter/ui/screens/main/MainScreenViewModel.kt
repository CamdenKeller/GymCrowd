package com.example.a6starter.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a6starter.data.model.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

sealed class MainScreenViewState {
    data class Loaded(
        val setup: String?,
        val punchline: String?,
        val joke: String?,
    ) : MainScreenViewState()

    data object Loading : MainScreenViewState()
    data object Error : MainScreenViewState()
    data object Idle : MainScreenViewState()
}


@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val myRepository: MyRepository,
) : ViewModel() {
    private val _viewState = MutableStateFlow<MainScreenViewState>(MainScreenViewState.Idle)
    val viewState = _viewState.asStateFlow()

    fun getJoke() = viewModelScope.launch {
        _viewState.update { MainScreenViewState.Loading }

        val jokeCall = myRepository.getSafeJoke()
        val jokeResponse = jokeCall.body()
        jokeResponse?.let { joke ->
            _viewState.update {
                MainScreenViewState.Loaded(
                    setup = joke.setup,
                    punchline = joke.delivery,
                    joke = joke.joke
                )
            }
        } ?: run {
            _viewState.update { MainScreenViewState.Error }
        }
    }
}