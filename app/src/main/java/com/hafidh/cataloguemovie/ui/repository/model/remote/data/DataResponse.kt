package com.hafidh.cataloguemovie.ui.repository.model.remote.data

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("results")
    val results: List<ItemData>
)
