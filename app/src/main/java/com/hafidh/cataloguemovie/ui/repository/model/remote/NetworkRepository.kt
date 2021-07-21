package com.hafidh.cataloguemovie.ui.repository.model.remote

import android.util.Log
import com.hafidh.cataloguemovie.BuildConfig
import com.hafidh.cataloguemovie.api.Config
import com.hafidh.cataloguemovie.ui.repository.model.local.data.MovieEntity
import com.hafidh.cataloguemovie.ui.repository.model.local.data.TvEntity
import com.hafidh.cataloguemovie.ui.repository.model.remote.data.DataResponse
import com.hafidh.cataloguemovie.ui.repository.model.remote.data.ItemData
import com.hafidh.cataloguemovie.ui.repository.model.remote.data.ItemDatatv
import com.hafidh.cataloguemovie.utils.DataHelper
import com.hafidh.cataloguemovie.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepository(private val networkConfig: Config) {
    private val key = BuildConfig.MOVIEDB_KEY
    companion object{
        private var INSTANCE: NetworkRepository? = null
        private val TAG = NetworkRepository::class.java.toString()

        fun getInstance(networkConfig: Config):NetworkRepository{
            if (INSTANCE == null)
                INSTANCE = NetworkRepository(networkConfig)
            return INSTANCE!!
        }
    }

    interface MovieCallback{
        fun movieResponseCallback(movieResponse: List<MovieEntity>)
    }

    interface MovieDetailCallback{
        fun movieDetailResponseCallback(movieDetailResponse: MovieEntity)
    }

    interface TvShowCallback{
        fun tvResponseCallback(tvShowResponse: List<TvEntity>)
    }

    interface TvShowDetailCallback{
        fun tvDetailResponseCallback(tvShowDetailResponse: TvEntity)
    }

    fun getMovieDb(callback: MovieCallback) {
        EspressoIdlingResource.increment()
            networkConfig.create().movies(key).enqueue(object: Callback<DataResponse> {
                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Log.d(TAG, t.printStackTrace().toString())
                    EspressoIdlingResource.decrement()
                }

                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    response.body()?.results?.let { callback.movieResponseCallback(DataHelper.mappingToResponse(it)) }
                    EspressoIdlingResource.decrement()
                }

            })
    }

    fun getMovieDetailDb(id: Int, callback: MovieDetailCallback){
        EspressoIdlingResource.increment()
            networkConfig.create().movieDetails(id, key).enqueue(object: Callback<ItemData>{
                override fun onFailure(call: Call<ItemData>, t: Throwable) {
                    Log.d(TAG, t.printStackTrace().toString())
                    EspressoIdlingResource.decrement()
                }

                override fun onResponse(call: Call<ItemData>, response: Response<ItemData>) {
                    response.body()?.let { callback.movieDetailResponseCallback(DataHelper.mappingDetail(it)) }
                    EspressoIdlingResource.decrement()
                }

            })
    }

    fun getTvShowDb(callback:TvShowCallback){
        EspressoIdlingResource.increment()
            networkConfig.create().tvShows(key).enqueue(object: Callback<DataResponse>{
                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Log.d(TAG, t.printStackTrace().toString())
                    EspressoIdlingResource.decrement()
                }

                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    response.body()?.results?.let { callback.tvResponseCallback(DataHelper.mappingTv(it)) }
                    EspressoIdlingResource.decrement()
                }

            })
    }

    fun getTvShowDetailDb(id: Int, callback: TvShowDetailCallback){
        EspressoIdlingResource.increment()
            networkConfig.create().tvShowDetails(id, key).enqueue(object: Callback<ItemDatatv>{
                override fun onFailure(call: Call<ItemDatatv>, t: Throwable) {
                    Log.d(TAG, t.printStackTrace().toString())
                    EspressoIdlingResource.decrement()
                }

                override fun onResponse(
                    call: Call<ItemDatatv>,
                    response: Response<ItemDatatv>
                ) {
                    response.body()?.let { callback.tvDetailResponseCallback(DataHelper.mappingDetail(it))}
                    EspressoIdlingResource.decrement()
                }

            })
    }
}