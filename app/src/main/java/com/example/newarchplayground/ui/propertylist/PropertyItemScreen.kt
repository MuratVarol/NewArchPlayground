package com.example.newarchplayground.ui.propertylist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.*
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newarchplayground.ui.propertylist.components.PropertyListScreenBody


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun PropertyListScreen(scaffoldState: ScaffoldState) {
//    val viewModel = hiltViewModel<MainViewModel>()
//    BaseComposeScreen(
//        scaffoldState = scaffoldState,
//        viewModel = viewModel
//    ) {

        // collectAsState same as toObservable everytime data change; UI will be updated
//        val propertyList by viewModel.propertyList.collectAsState()

        //remember: if we will update Composable UI value later: need to cascade it with "remember"
        // to notify UI after value changed
//        val loadingList = remember {
//            mutableStateListOf<String>().apply {
//                add("Loading")
//                add("Loading")
//                add("Loading")
//                add("Loading")
//                add("Loading")
//                add("Loading")
//                add("Loading")
//                add("Loading")
//                add("Loading")
//            }
//        }
//        PropertyListScreenBody(
//            viewModel = viewModel
//        )
//    }
}

