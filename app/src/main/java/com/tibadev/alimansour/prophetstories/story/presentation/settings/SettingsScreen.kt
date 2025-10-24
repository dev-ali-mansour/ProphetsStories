package com.tibadev.amazingsms.ui.screen.settings

import android.content.Context
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import com.tibadev.alimansour.prophetstories.R
import com.tibadev.alimansour.prophetstories.app.Route
import com.tibadev.alimansour.prophetstories.core.presentation.theme.barBackgroundColor
import com.tibadev.alimansour.prophetstories.core.presentation.theme.helveticaFamily
import com.tibadev.alimansour.prophetstories.core.presentation.util.Constants
import com.tibadev.alimansour.prophetstories.core.presentation.util.appRate
import com.tibadev.alimansour.prophetstories.core.presentation.util.appShare
import com.tibadev.alimansour.prophetstories.core.presentation.util.contactUs
import com.tibadev.alimansour.prophetstories.core.presentation.util.openLink
import com.tibadev.alimansour.prophetstories.core.presentation.util.reportError
import com.tibadev.alimansour.prophetstories.core.presentation.util.showOurApps
import com.tibadev.alimansour.prophetstories.story.domain.model.SettingsItem
import com.tibadev.alimansour.prophetstories.story.presentation.settings.SettingsContent
import com.tibadev.alimansour.prophetstories.story.presentation.settings.SettingsViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(
    viewModel: SettingsViewModel,
    onBackClicked: () -> Unit,
    navigateTo: (Route) -> Unit,
) {
    val settings by viewModel.settingsFlow.collectAsState()
    val context = LocalContext.current

    SideEffect {
        viewModel.getSettingsItems(context)
    }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = stringResource(id = R.string.nav_app_settings),
                        fontFamily = helveticaFamily,
                    )
                },
                colors =
                    TopAppBarDefaults.topAppBarColors(
                        containerColor = barBackgroundColor,
                        navigationIconContentColor = Color.White,
                        titleContentColor = Color.White,
                        actionIconContentColor = Color.White,
                    ),
                navigationIcon = {
                    IconButton(onClick = {
                        onBackClicked()
                    }) {
                        Icon(
                            painter = painterResource(R.drawable.core_ui_ic_arrow_back),
                            contentDescription = null,
                        )
                    }
                },
            )
        },
        content = {
            Box(modifier = Modifier.padding(it)) {
                SettingsContent(settings = settings) { settingsItem ->
                    onItemClicked(
                        settingsItem = settingsItem,
                        context = context,
                        navigateTo = { route ->
                            navigateTo(route)
                        },
                    )
                }
            }
        },
    )
}

private fun onItemClicked(
    settingsItem: SettingsItem,
    context: Context,
    navigateTo: (Route) -> Unit,
) {
    when (settingsItem.id) {
        Constants.SHARE_CODE -> context.appShare()
        Constants.RATE_CODE -> context.appRate()
        Constants.CONTACT_CODE -> context.contactUs()
        Constants.ERROR_REPORTING_CODE -> context.reportError()
        Constants.APPS_CODE -> context.showOurApps()
        Constants.POLICY_CODE ->
            context.openLink(context.getString(R.string.privacy_policy_url))

        Constants.ABOUT_CODE -> navigateTo(Route.About)
    }
}
