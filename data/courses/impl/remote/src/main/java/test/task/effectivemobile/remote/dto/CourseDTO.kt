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
        return Course(id, title, "https://eu-images.contentstack.com/v3/assets/blt07f68461ccd75245/blt09a2ac83e51a0e06/661ce198092eb8747525079e/programming_20evolution.jpg", text, price, rate, startDate, hasLike, publishDate)
    }
}