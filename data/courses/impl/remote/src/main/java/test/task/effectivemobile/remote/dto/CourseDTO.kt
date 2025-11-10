package test.task.effectivemobile.remote.dto

import test.task.effectivemobile.courses.Course


class CourseDTO(
    val id: Int,
    val title: String,
    //val imageUri: String,
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
        //course.imageUri,
        course.text,
        course.price,
        course.rate,
        course.startDate,
        course.hasLike,
        course.publishDate
    )
    
    fun toCourse() : Course {
        return Course(id, title, null, text, price, rate, startDate, hasLike, publishDate)
    }
}