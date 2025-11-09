package test.task.effectivemobile.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import test.task.effectivemobile.courses.repositories.CoursesRepository
import test.task.effectivemobile.impl.CoursesRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class CoursesRepositoryHiltModule {

    @Binds
    @Singleton
    abstract fun bindCoursesRepository(impl: CoursesRepositoryImpl) : CoursesRepository
}