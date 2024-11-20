package com.example.a6starter.ui.screens.main

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInVertically
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun MainScreen(viewModel: MainScreenViewModel = hiltViewModel()) {
    val viewState = viewModel.viewState.collectAsState().value
    var punchlineVisible by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier.animateContentSize()
        ) {
            Button(onClick = {
                punchlineVisible = false
                viewModel.getJoke()
            }) { Text("Get new joke") }
            when (viewState) {
                MainScreenViewState.Error -> Text("Could not load joke", color = Color.Red)
                is MainScreenViewState.Loaded -> {
                    MainScreen(
                        viewState,
                        punchlineVisible,
                        { punchlineVisible = !punchlineVisible })
                }

                MainScreenViewState.Loading -> {
                    CircularProgressIndicator()
                }

                MainScreenViewState.Idle -> {}
            }
        }
    }

}

@Composable
private fun ColumnScope.MainScreen(
    loaded: MainScreenViewState.Loaded,
    punchlineVisible: Boolean,
    togglePunchline: () -> Unit,
) {
    loaded.setup?.let { setup ->
        loaded.punchline?.let { punchline ->
            Button(onClick = togglePunchline) {
                Text("Toggle punchline")
            }
            Text(setup)
            AnimatedVisibility(
                punchlineVisible,
                enter = slideInVertically() + fadeIn()
            ) {
                Text(punchline)
            }
        }
    }
    loaded.joke?.let {
        Text(loaded.joke)
    }
}