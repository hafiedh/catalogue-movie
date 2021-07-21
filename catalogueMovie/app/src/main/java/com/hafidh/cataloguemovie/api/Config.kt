package com.hafidh.cataloguemovie.api

import com.hafidh.cataloguemovie.utils.Constans.BASE_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Config {
    fun create(): RetrofitClient {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
        return retrofit.create(RetrofitClient::class.java)
    }
}