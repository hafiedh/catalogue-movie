package com.hafidh.cataloguemovie.api

import com.hafidh.cataloguemovie.ui.Repository.model.remote.DataResponse
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemData
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemDatatv
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitClient {
    @GET("movie/popular")
    fun movies(@Query("api_key") apiKey: String): Call<DataResponse>

    @GET("movie/{movie_id}")
    fun movieDetails(
        @Path("movie_id") id: Int?,
        @Query("api_key") key: String?
    ): Call<ItemData>

    @GET("tv/popular")
    fun tvShows(@Query("api_key") key: String?): Call<DataResponse>


    @GET("tv/{tv_id}")
    fun tvShowDetails(
        @Path("tv_id") id: Int?,
        @Query("api_key") key: String?
    ): Call<ItemDatatv>
}