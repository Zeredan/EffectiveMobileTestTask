package test.task.effectivemobile.settings.repositories

import kotlinx.coroutines.flow.Flow
import test.task.effectivemobile.settings.RemoteMode

interface SettingsRepository {
    suspend fun setIsLocalMode(value: Boolean)
    fun getIsLocalModeAsFlow() : Flow<Boolean>
    
    suspend fun setPreferredRemoteMode(value: RemoteMode)
    fun getPreferredRemoteModeAsFlow() : Flow<RemoteMode>
}