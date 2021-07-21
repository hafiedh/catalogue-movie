package com.hafidh.cataloguemovie.utils

import androidx.lifecycle.LiveData
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemData
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemDatatv

interface Source {
    fun movie(): LiveData<List<ItemData>>
    fun movieDetail(movieId: Int): LiveData<ItemData>
    fun tvShow(): LiveData<List<ItemData>>
    fun tvShowDetail(tvShowId: Int): LiveData<ItemDatatv>
}