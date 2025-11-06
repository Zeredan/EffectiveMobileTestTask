package test.task.effectivemobile.ktor

import com.google.gson.Gson
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.request
import io.ktor.client.statement.bodyAsText
import io.ktor.http.HttpMethod
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import test.task.effectivemobile.courses.Course
import test.task.effectivemobile.courses.CoursesResult
import test.task.effectivemobile.courses.repositories.CoursesRepository
import test.task.effectivemobile.remote.dto.CoursesDTO
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoursesRepositoryRemoteKtorImpl @Inject constructor(
    private val httpClient: HttpClient
) : CoursesRepository {
    private val gson = Gson()
    override suspend fun addNewCourse(course: Course) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCourses(courses: List<Course>) {
        TODO("Not yet implemented")
    }

    override fun getCoursesAsFlow(): Flow<CoursesResult?> {
        return flow{
            emit(getCourses())
        }
    }

    override suspend fun getCourses(): CoursesResult {
        return try {
            CoursesResult.Retrieved(
                httpClient.request(
                    urlString = "https://drive.usercontent.google.com/u/0/uc?id=15arTK7XT2b7Yv\n" +
                            "4BJsmDctA4Hg-BbS8-q"
                ) {
                    method = HttpMethod.Get
                }.bodyAsText().let { gson.fromJson(it, CoursesDTO::class.java) }.toCourses()
            )
        } catch (e: Exception) {
            CoursesResult.Error(e.message ?: "Unknown error")
        }
    }

}