package test.task.effectivemobile.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import test.task.effectivemobile.combined_selector.CoursesRepositoryCombinedSelectorImpl
import test.task.effectivemobile.courses.Course
import test.task.effectivemobile.courses.CoursesResult
import test.task.effectivemobile.courses.repositories.CoursesRepository
import test.task.effectivemobile.saved.CoursesRepositorySavedImpl
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val coursesCombinedSelectorRepository: CoursesRepositoryCombinedSelectorImpl,
    private val savedCoursesRepository: CoursesRepositorySavedImpl
) : CoursesRepository {
    override suspend fun addNewCourse(course: Course) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCourses(courses: List<Course>) {
        TODO("Not yet implemented")
    }

    override fun getCoursesAsFlow(): Flow<CoursesResult?> {
        return coursesCombinedSelectorRepository.getCoursesAsFlow().map {
            it?.let { preResult ->
                if (preResult is CoursesResult.Retrieved) savedCoursesRepository.updateCourses(preResult.courses)
                if (preResult is CoursesResult.Error) savedCoursesRepository.getCourses() else preResult
            }
        }
    }

    override suspend fun getCourses(): CoursesResult {
        val preResult = coursesCombinedSelectorRepository.getCourses()
        if (preResult is CoursesResult.Retrieved) savedCoursesRepository.updateCourses(preResult.courses)
        return if (preResult is CoursesResult.Error) {
            savedCoursesRepository.getCourses()
        } else {
            preResult
        }
    }

}