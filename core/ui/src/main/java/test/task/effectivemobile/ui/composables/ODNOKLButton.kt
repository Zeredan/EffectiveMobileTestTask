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
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.graphics.Shader
import androidx.compose.ui.graphics.ShaderBrush
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import test.task.effectivemobile.ui.themes.EffectiveMobileThemeManager

@Composable
fun ODNOKLButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    val colorScheme by EffectiveMobileThemeManager.colorScheme.collectAsState()
    val iconScheme by EffectiveMobileThemeManager.iconScheme.collectAsState()

    val startColor = colorResource(colorScheme.odnoklLbBackground)
    val endColor = colorResource(colorScheme.odnoklRtBackground)

    Box(
        modifier = modifier
            .clip(RoundedCornerShape(30.dp))
            .background(object : ShaderBrush() {
                override fun createShader(size: Size): Shader {
                    return LinearGradientShader(
                        Offset(0f, size.height),
                        Offset(size.width, 0f),
                        listOf(startColor, endColor),
                    )
                }

            })
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier
                .size(16.dp, 26.dp),
            painter = painterResource(iconScheme.iconOdnokl),
            contentDescription = null
        )
    }
}