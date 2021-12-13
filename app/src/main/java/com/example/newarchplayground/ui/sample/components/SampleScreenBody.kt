package com.example.newarchplayground.ui.sample.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.newarchplayground.ui.common.successData
import com.example.newarchplayground.ui.sample.SampleUIState
import com.example.newarchplayground.ui.sample.SampleStateViewModel
import com.example.newarchplayground.ui.sample.SampleViewModel


@Composable
fun SampleScreenBody(viewModel: SampleViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            val state =
                remember(viewModel) { viewModel.uiState }.collectAsState(initial = SampleUIState())
            state.value.list.map {
                Button(
                    onClick = {
                        viewModel.onSampleButtonClick(it)
                    },
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text(text = it)
                }
            }
        }
    }
}

@Composable
fun SampleScreenBody2(viewModel: SampleStateViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            viewModel.currentState.successData.list.map {
                Button(
                    onClick = {
                        viewModel.onSampleButtonClick(it)
                    },
                    modifier = Modifier.padding(top = 20.dp)
                ) {
                    Text(text = it)
                }
            }
        }
    }
}