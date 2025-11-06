package test.task.effectivemobile.courses

sealed interface CoursesResult {
    data class Retrieved(val courses: List<Course>) : CoursesResult
    data class Cached(val courses: List<Course>) : CoursesResult
    data class Error(val message: String) : CoursesResult
}