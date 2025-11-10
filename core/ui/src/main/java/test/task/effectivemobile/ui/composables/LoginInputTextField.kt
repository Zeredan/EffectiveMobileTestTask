package test.task.effectivemobile.ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import test.task.effectivemobile.ui.R
import test.task.effectivemobile.ui.themes.EffectiveMobileThemeManager

@Composable
fun LoginInputTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    hideSymbol: Char? = null
) {
    val colorScheme by EffectiveMobileThemeManager.colorScheme.collectAsState()
    val robotoFontFamily = EffectiveMobileThemeManager.RobotoFontFamily()
    Box(
        modifier = modifier
            .height(40.dp)
            .clip(RoundedCornerShape(30.dp))
            .background(colorResource(colorScheme.loginBgTextField))
            .padding(horizontal = 16.dp, vertical = 10.dp),
        contentAlignment = Alignment.CenterStart
    ) {
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            textStyle = TextStyle(fontSize = 15.sp, color = colorResource(colorScheme.textTextField)),
            cursorBrush = SolidColor(colorResource(colorScheme.textTextField)),
            visualTransformation = if (hideSymbol != null) PasswordVisualTransformation(hideSymbol) else VisualTransformation.None,
            modifier = Modifier.fillMaxSize()
        )
        if (value.isEmpty()) {
            Text(
                text = placeholder,
                fontSize = 15.sp,
                color = colorResource(colorScheme.textTextField),
                fontFamily = robotoFontFamily,
                fontWeight = FontWeight.W500
            )
        }
    }
}