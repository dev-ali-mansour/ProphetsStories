package com.tibadev.alimansour.prophetstories.story.presentation.settings

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import com.tibadev.alimansour.prophetstories.app.Route
import com.tibadev.alimansour.prophetstories.core.presentation.theme.barBackgroundColor
import com.tibadev.alimansour.prophetstories.core.presentation.theme.helveticaFamily
import com.tibadev.alimansour.prophetstories.core.presentation.util.Constants
import com.tibadev.alimansour.prophetstories.core.util.Strings
import com.tibadev.alimansour.prophetstories.core.util.openUrl
import com.tibadev.alimansour.prophetstories.core.util.shareApp
import com.tibadev.alimansour.prophetstories.core.util.rateApp
import com.tibadev.alimansour.prophetstories.core.util.sendEmail
import com.tibadev.alimansour.prophetstories.core.util.showOurApps
import com.tibadev.alimansour.prophetstories.story.domain.model.SettingsItem
import org.koin.compose.viewmodel.koinViewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel = koinViewModel(),
    onBackClicked: () -> Unit,
    navigateTo: (Route) -> Unit,
) {
    val settings by viewModel.settingsFlow.collectAsState()

    LaunchedEffect(true) {
        viewModel.getSettingsItems()
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = Strings.nav_app_settings,
                        fontFamily = helveticaFamily(),
                    )
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = barBackgroundColor,
                    navigationIconContentColor = Color.White,
                    titleContentColor = Color.White,
                    actionIconContentColor = Color.White
                ),
                navigationIcon = {
                    IconButton(onClick = {
                        onBackClicked()
                    }) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = null,
                        )
                    }
                }
            )
        },
        content = { paddingValues ->
            Box(modifier = Modifier.padding(paddingValues)) {
                SettingsContent(settings = settings) { settingsItem ->
                    handleSettingsItemClick(
                        settingsItem = settingsItem,
                        navigateTo = { route ->
                            navigateTo(route)
                        },
                    )
                }
            }
        }
    )
}


private fun handleSettingsItemClick(
    settingsItem: SettingsItem,
    navigateTo: (Route) -> Unit,
) {
    when (settingsItem.id) {

        Constants.SHARE_CODE -> shareApp(
            text = Strings.share_app,
            url = Strings.website_url
        )
        Constants.RATE_CODE -> rateApp(
            marketUrl = "market://details?id=com.tibadev.alimansour.prophetstories",
            fallbackUrl = "https://play.google.com/store/apps/details?id=com.tibadev.alimansour.prophetstories"
        )
        Constants.CONTACT_CODE -> sendEmail(
            email = "dev.ali.mansour@gmail.com",
            subject = "تواصل معنا",
            body = null
        )
        Constants.ERROR_REPORTING_CODE -> sendEmail(
            email = "dev.ali.mansour@gmail.com",
            subject = "الإبلاغ عن خطأ",
            body = null
        )
        Constants.APPS_CODE -> showOurApps(
            marketUrl = "market://dev?id=8260943179739148532",
            fallbackUrl = "https://play.google.com/store/apps/dev?id=8260943179739148532"
        )
        Constants.POLICY_CODE ->
            openUrl(Strings.privacy_policy_url)

        Constants.ABOUT_CODE -> navigateTo(Route.About)
    }
}