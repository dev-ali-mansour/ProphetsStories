package com.tibadev.alimansour.prophetstories

import androidx.compose.ui.window.ComposeUIViewController
import com.tibadev.alimansour.prophetstories.app.App
import com.tibadev.alimansour.prophetstories.core.presentation.theme.ProphetStoriesTheme
import platform.UIKit.UIViewController

fun MainViewController(): UIViewController = ComposeUIViewController {
    ProphetStoriesTheme {
        App()
    }
}

