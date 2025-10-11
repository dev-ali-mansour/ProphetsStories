package com.tibadev.alimansour.prophetstories.story.presentation.story_list.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalLayoutDirection
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.tibadev.alimansour.prophetstories.core.presentation.theme.L_PADDING
import com.tibadev.alimansour.prophetstories.core.presentation.theme.backgroundColor
import com.tibadev.alimansour.prophetstories.core.presentation.theme.textColor
import org.jetbrains.compose.resources.painterResource
import prophetsstories.composeapp.generated.resources.Res
import prophetsstories.composeapp.generated.resources.ic_launcher

@Composable
fun StoryListItem(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val roundedShape = remember(L_PADDING) { RoundedCornerShape(L_PADDING) }
    
    CompositionLocalProvider(LocalLayoutDirection provides LayoutDirection.Rtl) {
        Box(
            modifier = Modifier
                .height(IntrinsicSize.Min)
                .clickable { onClick() },
            contentAlignment = Alignment.CenterStart
        ) {
            Surface(
                modifier = Modifier
                    .fillMaxWidth(),
                color = backgroundColor.copy(alpha = 0.6f),
                shape = roundedShape
            ) {
                Row(
                    modifier = modifier
                        .fillMaxWidth()
                        .height(IntrinsicSize.Min)
                        .padding(vertical = 8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = text,
                        modifier = Modifier
                            .weight(1f)
                            .padding(horizontal = 10.dp),
                        color = textColor,
                        fontSize = MaterialTheme.typography.titleMedium.fontSize,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Image(
                        painter = painterResource(Res.drawable.ic_launcher),
                        contentDescription = null,
                        modifier = Modifier
                            .size(60.dp)
                            .padding(end = 10.dp),
                        contentScale = ContentScale.Fit
                    )
                }
            }
        }
    }
}