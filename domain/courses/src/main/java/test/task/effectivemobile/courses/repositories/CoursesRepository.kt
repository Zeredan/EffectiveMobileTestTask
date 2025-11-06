package test.task.effectivemobile.courses.repositories

import kotlinx.coroutines.flow.Flow
import test.task.effectivemobile.courses.Course
import test.task.effectivemobile.courses.CoursesResult

interface CoursesRepository {
    suspend fun addNewCourse(course: Course)
    suspend fun updateCourses(courses: List<Course>)

    fun getCoursesAsFlow() : Flow<CoursesResult?>
    suspend fun getCourses() : CoursesResult
}