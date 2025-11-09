package test.task.effectivemobile.remote.dto

import test.task.effectivemobile.courses.Course

data class CoursesDTO(
    val courses: List<CourseDTO>
) {
    fun toCourses() : List<Course> {
        return courses.map {
            it.toCourse()
        }
    }
}