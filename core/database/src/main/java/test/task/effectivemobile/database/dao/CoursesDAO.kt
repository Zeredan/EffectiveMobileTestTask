package test.task.effectivemobile.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import test.task.effectivemobile.database.entities.CourseEntity

@Dao
interface CoursesDAO {
    @Query("SELECT * FROM CourseEntity")
    fun getAllCoursesAsFlow(): Flow<List<CourseEntity>>

    @Query("SELECT * FROM CourseEntity")
    fun getAllCourses(): List<CourseEntity>

    @Update
    fun updateCourses(courses: List<CourseEntity>)

    @Query("DELETE FROM CourseEntity")
    fun clearCourses(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCourse(course: CourseEntity)
}