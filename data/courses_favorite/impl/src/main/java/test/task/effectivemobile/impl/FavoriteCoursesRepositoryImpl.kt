package test.task.effectivemobile.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import test.task.effectivemobile.courses_favorite.repositories.repositories.FavoriteCoursesRepository
import test.task.effectivemobile.database.dao.FavoriteCoursesDAO
import test.task.effectivemobile.database.entities.FavoriteCourseEntity
import javax.inject.Inject

class FavoriteCoursesRepositoryImpl @Inject constructor(
    private val favoriteCoursesDao: FavoriteCoursesDAO
) : FavoriteCoursesRepository {
    override suspend fun addCourseToFavorites(courseId: Int) {
        favoriteCoursesDao.insertFavoriteCourse(FavoriteCourseEntity(courseId))
    }

    override suspend fun removeCourseFromFavorites(courseId: Int) {
        favoriteCoursesDao.removeCourseFromFavorites(courseId)
    }

    override suspend fun updateFavoriteCourses(courses: List<Int>) {
        favoriteCoursesDao.clearCourses()
        favoriteCoursesDao.updateCourses(courses.map { FavoriteCourseEntity(it) })
    }

    override fun getFavoriteCoursesAsFlow(): Flow<List<Int>> {
        return favoriteCoursesDao.getAllCoursesAsFlow().map {
            it.map { courseEntity -> courseEntity.id }
        }
    }

    override suspend fun getFavoriteCourses(): List<Int> {
        return favoriteCoursesDao.getAllCourses().map { it.id }
    }

}