package com.example.montypays.settings.presentation.share

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.OutlinedButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@ExperimentalMaterialApi
@Composable
fun CustomOutlinedButton(
    contentText: String,
    layoutId: String,
    backGroundColor: String,
    contentColor: Color,
    borderColor: Color,
    onClick: () -> Unit = {}
) {
    OutlinedButton(
        onClick = {
            onClick.invoke()
        }, shape = CircleShape,
        modifier = Modifier
            .layoutId(layoutId)
            .fillMaxWidth()
            .height(70.dp)
            .padding(start = 30.dp, end = 30.dp),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = contentColor,
            backgroundColor = Color(color = android.graphics.Color.parseColor(backGroundColor))
        ),
        border = BorderStroke(1.dp, borderColor)
    ) {
        Text(
            text = contentText,
            fontSize = 18.sp
        )
    }
}