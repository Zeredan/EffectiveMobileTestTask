package test.task.effectivemobile.ui.composables

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import test.task.effectivemobile.ui.R
import test.task.effectivemobile.ui.themes.EffectiveMobileThemeManager

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String
) {
    val colorScheme by EffectiveMobileThemeManager.colorScheme.collectAsState()
    val iconScheme by EffectiveMobileThemeManager.iconScheme.collectAsState()
    val robotoFontFamily = EffectiveMobileThemeManager.RobotoFontFamily()
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .height(56.dp)
            .clip(RoundedCornerShape(30.dp)),
        shape = RoundedCornerShape(30.dp),
        placeholder = {
            Text(
                text = placeholder,
                style = TextStyle(
                    fontSize = 15.sp,
                    fontFamily = robotoFontFamily,
                )
            )
        },
        leadingIcon = {
            Image(
                modifier = Modifier.size(24.dp),
                painter = painterResource(iconScheme.iconSearch),
                contentDescription = null
            )
        },
        textStyle = TextStyle(
            fontSize = 15.sp,
            fontFamily = robotoFontFamily,
            color = colorResource(colorScheme.textTextField),
        ),
        colors = TextFieldDefaults.colors(
            focusedContainerColor = colorResource(colorScheme.mainBgElement),
            unfocusedContainerColor = colorResource(colorScheme.mainBgElement),
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}