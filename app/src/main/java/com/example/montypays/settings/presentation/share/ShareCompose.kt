package com.example.montypays.settings.presentation.share

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.montypays.R
import com.example.montypays.settings.data.ShareSettings


@ExperimentalMaterialApi
@Composable
fun ShareCompose(
    shareSettings: ShareSettings,
    onClick: (share_id: Int) -> Unit = {}
) {
    val constraint = ConstraintSet {
        val title = createRefFor(id = "title")
        val threeDots = createRefFor(id = "threeDots")
        val content = createRefFor(id = "content")

        constrain(title) {
            start.linkTo(parent.start)
        }
        constrain(threeDots) {
            end.linkTo(parent.end)
        }
        constrain(content) {
            start.linkTo(parent.start)
            top.linkTo(title.bottom)
            end.linkTo(parent.end)
        }
    }
    ConstraintLayout(
        constraint,
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
            .height(200.dp)
            .background(
                color = Color(android.graphics.Color.parseColor("#C2E1EF")),
                shape = CircleShape.copy(
                    topStart = CornerSize(15.dp),
                    topEnd = CornerSize(15.dp),
                    bottomStart = CornerSize(15.dp),
                    bottomEnd = CornerSize(15.dp),
                )
            )
    ) {
        Text(
            text = shareSettings.merchantName,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier
                .layoutId("title")
                .padding(10.dp)
        )
        Image(
            painter = painterResource(
                id = R.drawable.ic_baseline_more_horiz_24
            ),
            contentDescription = "more",
            modifier = Modifier
                .layoutId("threeDots")
                .padding(10.dp)
                .clickable {
                    onClick.invoke(shareSettings.id!!)
                }
        )
        Text(
            text = shareSettings.shareContent,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(140.dp)
                .layoutId("content")
        )
    }
}
