package test.task.effectivemobile.retrofit

import retrofit2.http.GET
import test.task.effectivemobile.remote.dto.CoursesDTO

interface CoursesRetrofitApiService {
    //@GET("https://drive.usercontent.google.com/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q")
    @GET("/u/0/uc?id=15arTK7XT2b7Yv4BJsmDctA4Hg-BbS8-q")
    suspend fun getCourses() : CoursesDTO
}