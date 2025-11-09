package test.task.effectivemobile.ui.themes


import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import kotlinx.coroutines.flow.MutableStateFlow
import test.task.effectivemobile.ui.R

object EffectiveMobileThemeManager {
    val colorScheme = MutableStateFlow<EMColorScheme>(EMColorScheme.DARK)
    val iconScheme = MutableStateFlow<EMIconScheme>(EMIconScheme.DARK)
    var isInitialized = MutableStateFlow(false)
    @Composable fun RobotoFontFamily() : FontFamily {
        return FontFamily(
            Font(R.font.roboto_regular, FontWeight.W400),
            Font(R.font.roboto_medium, FontWeight.W500),
            Font(R.font.roboto_semibold, FontWeight.W600)
        )
    }
}