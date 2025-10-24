package com.tibadev.alimansour.prophetstories.story.presentation.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.tibadev.alimansour.prophetstories.R
import com.tibadev.alimansour.prophetstories.core.presentation.theme.L_PADDING
import com.tibadev.alimansour.prophetstories.core.presentation.theme.S_PADDING
import com.tibadev.alimansour.prophetstories.core.presentation.theme.XS_PADDING
import com.tibadev.alimansour.prophetstories.core.presentation.theme.backgroundColor
import com.tibadev.alimansour.prophetstories.core.presentation.theme.contentColor
import com.tibadev.alimansour.prophetstories.core.presentation.theme.helveticaFamily
import com.tibadev.alimansour.prophetstories.core.presentation.util.Constants
import com.tibadev.alimansour.prophetstories.story.domain.model.SettingsItem


@Composable
fun SettingsContent(
    settings: List<SettingsItem>,
    onItemClicked: (SettingsItem) -> Unit
) {
    Column {
        LazyColumn(
            contentPadding = PaddingValues(all = S_PADDING),
            verticalArrangement = Arrangement.spacedBy(S_PADDING)
        ) {
            items(settings) { settingsItem ->
                SettingItemView(
                    settingsItem = settingsItem,
                    onItemClicked = onItemClicked
                )
            }
        }
    }
}

@Composable
fun SettingItemView(
    settingsItem: SettingsItem,
    onItemClicked: (SettingsItem) -> Unit
) {
    Box(
        modifier = Modifier.clickable { onItemClicked(settingsItem) },
        contentAlignment = Alignment.CenterStart
    ) {
        Surface(
            modifier = Modifier
                .height(75.dp)
                .fillMaxWidth(),
            color = backgroundColor.copy(0.6f),
            shape = RoundedCornerShape(
                topStart = L_PADDING,
                topEnd = L_PADDING,
                bottomStart = L_PADDING,
                bottomEnd = L_PADDING
            )
        ) {
            Row(
                modifier = Modifier
                    .padding(all = XS_PADDING)
            ) {
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Icon(
                        modifier = Modifier.size(45.dp),
                        painter = painterResource(id = R.drawable.feature_settings_ic_arrow),
                        contentDescription = null,
                        tint = contentColor
                    )
                }
                Column(
                    modifier = Modifier.fillMaxHeight(),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = settingsItem.title,
                        color = contentColor,
                        fontSize = MaterialTheme.typography.titleLarge.fontSize,
                        fontWeight = FontWeight.Bold,
                        fontFamily = helveticaFamily,
                        maxLines = 1,
                        overflow = TextOverflow.Ellipsis
                    )
                }
            }
        }
    }
}

//@Preview(showBackground = true)
//@Preview(showBackground = true, uiMode = UI_MODE_NIGHT_YES)
//@Preview(locale = "ar", showBackground = true)
@Preview(locale = "hi", showBackground = true)
//@Preview(locale = "ar", showBackground = true, uiMode = UI_MODE_NIGHT_YES)
@Composable
fun SettingsItemPreview() {
    val context = LocalContext.current
    SettingsContent(listOf(
        SettingsItem(
            Constants.SHARE_CODE,
            context.getString(R.string.share_app)
        ),
        SettingsItem(
            Constants.RATE_CODE,
            context.getString(R.string.app_rate)
        ),
        SettingsItem(
            Constants.CONTACT_CODE,
            context.getString(R.string.contact_us)
        ),
        SettingsItem(
            Constants.ERROR_REPORTING_CODE,
            context.getString(R.string.report_us)
        ),
        SettingsItem(
            Constants.APPS_CODE,
            context.getString(R.string.our_apps)
        ),
        SettingsItem(
            Constants.POLICY_CODE,
            context.getString(R.string.privacy_policy)
        ),
        SettingsItem(
            Constants.ABOUT_CODE,
            context.getString(R.string.about_us)
        ),
    ),
    ) {}
}