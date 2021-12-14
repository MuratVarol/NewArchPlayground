package com.example.newarchplayground.ui.propertylist.components

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstrainedLayoutReference
import androidx.constraintlayout.compose.ConstraintLayoutScope
import androidx.constraintlayout.compose.Dimension
import com.example.newarchplayground.R
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.fade
import com.google.accompanist.placeholder.placeholder

@Composable
fun ConstraintLayoutScope.addTitle(
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
