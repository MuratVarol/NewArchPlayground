package com.example.newarchplayground.ui

import com.example.newarchplayground.ui.sample.SampleScreen
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.newarchplayground.ui.components.PropertyListUi

object PfDestination {
    const val SAMPLE_PATH = "sample-screen"
    const val PROPERTY_LIST = "property-list"
}

@Composable
fun NavGraph(
    navController: NavHostController = rememberNavController(),
    scaffoldState: ScaffoldState,
    startDestination: String = PfDestination.SAMPLE_PATH
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(PfDestination.SAMPLE_PATH) {
            SampleScreen(scaffoldState = scaffoldState)
        }
        composable(PfDestination.PROPERTY_LIST) {
            PropertyListUi()
        }
    }
}
