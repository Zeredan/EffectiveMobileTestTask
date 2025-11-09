package test.task.effectivemobile.impl

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import test.task.effectivemobile.courses_favorite.repositories.repositories.FavoriteCoursesRepository
import test.task.effectivemobile.database.dao.FavoriteCoursesDAO
import test.task.effectivemobile.database.entities.FavoriteCourseEntity
import javax.inject.Inject

class FavoriteCoursesRepositoryImpl @Inject constructor(
    private val favoriteCoursesDao: FavoriteCoursesDAO
) : FavoriteCoursesRepository {
    override suspend fun addCourseToFavorites(courseId: Int) {
        withContext(Dispatchers.IO) {
            println("FCRI: add course to favorites: $courseId")
            favoriteCoursesDao.insertFavoriteCourse(FavoriteCourseEntity(courseId))
        }
    }

    override suspend fun removeCourseFromFavorites(courseId: Int) {
        withContext(Dispatchers.IO) {
            println("FCRI: remove course to favorites: $courseId")
            favoriteCoursesDao.removeCourseFromFavorites(courseId)
        }
    }

    override suspend fun updateFavoriteCourses(courses: List<Int>) {
        withContext(Dispatchers.IO) {
            favoriteCoursesDao.clearCourses()
            favoriteCoursesDao.updateCourses(courses.map { FavoriteCourseEntity(it) })
        }
    }

    override fun getFavoriteCoursesAsFlow(): Flow<List<Int>> {
        return favoriteCoursesDao.getAllCoursesAsFlow().map {
            it.map { courseEntity -> courseEntity.id }
        }
    }

    override suspend fun getFavoriteCourses(): List<Int> {
        return withContext(Dispatchers.IO) {
            favoriteCoursesDao.getAllCourses().map { it.id }
        }
    }

}