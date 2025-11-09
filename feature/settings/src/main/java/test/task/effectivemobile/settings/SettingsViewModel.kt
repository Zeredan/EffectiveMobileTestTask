package test.task.effectivemobile.settings

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import test.task.effectivemobile.settings.usecases.UCGetAppThemeAsFlow
import test.task.effectivemobile.ui.themes.EMTheme
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val ucGetAppThemeAsFlow: UCGetAppThemeAsFlow
) : ViewModel() {
    val appTheme = ucGetAppThemeAsFlow()
        .stateIn(viewModelScope, SharingStarted.Eagerly, EMTheme.DARK)
}