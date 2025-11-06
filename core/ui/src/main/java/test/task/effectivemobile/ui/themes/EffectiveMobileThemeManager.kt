package test.task.effectivemobile.ui.themes

import kotlinx.coroutines.flow.MutableStateFlow

object EffectiveMobileThemeManager {
    val colorScheme = MutableStateFlow<EMColorScheme>(EMColorScheme.DARK)
    val iconScheme = MutableStateFlow<EMColorScheme>(EMColorScheme.DARK)
    var isInitialized = MutableStateFlow(false)
}