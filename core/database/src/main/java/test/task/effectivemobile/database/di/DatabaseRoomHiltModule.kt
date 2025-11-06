package test.task.effectivemobile.database.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import test.task.effectivemobile.database.EffectiveMobileDatabase
import test.task.effectivemobile.database.dao.CoursesDAO
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseRoomHiltModule {

    @Provides
    @Singleton
    fun provideCharactersDatabase(
        @ApplicationContext appContext: Context
    ) : EffectiveMobileDatabase {
        return Room.databaseBuilder(
            appContext,
            EffectiveMobileDatabase::class.java,
            "characters_database"
        ).build()
    }

    @Provides
    @Singleton
    fun provideCoursesDAO(
        database: EffectiveMobileDatabase
    ) : CoursesDAO {
        return database.coursesDao
    }
}