package com.hafidh.cataloguemovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.hafidh.cataloguemovie.ui.Repository.model.MainRepository
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemData

class MovieViewModel(private val repository: MainRepository) : ViewModel() {
    fun getMovie(): LiveData<List<ItemData>> = repository.movie()
    fun getDetail(id: Int): LiveData<ItemData> = repository.movieDetail(id)
}