package com.hafidh.cataloguemovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hafidh.cataloguemovie.ui.Repository.model.MainRepository
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemData
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemDatatv

class TvshowViewModel(private val repository: MainRepository) : ViewModel() {
    fun getTv(): LiveData<List<ItemData>> = repository.tvShow()
    fun getDetail(id: Int): LiveData<ItemDatatv> = repository.tvShowDetail(id)
}