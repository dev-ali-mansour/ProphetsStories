package com.tibadev.alimansour.prophetstories.story.presentation.story_list.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.tibadev.alimansour.prophetstories.core.presentation.theme.barBackgroundColor
import com.tibadev.alimansour.prophetstories.core.presentation.theme.helveticaFamily
import com.tibadev.alimansour.prophetstories.core.presentation.theme.textTitleSize
import com.tibadev.alimansour.prophetstories.core.util.Strings


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun StoriesTopBar(
    title: String,
    onSettingsClicked: () -> Unit
) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = title,
                fontFamily = helveticaFamily(),
                fontSize = textTitleSize,
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = barBackgroundColor,
            navigationIconContentColor = Color.White,
            titleContentColor = Color.White,
            actionIconContentColor = Color.White
        ),
        actions = {
            IconButton(onClick = onSettingsClicked) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = Strings.nav_app_settings,
                )
            }
        })
}