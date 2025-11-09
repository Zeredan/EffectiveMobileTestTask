package test.task.effectivemobile.settings.usecases

import test.task.effectivemobile.settings.repositories.SettingsRepository
import javax.inject.Inject

class UCGetAppThemeAsFlow @Inject constructor(
    private val settingsRepository: SettingsRepository
) {
    operator fun invoke() = settingsRepository.getAppThemeAsFlow()
}