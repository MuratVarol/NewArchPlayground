package com.example.newarchplayground.ui.sample

import com.example.newarchplayground.ui.base.composable.BaseComposeScreen
import com.example.newarchplayground.ui.base.successData
import com.example.newarchplayground.ui.sample.components.SampleScreenBody
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel


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
                state = state.successData,
                viewModel = viewModel
            )
        }
    }
}