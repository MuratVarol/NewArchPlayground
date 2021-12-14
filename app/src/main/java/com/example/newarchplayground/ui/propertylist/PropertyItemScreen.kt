package com.example.newarchplayground.ui.propertylist

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.ui.common.composable.BaseComposeScreen
import com.example.newarchplayground.ui.common.successData
import com.example.newarchplayground.ui.propertylist.components.PropertyListItem
import com.example.newarchplayground.ui.propertylist.components.PropertyListScreenBody


@ExperimentalAnimationApi
@Composable
fun PropertyListScreen(scaffoldState: ScaffoldState) {
    val viewModel: MainViewModel = hiltViewModel()

    BaseComposeScreen(
        scaffoldState = scaffoldState,
        viewModel = viewModel,
        renderOnLoading = {
            val loadingList = mutableListOf<String>().apply {
                repeat(9) {
                    add("Loading")
                }
            }
            //first loading items for shimmer Effect
            LazyColumn(
                state = rememberLazyListState(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
            ) {
                items(loadingList) {
                    PropertyListItem(property = PropertyUiModel("", "", "", ""), true)
                }
            }
        }
    ) { state ->
        PropertyListScreenBody(
            onRefresh = { viewModel.onRefresh() },
            onFabClick = { viewModel.onFabClicked() },
            isRefreshing = state.successData.isRefreshing,
            propertyList = state.successData.propertyList
        )
    }
}

