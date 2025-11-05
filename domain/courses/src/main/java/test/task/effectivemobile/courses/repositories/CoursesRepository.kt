package test.task.effectivemobile.courses.repositories

import kotlinx.coroutines.flow.Flow
import test.task.effectivemobile.courses.Course

interface CoursesRepository {
    suspend fun addNewCourse(course: Course)
    fun getCoursesAsFlow() : Flow<List<Course>>
    suspend fun getCourses() : List<Course>
}