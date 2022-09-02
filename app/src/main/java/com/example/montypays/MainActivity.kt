package com.example.montypays

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.montypays.dialogs.InternetConnectionDialog
import com.simplemobiletools.commons.extensions.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var connectivityObserver: ConnectivityObserver

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        connectivityObserver = NetworkConnectivityObserver(this)
        connectivityObserver.observe().onEach {
            when(it){
                ConnectivityObserver.Status.Lost -> InternetConnectionDialog(this,this)
                ConnectivityObserver.Status.Unavailable -> InternetConnectionDialog(this,this)
                ConnectivityObserver.Status.Available -> toast("Status is $it")
                ConnectivityObserver.Status.Losing -> toast("Your $it internet connection")
            }
        }.launchIn(lifecycleScope)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        installSplashScreen().apply {
            setKeepOnScreenCondition {
                viewModel.isLoading.value

            }
        }
        setContentView(R.layout.activity_main)

        this.window.setSoftInputMode(SOFT_INPUT_ADJUST_PAN)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            this.window.decorView.systemUiVisibility =
                View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
        this.window.statusBarColor = Color.TRANSPARENT
    }
}
