package com.example.newarchplayground.ui.sample.components

import com.example.newarchplayground.ui.sample.SampleContract
import com.example.newarchplayground.ui.sample.SampleViewModel
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp


@Composable
fun SampleScreenBody(state: SampleContract.State, viewModel: SampleViewModel) {
    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 20.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            state.list.map {
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