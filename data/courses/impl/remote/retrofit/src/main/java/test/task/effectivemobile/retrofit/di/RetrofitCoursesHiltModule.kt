package test.task.effectivemobile.retrofit.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import test.task.effectivemobile.retrofit.CoursesRetrofitApiService
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RetrofitCoursesHiltModule {
    @Provides
    @Singleton
    fun provideApiService(retrofit: Retrofit) : CoursesRetrofitApiService {
        return retrofit.create(CoursesRetrofitApiService::class.java)
    }
}