package test.task.effectivemobile.mock

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import test.task.effectivemobile.courses.Course
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

    override fun getCoursesAsFlow(): Flow<List<Course>> {
        return flow{
            emit(
                constList
            )
        }
    }

    override suspend fun getCourses(): List<Course> {
        return constList
    }


}