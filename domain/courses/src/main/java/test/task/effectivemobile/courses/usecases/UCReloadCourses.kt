package test.task.effectivemobile.courses.usecases

import test.task.effectivemobile.courses.repositories.CoursesRepository
import javax.inject.Inject


class UCReloadCourses @Inject constructor(
    private val coursesRepository: CoursesRepository
) {
    suspend operator fun invoke() {
        coursesRepository.reloadCourses()
    }
}