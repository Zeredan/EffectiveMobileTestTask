package test.task.effectivemobile.combined_selector

import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.launch
import test.task.effectivemobile.courses.Course
import test.task.effectivemobile.courses.repositories.CoursesRepository
import test.task.effectivemobile.ktor.CoursesRepositoryRemoteKtorImpl
import test.task.effectivemobile.local.CoursesRepositoryLocalImpl
import test.task.effectivemobile.retrofit.CoursesRepositoryRemoteRetrofitImpl
import test.task.effectivemobile.settings.RemoteMode
import test.task.effectivemobile.settings.repositories.SettingsRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoursesRepositoryCombinedSelectorImpl @Inject constructor(
    private val settingsRepository: SettingsRepository,
    private val localImpl: CoursesRepositoryLocalImpl,
    private val remoteKtorImpl: CoursesRepositoryRemoteKtorImpl,
    private val remoteRetrofitImpl: CoursesRepositoryRemoteRetrofitImpl
) : CoursesRepository {
    private val resultingCoursesFlow = MutableStateFlow<List<Course>>(emptyList())
    private var currentCollectorJob: Job? = null
    private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.IO)

    init {
        coroutineScope.launch {
            settingsRepository.getIsLocalModeAsFlow().distinctUntilChanged().collect { isLocal ->
                currentCollectorJob?.cancel()
                if (isLocal) {
                    currentCollectorJob = coroutineScope.launch {
                        localImpl.getCoursesAsFlow().collect { lst ->
                            resultingCoursesFlow.value = lst
                        }
                    }
                } else {
                    currentCollectorJob = coroutineScope.launch modeSelector@ {
                        var remoteCollectorJob: Job? = null
                        settingsRepository.getPreferredRemoteModeAsFlow().collect { mode ->
                            remoteCollectorJob?.cancel()
                            when(mode) {
                                RemoteMode.KTOR -> {
                                    remoteCollectorJob = this.launch {
                                        remoteKtorImpl.getCoursesAsFlow().collect { lst ->
                                            resultingCoursesFlow.value = lst
                                        }
                                    }
                                }
                                RemoteMode.RETROFIT -> {
                                    remoteCollectorJob = this.launch {
                                        remoteRetrofitImpl.getCoursesAsFlow().collect { lst ->
                                            resultingCoursesFlow.value = lst
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    override suspend fun addNewCourse(course: Course) {
        TODO("Not yet implemented")
    }

    override fun getCoursesAsFlow(): Flow<List<Course>> {
        return resultingCoursesFlow
    }

    override suspend fun getCourses(): List<Course> {
        return resultingCoursesFlow.value
    }


}