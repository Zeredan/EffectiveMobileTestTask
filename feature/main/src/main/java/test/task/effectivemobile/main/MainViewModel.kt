package test.task.effectivemobile.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import test.task.effectivemobile.courses.repositories.CoursesRepository
import test.task.effectivemobile.courses.usecases.UCGetCoursesAsFlow
import test.task.effectivemobile.courses_favorite.repositories.repositories.FavoriteCoursesRepository
import test.task.effectivemobile.courses_favorite.repositories.usecases.UCAddToFavorites
import test.task.effectivemobile.courses_favorite.repositories.usecases.UCRemoveFromFavorites
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val ucGetCoursesAsFlow: UCGetCoursesAsFlow,
    private val ucAddToFavorites: UCAddToFavorites,
    private val ucRemoveFromFavorites: UCRemoveFromFavorites
) : ViewModel(){
    val courses = ucGetCoursesAsFlow()
        .stateIn(viewModelScope, SharingStarted.Companion.Eagerly, null)

    private val _searchText = MutableStateFlow("")
    val searchText = _searchText.asStateFlow()

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