package com.hafidh.cataloguemovie.ui.Repository.model.remote

import com.google.gson.annotations.SerializedName

data class ItemDatatv(
    @SerializedName("id")
    val id: Int,

    @SerializedName("poster_path")
    val poster: String?,

    @SerializedName("backdrop_path")
    val bigPoster: String?,

    @SerializedName("name")
    val name: String?,

    @SerializedName("first_air_date")
    val released: String?,

    @SerializedName("overview")
    val overview: String?,

    @SerializedName("vote_average")
    val rating: Double?
)
