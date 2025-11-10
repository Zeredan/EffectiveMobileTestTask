package test.task.effectivemobile.mock

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import test.task.effectivemobile.courses.Course
import test.task.effectivemobile.courses.CoursesResult
import test.task.effectivemobile.courses.repositories.CoursesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoursesRepositoryMock @Inject constructor(

) : CoursesRepository {
    private val constList = listOf(
        Course(1, "qwe", "ere", "12", "5", "0301", true, "0303")
    )

    override suspend fun addNewCourse(course: Course) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCourses(courses: List<Course>) {
        TODO("Not yet implemented")
    }

    override suspend fun reloadCourses() {
        TODO("Not yet implemented")
    }

    override fun getCoursesAsFlow(): Flow<CoursesResult> {
        return flow{
            emit(
                getCourses()
            )
        }
    }

    override suspend fun getCourses(): CoursesResult {
        return CoursesResult.Retrieved(constList)
    }


}