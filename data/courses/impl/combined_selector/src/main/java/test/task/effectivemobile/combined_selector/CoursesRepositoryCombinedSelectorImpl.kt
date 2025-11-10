package test.task.effectivemobile.combined_selector

import dagger.hilt.android.scopes.ActivityScoped
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import test.task.effectivemobile.courses.Course
import test.task.effectivemobile.courses.CoursesResult
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
    private val resultingCoursesFlow = MutableStateFlow<CoursesResult?>(null)
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

    override suspend fun updateCourses(courses: List<Course>) {
        TODO("Not yet implemented")
    }

    override suspend fun reloadCourses() {
        if (settingsRepository.getIsLocalModeAsFlow().first()) {
            localImpl.reloadCourses()
        } else {
            when (settingsRepository.getPreferredRemoteModeAsFlow().first()) {
                RemoteMode.KTOR -> remoteKtorImpl.reloadCourses()
                RemoteMode.RETROFIT -> remoteRetrofitImpl.reloadCourses()
            }
        }
    }

    override fun getCoursesAsFlow(): Flow<CoursesResult?> {
        return resultingCoursesFlow
    }

    override suspend fun getCourses(): CoursesResult {
        val result = if (settingsRepository.getIsLocalModeAsFlow().first()) {
            localImpl.getCourses()
        } else {
            when (settingsRepository.getPreferredRemoteModeAsFlow().first()) {
                RemoteMode.KTOR -> remoteKtorImpl.getCourses()
                RemoteMode.RETROFIT -> remoteRetrofitImpl.getCourses()
            }
        }
        return result
    }


}