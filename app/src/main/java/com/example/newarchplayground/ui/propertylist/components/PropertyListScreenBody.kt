package com.example.newarchplayground.ui.propertylist.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.ui.propertylist.MainViewModel
import com.example.newarchplayground.util.extensions.longToast
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@ExperimentalAnimationApi
@Composable
fun PropertyListScreenBody(viewModel: MainViewModel) {

    //reaching context in Composable
    val context = LocalContext.current

    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        //creating reference for constraint layout
        val (loading) = createRefs()
        val (fab) = createRefs()

        //showing progressbar with visibility animation
        AnimatedVisibility(visible = false
            /**isLoading.value*/
            /**isLoading.value*/
            /**isLoading.value*/
            /**isLoading.value*/
            , modifier = Modifier
                .constrainAs(loading) {
                    centerVerticallyTo(parent)
                    centerHorizontallyTo(parent)
                }
                .wrapContentSize()
                .padding(top = 4.dp)
                .zIndex(2f)) {
            CircularProgressIndicator()
        }

        //first loading items for shimmer Effect
        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 8.dp),
        ) {
//            items(loadingList) {
//                PropertyListItem(property = PropertyUiModel("", "", "", ""), isLoading)
//            }
        }

        //Accompanist has componnets such as SwipeRefresh or CardView
        // It is same as previous Anko of Kotlin but developed by Google
//        val swipeToRefreshState = rememberSwipeRefreshState(isLoading)
//        SwipeRefresh(
//            state = swipeToRefreshState,
//            onRefresh = {
//                viewModel.getProperties()
//                loadingList.clear()
//            },
//            modifier = Modifier.fillMaxSize()
//        ) {
//            LazyColumn(
//                state = rememberLazyListState(),
//                modifier = Modifier
//                    .fillMaxSize()
//                    .padding(top = 8.dp),
//            ) {
//                items(propertyList) { item ->
//                    PropertyListItem(property = item, isLoading)
//                }
//            }
//        }
        FloatingActionButton(
            onClick = {
                context.longToast("from FAB")
                viewModel.reverseList()
            },
            backgroundColor = Color.Green,
//            modifier = Modifier.constrainAs(fab) {
//                bottom.linkTo(parent.bottom, 8.dp)
//                end.linkTo(parent.end, 8.dp)
//            }
        ) {
            Icon(Icons.Filled.Add, "Add")
        }
    }
}