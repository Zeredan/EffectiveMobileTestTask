package test.task.effectivemobile.database.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class FavoriteCourseEntity (
    @PrimaryKey
    val id: Int
)