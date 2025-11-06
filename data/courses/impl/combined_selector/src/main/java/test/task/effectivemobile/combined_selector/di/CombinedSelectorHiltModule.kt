package test.task.effectivemobile.combined_selector.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import test.task.effectivemobile.combined_selector.CoursesRepositoryCombinedSelectorImpl
import test.task.effectivemobile.courses.repositories.CoursesRepository
import javax.inject.Singleton

//@Module
//@InstallIn(SingletonComponent::class)
//abstract class CombinedSelectorHiltModule {
//
//    @Binds
//    @Singleton
//    abstract fun bindCoursesRepository(impl: CoursesRepositoryCombinedSelectorImpl) : CoursesRepository
//}