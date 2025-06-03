package com.maestrocorona.appferia.api

import retrofit2.http.GET

interface CatApi {
    @GET("images/search?limit=20")
    suspend fun getCatImages(): List<CatImage>
}

