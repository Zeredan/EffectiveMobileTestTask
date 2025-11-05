package test.task.effectivemobile.retrofit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import test.task.effectivemobile.courses.Course
import test.task.effectivemobile.courses.repositories.CoursesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoursesRepositoryRemoteRetrofitImpl @Inject constructor(
    private val coursesRetrofitApiService: CoursesRetrofitApiService
) : CoursesRepository {
    override suspend fun addNewCourse(course: Course) {
        TODO("Not yet implemented")
    }

    override fun getCoursesAsFlow(): Flow<List<Course>> {
        return flow {
            emit(getCourses())
        }
    }

    override suspend fun getCourses(): List<Course> {
        return coursesRetrofitApiService.getCourses().toCourses()
    }

}