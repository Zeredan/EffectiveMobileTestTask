package test.task.effectivemobile.impl

import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import test.task.effectivemobile.settings.RemoteMode
import test.task.effectivemobile.settings.repositories.SettingsRepository
import test.task.effectivemobile.ui.themes.EMTheme
import javax.inject.Inject

class SettingsRepositoryImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
) : SettingsRepository {
    private object PreferencesKeys {
        val IS_LOCAL_MODE = androidx.datastore.preferences.core.booleanPreferencesKey("is_local_mode")
        val REMOTE_MODE = androidx.datastore.preferences.core.stringPreferencesKey("remote_mode")
        val APP_THEME = androidx.datastore.preferences.core.stringPreferencesKey("app_theme")
    }

    override suspend fun setIsLocalMode(value: Boolean) {
        dataStore.edit { 
            it[PreferencesKeys.IS_LOCAL_MODE] = value
        }
    }
    override fun getIsLocalModeAsFlow(): Flow<Boolean> {
        return dataStore.data.map { preferences ->
            preferences[PreferencesKeys.IS_LOCAL_MODE] ?: false
        }
    }

    override suspend fun setPreferredRemoteMode(value: RemoteMode) {
        dataStore.edit { 
            it[PreferencesKeys.REMOTE_MODE] = value.name
        }
    }
    override fun getPreferredRemoteModeAsFlow(): Flow<RemoteMode> {
        return dataStore.data.map { preferences ->
            val modeString = preferences[PreferencesKeys.REMOTE_MODE] ?: RemoteMode.KTOR.name
            RemoteMode.valueOf(modeString)
        }
    }

    override suspend fun setAppTheme(value: EMTheme) {
        dataStore.edit {
            it[PreferencesKeys.APP_THEME] = value.name
        }
    }
    override fun getAppThemeAsFlow(): Flow<EMTheme> {
        return dataStore.data.map { preferences ->
            val themeString = preferences[PreferencesKeys.APP_THEME] ?: EMTheme.DARK.name
            EMTheme.valueOf(themeString)
        }
    }
}