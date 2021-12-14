package com.example.newarchplayground.ui.propertylist.components

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.newarchplayground.PropertyUiModel
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@ExperimentalAnimationApi
@Composable
fun PropertyListScreenBody(
    onRefresh: () -> Unit,
    onFabClick: () -> Unit,
    propertyList: List<PropertyUiModel>,
    isRefreshing: Boolean
) {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        //creating reference for constraint layout
        val (fab) = createRefs()

        //Accompanist has componnets such as SwipeRefresh or CardView
        // It is same as previous Anko of Kotlin but developed by Google
        val swipeToRefreshState = rememberSwipeRefreshState(isRefreshing = isRefreshing)
        SwipeRefresh(
            state = swipeToRefreshState,
            onRefresh = onRefresh,
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                state = rememberLazyListState(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
            ) {
                items(propertyList) { item ->
                    PropertyListItem(property = item, false)
                }
            }
        }
        FloatingActionButton(
            onClick = onFabClick,
            backgroundColor = Color.Green,
            modifier = Modifier.constrainAs(fab) {
                bottom.linkTo(parent.bottom, 8.dp)
                end.linkTo(parent.end, 8.dp)
            }
        ) {
            Icon(Icons.Filled.Add, "Add")
        }
    }
}