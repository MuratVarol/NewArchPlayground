package com.example.newarchplayground.ui.propertylist.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayoutScope
import coil.compose.rememberImagePainter
import coil.transform.CircleCropTransformation
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder


@Composable
private fun ConstraintLayoutScope.addImage(
    url: String,
    isLoading: Boolean
): ConstrainedLayoutReference {
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

