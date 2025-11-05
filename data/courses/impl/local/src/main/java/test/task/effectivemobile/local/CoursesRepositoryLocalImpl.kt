package test.task.effectivemobile.local

import android.content.Context
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import test.task.effectivemobile.courses.Course
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

    override fun getCoursesAsFlow(): Flow<List<Course>> {
        return flow {
            emit(getCourses())
        }
    }

    override suspend fun getCourses(): List<Course> {
        if (cachedCourses == null) {
            val inputStream = context.resources.openRawResource(R.raw.courses)
            val json = inputStream.bufferedReader().use { it.readText() }
            cachedCourses = gson.fromJson(json, Array<Course>::class.java).toList()
        }
        return cachedCourses ?: emptyList()
    }
}