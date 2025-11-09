package test.task.effectivemobile.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import test.task.effectivemobile.ui.themes.EffectiveMobileThemeManager

@Composable
fun VKButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val colorScheme by EffectiveMobileThemeManager.colorScheme.collectAsState()
    val iconScheme by EffectiveMobileThemeManager.iconScheme.collectAsState()
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(colorResource(colorScheme.vkBackground))
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(26.dp, 16.dp),
            painter = painterResource(iconScheme.iconVk),
            contentDescription = null
        )
    }
}