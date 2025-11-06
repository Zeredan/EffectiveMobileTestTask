package test.task.effectivemobile.saved

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import test.task.effectivemobile.courses.Course
import test.task.effectivemobile.courses.CoursesResult
import test.task.effectivemobile.courses.repositories.CoursesRepository
import test.task.effectivemobile.database.dao.CoursesDAO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoursesRepositorySavedImpl @Inject constructor(
    private val coursesDAO: CoursesDAO,
) : CoursesRepository {
    override suspend fun addNewCourse(course: Course) {
        coursesDAO.insertCourse(course.toCourseEntity())
    }

    override suspend fun updateCourses(courses: List<Course>) {
        coursesDAO.clearCourses()
        coursesDAO.updateCourses(courses.map { it.toCourseEntity() })
    }

    override fun getCoursesAsFlow(): Flow<CoursesResult?> {
        return coursesDAO.getAllCoursesAsFlow().map { lst ->
            CoursesResult.Cached(lst.map { it.toCourse() })
        }
    }

    override suspend fun getCourses(): CoursesResult {
        return try {
            val lst = coursesDAO.getAllCourses()
            CoursesResult.Cached(lst.map { it.toCourse() })
        } catch (e: Exception) {
            CoursesResult.Error(e.message ?: "Unknown error")
        }
    }

}