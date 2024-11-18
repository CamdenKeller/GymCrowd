package com.example.a6starter.ui.screens.main

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()) {
    val currentViewState = viewModel.mainScreenViewState.collectAsState().value

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Username")
        TextField(value = currentViewState.username, onValueChange = viewModel::setUsername)
        Text("Password")
        TextField(value = currentViewState.password, onValueChange = viewModel::setPassword)
        Text("Confirm password")
        TextField(
            value = currentViewState.confirmPassword,
            onValueChange = viewModel::setConfirmPassword
        )
        currentViewState.errorMessage?.let { error ->
            Spacer(Modifier.height(16.dp))
            Text(error, color = Color.Red)
        }

    }
}