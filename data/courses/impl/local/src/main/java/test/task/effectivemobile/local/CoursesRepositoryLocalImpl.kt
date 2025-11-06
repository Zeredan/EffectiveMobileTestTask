package test.task.effectivemobile.local

import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import test.task.effectivemobile.courses.Course
import test.task.effectivemobile.courses.CoursesResult
import test.task.effectivemobile.courses.repositories.CoursesRepository
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CoursesRepositoryLocalImpl @Inject constructor(
    @ApplicationContext private val context: Context
) : CoursesRepository {
    private val gson = Gson()
    private var cachedCourses: List<Course>? = null

    override suspend fun addNewCourse(course: Course) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCourses(courses: List<Course>) {
        TODO("Not yet implemented")
    }

    override fun getCoursesAsFlow(): Flow<CoursesResult?> {
        return flow {
            emit(getCourses())
        }
    }

    override suspend fun getCourses(): CoursesResult {
        return try {
            if (cachedCourses == null) {
                val inputStream = context.resources.openRawResource(R.raw.courses)
                val json = inputStream.bufferedReader().use { it.readText() }
                cachedCourses = gson.fromJson(json, Array<Course>::class.java).toList()
            }
            CoursesResult.Retrieved(cachedCourses ?: emptyList())
        } catch (e: Exception) {
            CoursesResult.Error(e.message ?: "Unknown error")
        }
    }
}