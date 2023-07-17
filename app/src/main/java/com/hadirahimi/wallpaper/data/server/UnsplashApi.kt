package com.hadirahimi.wallpaper.data.server

import com.hadirahimi.wallpaper.data.model.UnsplashPhoto
import com.hadirahimi.wallpaper.utils.TOKEN
import retrofit2.http.GET
import retrofit2.http.Query

interface UnsplashApi {
    @GET("photos")
    suspend fun getPhotos(
        @Query("client_id") clientId: String = TOKEN,
        @Query("page") page: Int,
        @Query("per_page") perPage: Int = 5
    ): List<UnsplashPhoto>
}