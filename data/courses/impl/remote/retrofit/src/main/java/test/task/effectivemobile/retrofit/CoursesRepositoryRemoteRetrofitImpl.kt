package test.task.effectivemobile.retrofit

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.onStart
import test.task.effectivemobile.courses.Course
import test.task.effectivemobile.courses.CoursesResult
import test.task.effectivemobile.courses.repositories.CoursesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoursesRepositoryRemoteRetrofitImpl @Inject constructor(
    private val coursesRetrofitApiService: CoursesRetrofitApiService
) : CoursesRepository {
    private val reloadTrigger = MutableSharedFlow<CoursesResult?>()
    override suspend fun addNewCourse(course: Course) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCourses(courses: List<Course>) {
        TODO("Not yet implemented")
    }

    override suspend fun reloadCourses() {
        reloadTrigger.emit(getCourses())
    }

    override fun getCoursesAsFlow(): Flow<CoursesResult?> {
        return reloadTrigger
            .onStart {
                emit(getCourses())
            }
    }

    override suspend fun getCourses(): CoursesResult {
        return try{
            CoursesResult.Retrieved(coursesRetrofitApiService.getCourses().toCourses())
        } catch (e: Exception) {
            CoursesResult.Error(e.message ?: "Unknown error")
        }
    }

}