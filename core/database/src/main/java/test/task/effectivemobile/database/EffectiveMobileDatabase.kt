package test.task.effectivemobile.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import test.task.effectivemobile.database.dao.CoursesDAO
import test.task.effectivemobile.database.dao.FavoriteCoursesDAO
import test.task.effectivemobile.database.entities.CourseEntity
import test.task.effectivemobile.database.entities.FavoriteCourseEntity

@Database(entities = [CourseEntity::class, FavoriteCourseEntity::class], version = 1)
@TypeConverters(Converters::class)
abstract class EffectiveMobileDatabase : RoomDatabase() {
    abstract val coursesDao: CoursesDAO
    abstract val favoriteCoursesDao: FavoriteCoursesDAO
}