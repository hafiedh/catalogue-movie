package com.hafidh.cataloguemovie.ui.Repository.model.remote

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("results")
    val results: List<ItemData>
)
