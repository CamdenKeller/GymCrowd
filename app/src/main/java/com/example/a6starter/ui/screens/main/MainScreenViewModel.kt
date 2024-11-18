package com.example.a6starter.ui.screens.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.a6starter.data.model.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import javax.inject.Inject

data class MainScreenViewState(
    val username: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val errorMessage: String? = null,
)

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val myRepository: MyRepository,
) : ViewModel() {
    private val usernameFlow = MutableStateFlow("")
    private val passwordFlow = MutableStateFlow("")
    private val confirmPasswordFlow = MutableStateFlow("")

    val mainScreenViewState: StateFlow<MainScreenViewState> =
        combine(
            usernameFlow,
            passwordFlow,
            confirmPasswordFlow
        ) { username, password, confirmPassword ->
            val baseViewState = MainScreenViewState(username, password, confirmPassword)
            if (username == password) {
                return@combine baseViewState.copy(errorMessage = "Username may not be the same as password")
            }
            if (password.length < 8) {
                return@combine baseViewState.copy(errorMessage = "Password does not meet minimum length requirement")
            }
            if (password != confirmPassword) {
                return@combine baseViewState.copy(errorMessage = "Password does not match confirm password")
            }

            return@combine baseViewState.copy(errorMessage = null)
        }.stateIn(
            viewModelScope, SharingStarted.Eagerly,
            MainScreenViewState("", "", "")
        )

    fun onUsernameChange(username: String) {
        usernameFlow.update { username }
    }

    fun onPasswordChange(password: String) {
        passwordFlow.update { password }
    }

    fun onConfirmPasswordChange(confirmPassword: String) {
        confirmPasswordFlow.update { confirmPassword }
    }
}