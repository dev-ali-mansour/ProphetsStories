package com.tibadev.alimansour.prophetstories

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.tibadev.alimansour.prophetstories.app.App
import com.tibadev.alimansour.prophetstories.core.presentation.theme.ProphetStoriesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()

        setContent {
            ProphetStoriesTheme {
                App()
            }
        }
    }
}