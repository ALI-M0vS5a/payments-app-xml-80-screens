package com.example.montypays.settings.presentation.share.bottomsheets


import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.montypays.R
import com.example.montypays.settings.presentation.share.ShareSettingsEvent
import com.example.montypays.settings.presentation.share.CustomOutlinedButton
import com.example.montypays.settings.presentation.share.ShareSettingsViewModel
import kotlinx.coroutines.CoroutineScope

@ExperimentalMaterialApi
@Composable
 fun DeleteSheetContent(
    viewModel: ShareSettingsViewModel,
    scope: CoroutineScope,
    deleteSheetState: BottomSheetState,
    successDeleteSheetState: BottomSheetState
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(600.dp)
    ) {
        val constraint = ConstraintSet {
            val image = createRefFor(id = "image")
            val title = createRefFor(id = "title")
            val titleLight = createRefFor(id = "titleLight")
            val titleLight2 = createRefFor(id = "titleLight2")
            val delete = createRefFor(id = "delete")
            val cancel = createRefFor(id = "cancel")

            constrain(image) {
                top.linkTo(parent.top, margin = 100.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(title) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(image.bottom, margin = 30.dp)
            }
            constrain(titleLight) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(title.bottom, margin = 10.dp)
            }
            constrain(titleLight2) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(titleLight.bottom)
            }
            constrain(delete) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(titleLight2.bottom, margin = 40.dp)
            }
            constrain(cancel) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(delete.bottom, margin = 20.dp)
            }

        }
        ConstraintLayout(constraint, modifier = Modifier.fillMaxWidth()) {
            Text(
                text = "Delete Share Message",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.layoutId("title")
            )
            Text(
                text = "Are you sure you want to delete",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.layoutId("titleLight")
            )
            Text(
                text = "this share content?",
                fontSize = 16.sp,
                fontWeight = FontWeight.Light,
                modifier = Modifier.layoutId("titleLight2")
            )
            CustomOutlinedButton(
                contentText = "Delete",
                layoutId = "delete",
                backGroundColor = "#1fa9e5",
                contentColor = Color.White,
                borderColor = Color(android.graphics.Color.parseColor("#1fa9e5")),
                onClick = {
                    viewModel.onEvent(ShareSettingsEvent.OnSureDeleteClicked(
                        deleteSheetState,
                        successDeleteSheetState,
                        viewModel.arrayList[0]),scope)
                }
            )
            CustomOutlinedButton(
                contentText = "Cancel",
                layoutId = "cancel",
                backGroundColor = "#ffffff",
                contentColor = Color(android.graphics.Color.parseColor("#1fa9e5")),
                borderColor = Color(android.graphics.Color.parseColor("#1fa9e5")),
                onClick = {
                    viewModel.onEvent(ShareSettingsEvent.OnCancelClicked(deleteSheetState),scope)
                }
            )
            Image(
                painter = painterResource(id = R.drawable.delete_image),
                contentDescription = "deleteImage",
                contentScale = ContentScale.Crop,
                modifier = Modifier.layoutId("image")
            )
        }
    }
}