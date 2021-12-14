package com.example.newarchplayground.ui.sample

import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newarchplayground.ui.common.composable.BaseComposeScreen
import com.example.newarchplayground.ui.common.composable.BaseComposeScreen2
import com.example.newarchplayground.ui.common.successData
import com.example.newarchplayground.ui.sample.components.SampleScreenBody
import com.example.newarchplayground.ui.sample.components.SampleScreenBody2


@Composable
fun SampleScreen(
    scaffoldState: ScaffoldState
) {
    val viewModel: SampleViewModel = hiltViewModel()
    BaseComposeScreen(
        scaffoldState = scaffoldState,
        viewModel = viewModel
    ) {
        Scaffold {
            SampleScreenBody(
                viewModel = viewModel
            )
        }
    }
}

@Composable
fun SampleScreen2(
    scaffoldState: ScaffoldState
) {
    val viewModel: SampleStateViewModel = hiltViewModel()
    BaseComposeScreen2(
        scaffoldState = scaffoldState,
        viewModel = viewModel
    ) { state ->
        Scaffold {
            SampleScreenBody2(
                list = state.successData.list,
                onClick = viewModel.onSampleButtonClick
            )
        }
    }
}