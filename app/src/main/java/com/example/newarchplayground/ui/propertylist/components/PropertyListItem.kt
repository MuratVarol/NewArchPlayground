package com.example.newarchplayground.ui.propertylist.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.newarchplayground.PropertyUiModel
import com.example.newarchplayground.ui.theme.Shapes
import com.example.newarchplayground.util.extensions.longToast

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
                onClick = { context.longToast(property?.name) }
            ),
        backgroundColor = Color.White
    ) {
//        ConstraintLayout {
//            val image = addImage(property?.imageUrl ?: "", isLoading)
//            val title = addTitle(image, property?.name ?: "", isLoading)
//            val author = addDescription(title, image, property?.description ?: "", isLoading)
//        }
    }
}