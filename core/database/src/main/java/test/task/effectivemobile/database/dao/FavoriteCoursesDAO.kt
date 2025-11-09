package test.task.effectivemobile.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import test.task.effectivemobile.database.entities.CourseEntity
import test.task.effectivemobile.database.entities.FavoriteCourseEntity

@Dao
interface FavoriteCoursesDAO {
    @Query("SELECT * FROM FavoriteCourseEntity")
    fun getAllCoursesAsFlow(): Flow<List<FavoriteCourseEntity>>

    @Query("SELECT * FROM FavoriteCourseEntity")
    fun getAllCourses(): List<FavoriteCourseEntity>

    @Query("DELETE FROM FavoriteCourseEntity WHERE id = :courseId")
    suspend fun removeCourseFromFavorites(courseId: Int)
    
    @Update
    fun updateCourses(courseIds: List<FavoriteCourseEntity>)

    @Query("DELETE FROM FavoriteCourseEntity")
    fun clearCourses(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavoriteCourse(courseIdEntity: FavoriteCourseEntity)
}