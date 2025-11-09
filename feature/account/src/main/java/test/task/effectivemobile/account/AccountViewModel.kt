package test.task.effectivemobile.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import test.task.effectivemobile.courses.CoursesResult
import test.task.effectivemobile.courses.usecases.UCGetCoursesAsFlow
import test.task.effectivemobile.courses_favorite.repositories.usecases.UCAddToFavorites
import test.task.effectivemobile.courses_favorite.repositories.usecases.UCRemoveFromFavorites
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
) : ViewModel(){

}