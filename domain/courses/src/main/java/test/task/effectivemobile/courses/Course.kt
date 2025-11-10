package test.task.effectivemobile.courses

data class Course(
    val id: Int,
    val title: String,
    val imageUri: String = "https://portscaner.ru/Files/Wallpaper/1024x768/5fc4f836b358f.jpg",
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val hasLike: Boolean,
    val publishDate: String
)