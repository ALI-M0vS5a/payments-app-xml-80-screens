package com.example.montypays.settings.presentation.share


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.NavController
import androidx.navigation.findNavController
import com.example.montypays.R
import com.example.montypays.settings.presentation.floatsheet.BottomSheetContent
import com.example.montypays.settings.presentation.share.bottomsheets.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope


@AndroidEntryPoint
@ExperimentalMaterialApi
class ShareSettingsFragment : Fragment() {
    private val viewModel: ShareSettingsViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                ShareSettingsScreen(viewModel)
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun ComposeView.ShareSettingsScreen(
    viewModel: ShareSettingsViewModel
) {
    val editSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val editScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = editSheetState)
    val deleteSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val deleteScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = deleteSheetState)
    val successDeleteSheetState =
        rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val successScaffoldState =
        rememberBottomSheetScaffoldState(bottomSheetState = successDeleteSheetState)
    val sheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val scaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = sheetState)
    val moreSheetState = rememberBottomSheetState(initialValue = BottomSheetValue.Collapsed)
    val moreScaffoldState = rememberBottomSheetScaffoldState(bottomSheetState = moreSheetState)
    val scope = rememberCoroutineScope()

    BottomSheetScaffold(
        scaffoldState = scaffoldState,
        sheetContent = {
            BottomSheetContent(
                viewModel = viewModel,
                sheetState = sheetState,
                scope = scope,
            )
        }, sheetBackgroundColor = Color.White,
        sheetPeekHeight = 0.dp,
        sheetShape = CircleShape.copy(
            topStart = CornerSize(50.dp),
            topEnd = CornerSize(50.dp),
            bottomStart = CornerSize(0.dp),
            bottomEnd = CornerSize(0.dp)
        ),
        sheetElevation = 10.dp
    ) {
        BottomSheetScaffold(
            sheetContent = {
                MoreBottomSheetContent(
                    moreSheetState = moreSheetState,
                    deleteSheetState = deleteSheetState,
                    scope = scope,
                    editSheetState = editSheetState,
                    viewModel = viewModel
                )
            },
            sheetPeekHeight = 0.dp,
            scaffoldState = moreScaffoldState,
            sheetShape = CircleShape.copy(
                topStart = CornerSize(30.dp),
                topEnd = CornerSize(30.dp),
                bottomStart = CornerSize(0.dp),
                bottomEnd = CornerSize(0.dp)
            ),
        ) {

            BottomSheetScaffold(
                sheetContent = {
                    DeleteSheetContent(
                        viewModel,
                        scope,
                        deleteSheetState,
                        successDeleteSheetState
                    )
                },
                scaffoldState = deleteScaffoldState,
                sheetPeekHeight = 0.dp,
                sheetShape = CircleShape.copy(
                    topStart = CornerSize(50.dp),
                    topEnd = CornerSize(50.dp),
                    bottomStart = CornerSize(0.dp),
                    bottomEnd = CornerSize(0.dp)
                ),

                ) {
                BottomSheetScaffold(
                    sheetContent = {
                        BottomSheetContentEdit(
                            viewModel = viewModel,
                            sheetState = editSheetState,
                            scope = scope
                        )
                    }, sheetPeekHeight = 0.dp,
                    sheetShape = CircleShape.copy(
                        topStart = CornerSize(50.dp),
                        topEnd = CornerSize(50.dp),
                        bottomStart = CornerSize(0.dp),
                        bottomEnd = CornerSize(0.dp)
                    ), scaffoldState = editScaffoldState
                ) {
                    BottomSheetScaffold(
                        sheetContent = {
                            SuccessfullyDeletedSheet()
                        },
                        sheetPeekHeight = 0.dp,
                        sheetShape = CircleShape.copy(
                            topStart = CornerSize(50.dp),
                            topEnd = CornerSize(50.dp),
                            bottomStart = CornerSize(0.dp),
                            bottomEnd = CornerSize(0.dp)
                        ),
                        scaffoldState = successScaffoldState
                    ) {
                        Scaffold(
                            scope = scope,
                            sheetState = sheetState,
                            moreSheetState = moreSheetState,
                            viewModel = viewModel
                        )
                    }
                }
            }
        }
    }
}


@ExperimentalMaterialApi
@Composable
fun ComposeView.Scaffold(
    scope: CoroutineScope,
    sheetState: BottomSheetState,
    moreSheetState: BottomSheetState,
    viewModel: ShareSettingsViewModel

) {
    Column(modifier = Modifier.fillMaxSize()) {
        Spacer(modifier = Modifier.height(54.dp))
        ListScreen(
            scope = scope,
            sheetState = sheetState,
            moreSheetState = moreSheetState,
            viewModel = viewModel
        )
    }
}

@Composable
fun TopBar(
    name: String,
    modifier: Modifier = Modifier,
    navController: NavController
) {
    val constraint = ConstraintSet {
        val arrowBack = createRefFor(id = "arrowBack")
        val title = createRefFor(id = "title")

        constrain(arrowBack) {
            start.linkTo(parent.start, margin = 15.dp)
            top.linkTo(parent.top)
        }
        constrain(title) {
            top.linkTo(parent.top)
            start.linkTo(arrowBack.end)
            end.linkTo(parent.end, margin = 30.dp)
        }
    }
    ConstraintLayout(constraint, modifier.fillMaxWidth()) {
        Image(
            painter = painterResource(id = R.drawable.ic_baseline_arrow_back_ios_new_24),
            contentDescription = "arrowBack",
            modifier = modifier
                .size(24.dp)
                .clickable {
                    navController.navigateUp()
                }
                .layoutId("arrowBack")
        )
        Text(
            text = name,
            overflow = TextOverflow.Ellipsis,
            fontWeight = FontWeight.Bold,
            fontSize = 18.sp,
            modifier = modifier
                .layoutId("title")
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun ComposeView.ListScreen(
    scope: CoroutineScope,
    sheetState: BottomSheetState,
    moreSheetState: BottomSheetState,
    viewModel: ShareSettingsViewModel
) {
    Surface(
        color = MaterialTheme.colors.background,
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column {
            Spacer(modifier = Modifier.height(15.dp))
            TopBar(
                name = "Share Settings",
                navController = findNavController()
            )

            val hint = "Search.."

            var isHintDisplayed by remember {
                mutableStateOf(hint != "")
            }


            Box(
                modifier = Modifier
                    .padding(16.dp)
            ) {
                BasicTextField(
                    value = viewModel.textSearch, onValueChange = {
                        viewModel.onEvent(ShareSettingsEvent.OnSearchChanged(it))
                        isHintDisplayed = it.isEmpty()

                    },
                    maxLines = 1,
                    singleLine = true,
                    textStyle = TextStyle(color = Color.Black),
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(5.dp, CircleShape)
                        .background(Color.White, CircleShape)
                        .padding(horizontal = 20.dp, vertical = 12.dp)
                )
                if (isHintDisplayed) {
                    Text(
                        text = hint,
                        color = Color.LightGray,
                        modifier = Modifier.padding(
                            horizontal = 20.dp,
                            vertical = 12.dp
                        )
                    )
                }
            }
            val cachedList =
                viewModel.searchDatabase("%${viewModel.textSearch}%").observeAsState(listOf()).value
            LazyColumn {
                items(
                    items = cachedList
                ) { share ->
                    ShareCompose(
                        shareSettings = share,
                        onClick = {
                            viewModel.onEvent(
                                ShareSettingsEvent.OnMoreClicked(
                                    moreSheetState, share
                                ), scope
                            )
                        }
                    )
                }
            }
        }
        ShareButton(
            scope = scope,
            sheetState = sheetState,
            viewModel = viewModel
        )
    }
}

@ExperimentalMaterialApi
@Composable
fun ShareButton(
    scope: CoroutineScope,
    sheetState: BottomSheetState,
    viewModel: ShareSettingsViewModel
) {
    val constraint = ConstraintSet {
        val shareButton = createRefFor(id = "shareButton")
        constrain(shareButton) {
            bottom.linkTo(parent.bottom, margin = 46.dp)
            end.linkTo(parent.end, margin = 36.dp)
        }
    }
    ConstraintLayout(constraint, modifier = Modifier.fillMaxWidth()) {
        FloatingActionButton(
            onClick = {
               viewModel.onEvent(ShareSettingsEvent.OnAddShareClicked(sheetState),scope)
            },
            modifier = Modifier
                .size(65.dp)
                .layoutId("shareButton"),
            shape = CircleShape,
            contentColor = Color.White,
            backgroundColor = Color(android.graphics.Color.parseColor("#1fa9e5"))
        ) {
            Icon(
                painter = painterResource(id = com.simplemobiletools.commons.R.drawable.ic_plus_vector),
                contentDescription = "addShare"
            )
        }
    }

}







