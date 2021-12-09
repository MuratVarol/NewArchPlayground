package com.example.newarchplayground.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.example.newarchplayground.MainViewModel
import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.R
import com.example.newarchplayground.ui.theme.Shapes
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState


@OptIn(ExperimentalAnimationApi::class)
@Preview
@Composable
fun PropertyListUi(scaffoldState: ScaffoldState) {
    val viewModel = hiltViewModel<MainViewModel>()
    // collectAsState same as toObservable everytime data change; UI will be updated
    val propertyList by viewModel.propertyList.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    //remember: if we will update Composable UI value later: need to cascade it with "remember"
    // to notify UI after value changed
    val loadingList  = remember {
        mutableStateListOf<String>().apply {
            add("Loading")
            add("Loading")
            add("Loading")
            add("Loading")
            add("Loading")
            add("Loading")
            add("Loading")
            add("Loading")
            add("Loading")
        }
    }
    //reaching context in Composable
    val context = LocalContext.current
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        //creating reference for constraint layout
        val (loading) = createRefs()
        val (fab) = createRefs()

        //showing progressbar with visibility animation
        AnimatedVisibility(visible = false/**isLoading.value*/, modifier = Modifier
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
            items(loadingList){
                PropertyListItem(property = PropertyUiModel("","","",""), isLoading)
            }
        }

        //Accompanist has componnets such as SwipeRefresh or CardView
        // It is same as previous Anko of Kotlin but developed by Google
        val swipeToRefreshState = rememberSwipeRefreshState(isLoading)
        SwipeRefresh(
            state = swipeToRefreshState,
            onRefresh = {
                viewModel.getProperties()
                loadingList.clear()
            },
            modifier = Modifier.fillMaxSize()
        ) {
            LazyColumn(
                state = rememberLazyListState(),
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
            ) {
                items(propertyList) { item ->
                    PropertyListItem(property = item, isLoading)
                }
            }
        }
        FloatingActionButton(
            onClick = {
                toastTest("from FAB", context)
                viewModel.reverseList()
            },
            backgroundColor = Color.Green,
            modifier = Modifier.constrainAs(fab){
                bottom.linkTo(parent.bottom, 8.dp)
                end.linkTo(parent.end, 8.dp)
            }
        ) { Icon(Icons.Filled.Add,"Add")
        }
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PropertyListItem(
    property: PropertyUiModel?,
    isLoading: Boolean
) {
    val context = LocalContext.current
    Card(
        shape = Shapes.medium,
        elevation = 8.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .wrapContentHeight()
            .clickable(
                onClick = { toastTest(property?.name, context) }

            ),
        backgroundColor = Color.White
    ) {
        ConstraintLayout {
            val image = addImage(property?.imageUrl?: "", isLoading)
            val title = addTitle(image, property?.name?: "", isLoading)
            val author = addDescription(title, image, property?.description?: "", isLoading)
        }
    }
}

fun toastTest(text : String?, context: Context) {
    Toast.makeText(context, text.toString(), Toast.LENGTH_LONG).show()
}

@Composable
private fun ConstraintLayoutScope.addImage(url: String, isLoading: Boolean): ConstrainedLayoutReference {
    val image = createRef()
    val painter = rememberImagePainter(
        data = url,
        builder = {
            transformations(CircleCropTransformation())
        })
    Image(
        painter = painter,
        contentDescription = null,
        modifier = Modifier
            .size(120.dp)
            .constrainAs(image) {
                top.linkTo(parent.top, margin = 8.dp)
                start.linkTo(parent.start, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            }
            .placeholder(
                visible = isLoading,
                color = Color.Gray,
                shape = RoundedCornerShape(90.dp),
                highlight = PlaceholderHighlight.fade(
                    highlightColor = Color.White,
                )
            )
    )
    return image
}

@Composable
private fun ConstraintLayoutScope.addTitle(
    image: ConstrainedLayoutReference,
    titleText: String?,
    isLoading: Boolean,
): ConstrainedLayoutReference {
    val title = createRef()
    Text(
        text = titleText ?: stringResource(id = R.string.app_name),
        modifier = Modifier
            .constrainAs(title) {
                start.linkTo(image.end, 8.dp)
                top.linkTo(image.top, 8.dp)
                width = Dimension.fillToConstraints
            }
            .placeholder(
                visible = isLoading,
                color = Color.Gray,
                shape = RoundedCornerShape(0.dp),
                highlight = PlaceholderHighlight.fade(
                    highlightColor = Color.White,
                )
            ),
        maxLines = 3,
        style = TextStyle(fontWeight = FontWeight.SemiBold, fontSize = 18.sp)
    )
    return title
}

@Composable
private fun ConstraintLayoutScope.addDescription(
    title: ConstrainedLayoutReference,
    image: ConstrainedLayoutReference,
    descriptionText: String?,
    isLoading: Boolean
): ConstrainedLayoutReference {
    val description = createRef()
    Text(
        text = descriptionText ?: stringResource(id = R.string.app_name),
        modifier = Modifier
            .constrainAs(description) {
                start.linkTo(image.end, 8.dp)
                top.linkTo(title.bottom, 4.dp)
                end.linkTo(parent.end, 8.dp)
                width = Dimension.fillToConstraints
            }
            .placeholder(
                visible = isLoading,
                color = Color.Gray,
                shape = RoundedCornerShape(0.dp),
                highlight = PlaceholderHighlight.fade(
                    highlightColor = Color.White,
                )
            ),
        style = TextStyle(fontSize = 16.sp, color = Color.Gray),
        fontStyle = FontStyle.Italic
    )
    return description
}
