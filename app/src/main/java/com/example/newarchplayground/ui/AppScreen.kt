package com.example.newarchplayground.ui

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.newarchplayground.ui.theme.AndroidPropertyFinderTheme

@ExperimentalAnimationApi
@Composable
fun AppScreen() {
    AndroidPropertyFinderTheme {
        val navController = rememberNavController()
        val scaffoldState = rememberScaffoldState()

        Scaffold(
            scaffoldState = scaffoldState,
            topBar = { TopAppBar(title = { Text("SimpleListWithCOMPOSE") }) },
        ) {
            NavGraph(
                navController = navController,
                scaffoldState = scaffoldState,
            )
        }
    }
}
