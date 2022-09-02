package com.example.montypays.settings.presentation.share.bottomsheets

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.montypays.R

@Composable
fun SuccessfullyDeletedSheet() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(250.dp)
    ) {
        val constraint = ConstraintSet {
            val image = createRefFor(id = "image")
            val textTop = createRefFor(id = "textTop")
            val textBottom = createRefFor(id = "textBottom")
            constrain(image) {
                top.linkTo(parent.top, margin = 25.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(textTop){
                top.linkTo(image.bottom, margin = 20.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(textBottom){
                top.linkTo(textTop.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
        }
        ConstraintLayout(constraint, modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = R.drawable.deleted_successfully),
                contentDescription = "Success",
                modifier = Modifier
                    .layoutId("image")
            )
            Text(
                text = "Share Message",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.layoutId("textTop")
            )
            Text(
                text = "Deleted Successfully",
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.layoutId("textBottom")
            )
        }
    }
}