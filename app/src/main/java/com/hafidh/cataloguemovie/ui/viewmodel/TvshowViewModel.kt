package com.hafidh.cataloguemovie.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import com.hafidh.cataloguemovie.ui.repository.model.MainRepository
import com.hafidh.cataloguemovie.ui.repository.model.local.data.TvEntity
import com.hafidh.cataloguemovie.utils.Resource

class TvshowViewModel(private val repository: MainRepository) : ViewModel() {
    fun getTv(): LiveData<List<TvEntity>> = repository.tvShow()
    fun getDetail(id: Int): LiveData<TvEntity> = repository.tvShowDetail(id)

    fun insert(data: List<TvEntity>) {
        repository.insertTv(data)
    }

    fun getTvShows(): LiveData<Resource<List<TvEntity>>> = repository.getTv()

    fun tvPaged(): LiveData<Resource<PagedList<TvEntity>>> = repository.getTvShowAsPaged()

    fun setFav() {
        getTvSwitch.value?.data?.let {
            val state = !it.isFavorite
            repository.setFavTv(it,state)
        }
    }

    val id = MutableLiveData<Int>()

    fun setId(tvId: Int) {
        this.id.value = tvId
    }

    val getTvSwitch: LiveData<Resource<TvEntity>> = Transformations.switchMap(id) {
        repository.getTvShow(it)
    }
}