package com.hafidh.cataloguemovie.ui.Repository.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemData
import com.hafidh.cataloguemovie.ui.Repository.model.remote.ItemDatatv
import com.hafidh.cataloguemovie.ui.Repository.model.remote.NetworkRepository
import com.hafidh.cataloguemovie.utils.Source

class MainRepository(private val remote: NetworkRepository) : Source {
    companion object {
        @Volatile
        private var INSTANCE: MainRepository? = null

        fun getInstance(
            remoteNetworkRepository: NetworkRepository
        ): MainRepository? {
            if (INSTANCE == null) {
                synchronized(MainRepository::class.java) {
                    if (INSTANCE == null)
                        INSTANCE = MainRepository(remoteNetworkRepository)
                }
            }
            return INSTANCE
        }
    }

    override fun movie(): LiveData<List<ItemData>> {
        val movie = MutableLiveData<List<ItemData>>()
        remote.getMovieDb(object : NetworkRepository.MovieCallback {
            override fun movieResponseCallback(movieResponse: List<ItemData>) {
                movie.postValue(movieResponse)
            }

        })
        return movie
    }

    override fun movieDetail(id: Int): LiveData<ItemData> {
        val movieDetail = MutableLiveData<ItemData>()
        remote.getMovieDetailDb(id, object : NetworkRepository.MovieDetailCallback {
            override fun movieDetailResponseCallback(movieDetailResponse: ItemData) {
                movieDetail.postValue(movieDetailResponse)
            }

        })
        return movieDetail
    }

    override fun tvShow(): LiveData<List<ItemData>> {
        val tv = MutableLiveData<List<ItemData>>()
        remote.getTvShowDb(object : NetworkRepository.TvShowCallback {
            override fun tvResponseCallback(tvShowResponse: List<ItemData>) {
                tv.postValue(tvShowResponse)
            }

        })
        return tv
    }

    override fun tvShowDetail(id: Int): LiveData<ItemDatatv> {
        val tvDetail = MutableLiveData<ItemDatatv>()
        remote.getTvShowDetailDb(id, object : NetworkRepository.TvShowDetailCallback {
            override fun tvDetailResponseCallback(tvShowDetailResponse: ItemDatatv) {
                tvDetail.postValue(tvShowDetailResponse)
            }

        })
        return tvDetail
    }
}
