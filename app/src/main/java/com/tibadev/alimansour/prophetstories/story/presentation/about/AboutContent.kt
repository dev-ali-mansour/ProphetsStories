package com.tibadev.alimansour.prophetstories.story.presentation.about

import android.content.Context
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.LayoutDirection
import com.tibadev.alimansour.prophetstories.R
import com.tibadev.alimansour.prophetstories.core.presentation.theme.LOGO_HEIGHT
import com.tibadev.alimansour.prophetstories.core.presentation.theme.LOGO_WIDTH
import com.tibadev.alimansour.prophetstories.core.presentation.theme.M_PADDING
import com.tibadev.alimansour.prophetstories.core.presentation.theme.SOCIAL_ICON_SIZE
import com.tibadev.alimansour.prophetstories.core.presentation.theme.S_PADDING
import com.tibadev.alimansour.prophetstories.core.presentation.theme.helveticaFamily
import com.tibadev.alimansour.prophetstories.core.presentation.theme.tintColor
import com.tibadev.alimansour.prophetstories.core.presentation.util.openLink


@Composable
fun AboutContent() {
    val context = LocalContext.current
    val version = "${stringResource(id = R.string.version)} ${context.getAppVersion()}"
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {

        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize()
                .padding(M_PADDING)
        ) {
            Text(
                text = version,
                color = tintColor.copy(alpha = 0.6f),
                fontSize = MaterialTheme.typography.titleLarge.fontSize,
                fontFamily = helveticaFamily,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Text(
                text = stringResource(id = R.string.about),
                color = tintColor.copy(alpha = 0.6f),
                fontSize = MaterialTheme.typography.titleMedium.fontSize,
                fontFamily = helveticaFamily,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(S_PADDING))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    modifier = Modifier
                        .size(LOGO_WIDTH, LOGO_HEIGHT)
                        .clickable {
                            context.openLink(context.getString(R.string.website_url))
                        },
                    painter = painterResource(id = R.drawable.logo),
                    contentScale = ContentScale.Fit,
                    contentDescription = null
                )
            }
            Spacer(modifier = Modifier.height(S_PADDING))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Row(
                    modifier = Modifier.size(LOGO_WIDTH, LOGO_HEIGHT),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Image(
                        modifier = Modifier
                            .size(SOCIAL_ICON_SIZE)
                            .clickable {
                                context.openLink(context.getString(R.string.facebook_url))
                            },
                        painter = painterResource(id = R.drawable.ic_social_facebook),
                        contentScale = ContentScale.Fit,
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier
                            .size(SOCIAL_ICON_SIZE)
                            .clickable {
                                context.openLink(context.getString(R.string.twitter_url))
                            },
                        painter = painterResource(id = R.drawable.ic_social_twitter),
                        contentScale = ContentScale.Fit,
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier
                            .size(SOCIAL_ICON_SIZE)
                            .clickable {
                                context.openLink(context.getString(R.string.linkedin_url))
                            },
                        painter = painterResource(id = R.drawable.ic_social_linkedin),
                        contentScale = ContentScale.Fit,
                        contentDescription = null
                    )
                    Image(
                        modifier = Modifier
                            .size(SOCIAL_ICON_SIZE)
                            .clickable {
                                context.openLink(context.getString(R.string.youtube_channel_url))
                            },
                        painter = painterResource(id = R.drawable.ic_social_youtube),
                        contentScale = ContentScale.Fit,
                        contentDescription = null
                    )
                }
            }
        }
    }
}

private fun Context.getAppVersion(): String {
    val appVersion = StringBuilder()
    try {
        appVersion.append(packageManager.getPackageInfo(packageName, 0).versionName)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return appVersion.toString()
}

@Preview
@Composable
fun AboutContentPreview() {
    AboutContent()
}