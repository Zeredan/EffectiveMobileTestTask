package test.task.effectivemobile.main

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
import test.task.effectivemobile.courses.repositories.CoursesRepository
import test.task.effectivemobile.courses.usecases.UCGetCoursesAsFlow
import test.task.effectivemobile.courses.usecases.UCReloadCourses
import test.task.effectivemobile.courses_favorite.repositories.repositories.FavoriteCoursesRepository
import test.task.effectivemobile.courses_favorite.repositories.usecases.UCAddToFavorites
import test.task.effectivemobile.courses_favorite.repositories.usecases.UCRemoveFromFavorites
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val ucGetCoursesAsFlow: UCGetCoursesAsFlow,
    private val ucAddToFavorites: UCAddToFavorites,
    private val ucRemoveFromFavorites: UCRemoveFromFavorites,
    private val ucReloadCourses: UCReloadCourses
) : ViewModel(){
    val courses = ucGetCoursesAsFlow()
        .stateIn(viewModelScope, SharingStarted.Companion.Eagerly, null)

    private val _isReloading = MutableStateFlow(false)
    val isReloading = _isReloading.asStateFlow()

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

    private val _coursesSortAscendingMode: MutableStateFlow<Boolean?> = MutableStateFlow(null)
    val coursesSortAscendingMode = _coursesSortAscendingMode.asStateFlow()

    val resultingCourses = courses
        .combine(searchText) { courses, searchText ->
            when(courses) {
                null, is CoursesResult.Error -> emptyList()
                is CoursesResult.Cached -> courses.courses
                is CoursesResult.Retrieved -> courses.courses
            }.filter { course -> course.title.contains(searchText, ignoreCase = true) }
        }
        .combine(coursesSortAscendingMode) { unsortedCourses, sortAscendingMode ->
            sortAscendingMode?.let {
                if (it) unsortedCourses.sortedBy { course -> course.publishDate }
                else unsortedCourses.sortedByDescending { course -> course.publishDate }
            } ?: unsortedCourses
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun toggleSortMode() {
        _coursesSortAscendingMode.value =
            when(_coursesSortAscendingMode.value) {
                null -> true
                true -> false
                false -> true
            }
    }
    fun reloadCourses() {
        viewModelScope.launch {
            _isReloading.value = true
            ucReloadCourses()
            _isReloading.value = false
        }
    }
    fun setSearchText(text: String) {
        _searchText.value = text
    }
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