package com.guilhermereisdev.retrofitdemo.api

import com.guilhermereisdev.retrofitdemo.model.Albums
import retrofit2.Response
import retrofit2.http.GET

interface AlbumService {

    @GET("/albums")
    suspend fun getAlbums(): Response<Albums>

}
