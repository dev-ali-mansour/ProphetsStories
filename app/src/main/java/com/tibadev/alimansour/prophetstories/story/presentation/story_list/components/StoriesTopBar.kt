package com.tibadev.alimansour.prophetstories.story.presentation.story_list.components

import android.content.res.Configuration.UI_MODE_NIGHT_YES
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.tibadev.alimansour.prophetstories.R
import com.tibadev.alimansour.prophetstories.core.presentation.theme.barBackgroundColor
import com.tibadev.alimansour.prophetstories.core.presentation.theme.helveticaFamily


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
                fontFamily = helveticaFamily,
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
                    contentDescription = null,
                )
            }
        })
}

@Preview
@Preview(uiMode = UI_MODE_NIGHT_YES)
@Composable
fun StoriesTopBarPreview() {
    StoriesTopBar(
        title = stringResource(id = R.string.app_name),
        onSettingsClicked = {}
    )
}