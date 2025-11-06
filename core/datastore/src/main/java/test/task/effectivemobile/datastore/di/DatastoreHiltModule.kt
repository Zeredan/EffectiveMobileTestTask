package test.task.effectivemobile.datastore.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

val Context.effectiveMobileDataStore : DataStore<Preferences> by preferencesDataStore("EffectiveMobileDataStore")

@Module
@InstallIn(SingletonComponent::class)
class DatastoreHiltModule {
    @Provides
    @Singleton
    fun provideDatastoreModule(@ApplicationContext context: Context) : DataStore<Preferences> {
        return context.effectiveMobileDataStore
    }
}