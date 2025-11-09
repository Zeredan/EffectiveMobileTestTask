package test.task.effectivemobile.courses_favorite.repositories.usecases

import test.task.effectivemobile.courses_favorite.repositories.repositories.FavoriteCoursesRepository
import javax.inject.Inject


class UCRemoveFromFavorites @Inject constructor(
    private val favoritesRepository: FavoriteCoursesRepository
) {
    suspend operator fun invoke(courseId: Int) = favoritesRepository.removeCourseFromFavorites(courseId)
}