package test.task.effectivemobile.impl.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import test.task.effectivemobile.impl.SettingsRepositoryImpl
import test.task.effectivemobile.settings.repositories.SettingsRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class SettingsHiltModule {
    @Provides
    @Singleton
    abstract fun bindSettingsRepository(impl: SettingsRepositoryImpl): SettingsRepository
}