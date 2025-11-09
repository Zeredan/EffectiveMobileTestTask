package test.task.effectivemobile.courses.usecases

import test.task.effectivemobile.courses.repositories.CoursesRepository
import javax.inject.Inject


class UCGetCoursesAsFlow @Inject constructor(
    private val coursesRepository: CoursesRepository
) {
    operator fun invoke() = coursesRepository.getCoursesAsFlow()
}