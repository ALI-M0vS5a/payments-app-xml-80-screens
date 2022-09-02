package com.example.montypays.settings.presentation.share.bottomsheets

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomSheetState
import androidx.compose.material.ExperimentalMaterialApi
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
import com.example.montypays.settings.presentation.share.ShareSettingsEvent
import com.example.montypays.settings.presentation.share.ShareSettingsViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@ExperimentalMaterialApi
@Composable
fun MoreBottomSheetContent(
    moreSheetState: BottomSheetState,
    deleteSheetState: BottomSheetState,
    scope: CoroutineScope,
    editSheetState: BottomSheetState,
    viewModel: ShareSettingsViewModel

) {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(220.dp)
    ) {
        val constraint = ConstraintSet {
            val title = createRefFor(id = "title")
            val close = createRefFor(id = "close")
            val editImage = createRefFor(id = "editImage")
            val editText = createRefFor(id = "editText")
            val deleteImage = createRefFor(id = "deleteImage")
            val deleteText = createRefFor(id = "deleteText")

            constrain(title) {
                start.linkTo(parent.start)
                top.linkTo(parent.top)
            }
            constrain(close) {
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            }
            constrain(editImage) {
                start.linkTo(parent.start)
                top.linkTo(title.bottom, margin = 50.dp)
            }
            constrain(editText) {
                start.linkTo(editImage.end, margin = 10.dp)
                top.linkTo(title.bottom, margin = 50.dp)
            }
            constrain(deleteImage) {
                start.linkTo(parent.start)
                top.linkTo(editImage.bottom, margin = 20.dp)
            }
            constrain(deleteText) {
                start.linkTo(deleteImage.end, margin = 10.dp)
                top.linkTo(editImage.bottom, margin = 20.dp)
            }

        }
        ConstraintLayout(
            constraint, modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = "Khoury Home",
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .layoutId("title")
            )
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_close_24),
                contentDescription = "closeMore",
                modifier = Modifier
                    .layoutId("close")
                    .clickable {
                        scope.launch {
                            moreSheetState.collapse()
                        }
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_edit_24),
                contentDescription = "editImage",
                modifier = Modifier
                    .layoutId("editImage")
                    .clickable {
                        viewModel.onEvent(ShareSettingsEvent.OnEditClicked(
                            moreSheetState,editSheetState),scope)
                    }
            )
            Text(
                text = "Edit",
                fontSize = 16.sp,
                modifier = Modifier
                    .layoutId("editText")
                    .clickable {
                        viewModel.onEvent(ShareSettingsEvent.OnEditClicked(
                            moreSheetState,editSheetState),scope)
                    }
            )
            Image(
                painter = painterResource(id = R.drawable.ic_baseline_delete_24),
                contentDescription = "deleteImage",
                modifier = Modifier
                    .layoutId("deleteImage")
                    .clickable {
                        viewModel.onEvent(ShareSettingsEvent.OnDeleteClicked(
                            deleteSheetState,moreSheetState
                        ), scope)
                    }
            )
            Text(
                text = "Delete",
                fontSize = 16.sp,
                modifier = Modifier
                    .layoutId("deleteText")
                    .clickable {
                        viewModel.onEvent(ShareSettingsEvent.OnDeleteClicked(
                            deleteSheetState,moreSheetState
                        ), scope)
                    }
            )

        }
    }
}