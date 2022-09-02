package com.example.montypays.settings.presentation.floatsheet

import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import com.example.montypays.R
import com.example.montypays.settings.presentation.share.CustomOutlinedButton
import com.example.montypays.settings.presentation.share.ShareSettingsEvent
import com.example.montypays.settings.presentation.share.ShareSettingsViewModel
import com.example.montypays.utils.UiEvent
import kotlinx.coroutines.CoroutineScope

@ExperimentalMaterialApi
@Composable
fun BottomSheetContent(
    viewModel: ShareSettingsViewModel,
    sheetState: BottomSheetState,
    scope: CoroutineScope
) {
    val context = LocalContext.current
    val scaffoldState = rememberScaffoldState()
    LaunchedEffect(key1 = true){
        viewModel.uiEvent.collect { event ->
            when(event){
                is UiEvent.ShowSnackBar -> {
                    scaffoldState.snackbarHostState.showSnackbar(
                        message = event.message,
                        actionLabel = event.action
                    )
                    Toast.makeText(context, "test", Toast.LENGTH_SHORT).show()
                }
                else -> Toast.makeText(context, "test", Toast.LENGTH_SHORT).show()
            }
        }
    }
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(640.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        val constraint = ConstraintSet {
            val titleBold = createRefFor(id = "titleBold")
            val titleLight = createRefFor(id = "titleLight")
            val titleForSelect = createRefFor(id = "titleForSelect")
            val editTextSelect = createRefFor(id = "editTextSelect")
            val shareContent = createRefFor(id = "shareContent")
            val buttonSave = createRefFor(id = "buttonSave")
            val shareContentBox = createRefFor(id = "shareContentBox")
            constrain(titleBold) {
                top.linkTo(parent.top, margin = 25.dp)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(titleLight) {
                top.linkTo(titleBold.bottom)
                start.linkTo(parent.start)
                end.linkTo(parent.end)
            }
            constrain(titleForSelect) {
                start.linkTo(parent.start, margin = 35.dp)
                top.linkTo(titleLight.bottom, margin = 50.dp)
            }
            constrain(editTextSelect) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(titleForSelect.bottom, margin = 10.dp)
            }
            constrain(shareContent) {
                start.linkTo(parent.start, margin = 35.dp)
                top.linkTo(editTextSelect.bottom, margin = 35.dp)
            }
            constrain(buttonSave) {
                bottom.linkTo(parent.bottom, margin = 30.dp)
                start.linkTo(parent.start, margin = 65.dp)
                end.linkTo(parent.end, margin = 65.dp)
            }
            constrain(shareContentBox) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(shareContent.bottom)
                bottom.linkTo(buttonSave.top)
            }
        }
        ConstraintLayout(constraint, modifier = Modifier.fillMaxSize()) {
            Text(
                text = "New Share Message",
                modifier = Modifier.layoutId("titleBold"),
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Add your payment share content",
                modifier = Modifier.layoutId("titleLight"),
                fontSize = 15.sp,
                fontWeight = FontWeight.Light,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = "Merchant Name",
                modifier = Modifier.layoutId("titleForSelect"),
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis
            )

            OutlinedTextField(
                value = viewModel.merchantName, onValueChange = { newText ->
                    viewModel.onEvent(ShareSettingsEvent.OnMerchantNameChange(newText))
                }, modifier = Modifier
                    .layoutId("editTextSelect")
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp),
                trailingIcon = {
                    IconButton(onClick = {

                    }) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_baseline_keyboard_arrow_down_24),
                            contentDescription = "arrowDown"
                        )
                    }
                }, shape = CircleShape
            )
            Text(
                text = "Share Content",
                modifier = Modifier.layoutId("shareContent"),
                fontSize = 16.sp,
                overflow = TextOverflow.Ellipsis
            )

            OutlinedTextField(
                value = viewModel.shareContent, onValueChange = { newText ->
                    viewModel.onEvent(ShareSettingsEvent.OnShareContentChange(newText))
                }, modifier = Modifier
                    .layoutId("shareContentBox")
                    .fillMaxWidth()
                    .padding(start = 30.dp, end = 30.dp)
                    .height(250.dp),
                shape = CircleShape.copy(
                    topStart = CornerSize(15.dp),
                    topEnd = CornerSize(15.dp),
                    bottomEnd = CornerSize(15.dp),
                    bottomStart = CornerSize(15.dp)
                )
            )
            CustomOutlinedButton(
                contentText = "Save",
                layoutId = "buttonSave",
                backGroundColor = "#1fa9e5",
                contentColor = Color.White,
                borderColor = Color(android.graphics.Color.parseColor("#1fa9e5")),
                onClick = {
                  viewModel.onEvent(ShareSettingsEvent.OnSaveClick(sheetState),scope)
                }
            )
        }
    }
}