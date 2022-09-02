package com.example.montypays.authentication.presentation.reset

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.example.montypays.R

@ExperimentalMaterialApi
class ResetPasswordScreen : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
            setContent {
                setBackgroundResource(R.drawable.loading)
                FullScreen()
            }
        }
    }
}

@ExperimentalMaterialApi
@Composable
fun FullScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.height(241.dp))
        Image(
            painter = painterResource(id = R.drawable.my_project_1),
            contentDescription = "resetImage",
            modifier = Modifier
                .fillMaxWidth(),
            contentScale = ContentScale.FillWidth
        )
        Spacer(modifier = Modifier.height(50.dp))
        CustomText(
            text = "An email is being sent to your inbox."
        )
        CustomText(
            text = "Please reset your password"
        )
        CustomText(
            text = "and try again."
        )
        Spacer(modifier = Modifier.height(130.dp))
        OutlinedButton(onClick = {

        }, shape = CircleShape,
        modifier = Modifier
            .width(150.dp)
            .height(55.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = Color.White,
                backgroundColor = Color(android.graphics.Color.parseColor("#1fa9e5"))
            ),
            border = BorderStroke(1.dp,Color(android.graphics.Color.parseColor("#1fa9e5")))
        ) {
            Text(text = "Ok", fontSize = 18.sp)
        }
    }
}

@Composable
fun CustomText(
    text: String,
) {
    Text(
        text = text,
        fontSize = 18.sp,
        color = Color.White
    )
}