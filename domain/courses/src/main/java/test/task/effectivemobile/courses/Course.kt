package test.task.effectivemobile.courses

data class Course(
    val id: Int,
    val title: String,
    val imageUri: String = "https://www.excelsior.edu/wp-content/uploads/2025/01/computer-programming-vs-computer-science-image_blog.jpg",
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)