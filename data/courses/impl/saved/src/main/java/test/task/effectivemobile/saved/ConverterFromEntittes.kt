package test.task.effectivemobile.saved

import test.task.effectivemobile.courses.Course
import test.task.effectivemobile.database.entities.CourseEntity

fun CourseEntity.toCourse() = Course(id, title, imageUri, text, price, rate, startDate, hasLike, publishDate)

fun Course.toCourseEntity() = CourseEntity(id, title, imageUri, text, price, rate, startDate, hasLike, publishDate)