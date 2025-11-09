package test.task.effectivemobile.remote.dto

import test.task.effectivemobile.courses.Course


class CourseDTO(
    val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
) {
    constructor(course: Course) : this(
        course.id,
        course.title,
        course.text,
        course.price,
        course.rate,
        course.startDate,
        course.hasLike,
        course.publishDate
    )
    
    fun toCourse() : Course {
        return Course(id, title, text, price, rate, startDate, hasLike, publishDate)
    }
}