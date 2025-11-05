package test.task.effectivemobile.remote.dto

import test.task.effectivemobile.courses.Course

data class CoursesDTO(
    val courses: List<CourseDTO>
) {
    constructor(data: List<Course>) : this(data.map { CourseDTO(it) })
    fun toCourses() : List<Course> {
        return courses.map {
            it.toCourse()
        }
    }
}