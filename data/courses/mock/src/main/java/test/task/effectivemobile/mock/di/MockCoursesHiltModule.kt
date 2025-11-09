package test.task.effectivemobile.mock.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import test.task.effectivemobile.courses.repositories.CoursesRepository
import test.task.effectivemobile.mock.CoursesRepositoryMock
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class MockCoursesHiltModule {

    @Binds
    @Singleton
    abstract fun bindCoursesRepository(impl: CoursesRepositoryMock) : CoursesRepository
}