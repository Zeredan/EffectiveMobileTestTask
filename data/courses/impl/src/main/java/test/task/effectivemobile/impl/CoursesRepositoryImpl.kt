package test.task.effectivemobile.impl

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import test.task.effectivemobile.combined_selector.CoursesRepositoryCombinedSelectorImpl
import test.task.effectivemobile.courses.Course
import test.task.effectivemobile.courses.CoursesResult
import test.task.effectivemobile.courses.repositories.CoursesRepository
import test.task.effectivemobile.courses_favorite.repositories.repositories.FavoriteCoursesRepository
import test.task.effectivemobile.saved.CoursesRepositorySavedImpl
import javax.inject.Inject

class CoursesRepositoryImpl @Inject constructor(
    private val coursesCombinedSelectorRepository: CoursesRepositoryCombinedSelectorImpl,
    private val savedCoursesRepository: CoursesRepositorySavedImpl,
    private val favoriteCoursesRepository: FavoriteCoursesRepository
) : CoursesRepository {
    override suspend fun addNewCourse(course: Course) {
        TODO("Not yet implemented")
    }

    override suspend fun updateCourses(courses: List<Course>) {
        TODO("Not yet implemented")
    }

    override suspend fun reloadCourses() {
        coursesCombinedSelectorRepository.reloadCourses()
    }

    override fun getCoursesAsFlow(): Flow<CoursesResult?> {
        return combine(
            coursesCombinedSelectorRepository.getCoursesAsFlow(),
            savedCoursesRepository.getCoursesAsFlow()
        ) { combinedResult, localResult ->
            println("FBT1: ${combinedResult?.javaClass?.simpleName ?: "null"} ${localResult?.javaClass?.simpleName ?: "null"}")
            localResult?.let{println("FBT2: localResult: ${(it as CoursesResult.Cached).courses}")}
            when (combinedResult) {
                is CoursesResult.Retrieved -> {
                    println("FBT3: ${combinedResult.courses.size} ${(localResult as CoursesResult.Cached).courses.size}")
                    println("FBT4: ${combinedResult.courses == localResult.courses}")
                    if (combinedResult.courses != localResult.courses) savedCoursesRepository.updateCourses(combinedResult.courses)
                    combinedResult
                }

                is CoursesResult.Error -> localResult
                else -> combinedResult
            }
        }.run{
            combine(
                this,
                favoriteCoursesRepository.getFavoriteCoursesAsFlow()
            ) { preResult, favoriteCourses ->
                when(preResult) {
                    is CoursesResult.Retrieved -> {
                        preResult.copy(courses = preResult.courses.map { course ->
                            course.copy(hasLike = favoriteCourses.contains(course.id))
                        })
                    }
                    is CoursesResult.Cached -> {
                        preResult.copy(courses = preResult.courses.map { course ->
                            course.copy(hasLike = favoriteCourses.contains(course.id))
                        })
                    }

                    else -> preResult
                }
            }
        }
    }

    override suspend fun getCourses(): CoursesResult {
        val retrieved = coursesCombinedSelectorRepository.getCourses()
        if (retrieved is CoursesResult.Retrieved) savedCoursesRepository.updateCourses(retrieved.courses)
        val preResult = if (retrieved is CoursesResult.Error) {
            savedCoursesRepository.getCourses()
        } else {
            retrieved
        }
        val favoriteCourses = favoriteCoursesRepository.getFavoriteCourses()

        val result = when(preResult) {
            is CoursesResult.Retrieved -> {
                preResult.copy(courses = preResult.courses.map { course ->
                    course.copy(hasLike = favoriteCourses.contains(course.id))
                })
            }
            is CoursesResult.Cached -> {
                preResult.copy(courses = preResult.courses.map { course ->
                    course.copy(hasLike = favoriteCourses.contains(course.id))
                })
            }
            else -> preResult
        }
        return result
    }
}