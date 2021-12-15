package com.example.newarchplayground.ui.sample

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newarchplayground.ui.common.composable.BaseComposeScreen
import com.example.newarchplayground.ui.sample.components.SampleScreenBody


@Composable
fun SampleScreen(
    scaffoldState: ScaffoldState
) {
    val viewModel: SampleViewModel = hiltViewModel()
    BaseComposeScreen(
        scaffoldState = scaffoldState,
        viewModel = viewModel
    ) { state ->
        Scaffold {
            SampleScreenBody(
                list = state.list,
                onClick = viewModel.onSampleButtonClick
            )
        }
    }
}