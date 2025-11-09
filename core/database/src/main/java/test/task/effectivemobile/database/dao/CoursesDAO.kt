package test.task.effectivemobile.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow
import test.task.effectivemobile.database.entities.CourseEntity

@Dao
interface CoursesDAO {
    @Query("SELECT * FROM CourseEntity")
    fun getAllCoursesAsFlow(): Flow<List<CourseEntity>>

    @Query("SELECT * FROM CourseEntity")
    fun getAllCourses(): List<CourseEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourses(courses: List<CourseEntity>)

    @Update
    suspend fun updateCourses(courses: List<CourseEntity>)

    @Query("DELETE FROM CourseEntity")
    suspend fun clearCourses(): Int

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCourse(course: CourseEntity)

    @Transaction
    suspend fun replaceAllCourses(newCourses: List<CourseEntity>) {
        clearCourses()
        insertCourses(newCourses)
    }
}