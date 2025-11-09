package test.task.effectivemobile.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import test.task.effectivemobile.courses.CoursesResult
import test.task.effectivemobile.courses.usecases.UCGetCoursesAsFlow
import test.task.effectivemobile.courses_favorite.repositories.usecases.UCAddToFavorites
import test.task.effectivemobile.courses_favorite.repositories.usecases.UCRemoveFromFavorites
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val ucGetCoursesAsFlow: UCGetCoursesAsFlow,
    private val ucAddToFavorites: UCAddToFavorites,
    private val ucRemoveFromFavorites: UCRemoveFromFavorites
) : ViewModel(){
    val courses = ucGetCoursesAsFlow()
        .stateIn(viewModelScope, SharingStarted.Companion.Eagerly, null)

    val resultingCourses = courses
        .map { courses ->
            val list = when (courses) {
                null, is CoursesResult.Error -> emptyList()
                is CoursesResult.Cached -> courses.courses
                is CoursesResult.Retrieved -> courses.courses
            }
            list.filter { it.hasLike }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun addToFavorites(id: Int) {
        viewModelScope.launch {
            ucAddToFavorites(id)
        }
    }
    fun removeFromFavorites(id: Int) {
        viewModelScope.launch {
            ucRemoveFromFavorites(id)
        }
    }
}