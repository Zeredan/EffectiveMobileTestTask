package test.task.effectivemobile.courses_favorite.repositories.repositories

import kotlinx.coroutines.flow.Flow

interface FavoriteCoursesRepository {
    suspend fun addCourseToFavorites(courseId: Int)
    suspend fun removeCourseFromFavorites(courseId: Int)
    suspend fun updateFavoriteCourses(courses: List<Int>)

    fun getFavoriteCoursesAsFlow() : Flow<List<Int>>
    suspend fun getFavoriteCourses() : List<Int>
}
