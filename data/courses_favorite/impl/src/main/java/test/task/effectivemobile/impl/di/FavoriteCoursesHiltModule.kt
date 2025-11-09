package test.task.effectivemobile.impl.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import test.task.effectivemobile.courses_favorite.repositories.repositories.FavoriteCoursesRepository
import test.task.effectivemobile.impl.FavoriteCoursesRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FavoriteCoursesHiltModule {
    @Binds
    @Singleton
    abstract fun bindFavoriteCoursesRepository(impl: FavoriteCoursesRepositoryImpl): FavoriteCoursesRepository
}