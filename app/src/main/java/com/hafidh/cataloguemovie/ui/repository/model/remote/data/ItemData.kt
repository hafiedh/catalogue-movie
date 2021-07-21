package com.hafidh.cataloguemovie.ui.repository.model.remote.data

import com.google.gson.annotations.SerializedName

data class ItemData(
    @SerializedName("id")
    val id: Int,

    @SerializedName("poster_path")
    val poster: String,

    @SerializedName("backdrop_path")
    val bigPoster: String,

    @SerializedName("title")
    val title: String,

    @SerializedName("overview")
    val overview: String,

    @SerializedName("release_date")
    val released: String,

    @SerializedName("vote_average")
    val rating: Double,

   //For tvshow
    @SerializedName("name")
    val name: String
)