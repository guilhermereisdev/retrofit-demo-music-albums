package com.guilhermereisdev.retrofitdemo.api

import com.guilhermereisdev.retrofitdemo.model.Albums
import com.guilhermereisdev.retrofitdemo.model.AlbumsItem
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AlbumService {
    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

    @GET("/albums")
    suspend fun getAlbumsFromUserID(@Query("userId") userId: Int): Response<Albums>

    @GET("/albums/{id}")
    suspend fun getSingleAlbum(@Path(value = "id") albumId: Int): Response<AlbumsItem>

}
