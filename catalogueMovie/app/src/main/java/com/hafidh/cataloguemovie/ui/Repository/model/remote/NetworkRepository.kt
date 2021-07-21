package com.hafidh.cataloguemovie.ui.Repository.model.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import com.hafidh.cataloguemovie.BuildConfig
import com.hafidh.cataloguemovie.api.Config
import com.hafidh.cataloguemovie.utils.EspressoIdlingResource
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class NetworkRepository(private val networkConfig: Config) {
    private val key = BuildConfig.MOVIEDB_KEY
    private var handler = Handler(Looper.getMainLooper())

    companion object{
        private var INSTANCE: NetworkRepository? = null
        private val TAG = NetworkRepository::class.java.toString()
        private const val TIME: Long = 2000

        fun getInstance(networkConfig: Config):NetworkRepository{
            if (INSTANCE == null)
                INSTANCE = NetworkRepository(networkConfig)
            return INSTANCE!!
        }
    }

    interface MovieCallback{
        fun movieResponseCallback(movieResponse: List<ItemData>)
    }

    interface MovieDetailCallback{
        fun movieDetailResponseCallback(movieDetailResponse: ItemData)
    }

    interface TvShowCallback{
        fun tvResponseCallback(tvShowResponse: List<ItemData>)
    }

    interface TvShowDetailCallback{
        fun tvDetailResponseCallback(tvShowDetailResponse: ItemDatatv)
    }

    fun getMovieDb(callback: MovieCallback) {
        EspressoIdlingResource.increment()
        handler.postDelayed({
            networkConfig.create().movies(key).enqueue(object: Callback<DataResponse> {
                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Log.d(TAG, t.printStackTrace().toString())
                }

                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    response.body()?.results?.let { callback.movieResponseCallback(it) }
                    EspressoIdlingResource.decrement()
                }

            })
        }, TIME)
    }

    fun getMovieDetailDb(id: Int, callback: MovieDetailCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            networkConfig.create().movieDetails(id, key).enqueue(object: Callback<ItemData>{
                override fun onFailure(call: Call<ItemData>, t: Throwable) {
                    Log.d(TAG, t.printStackTrace().toString())
                }

                override fun onResponse(call: Call<ItemData>, response: Response<ItemData>) {
                    response.body()?.let { callback.movieDetailResponseCallback(it) }
                    EspressoIdlingResource.decrement()
                }

            })
        }, TIME)
    }

    fun getTvShowDb(callback: TvShowCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            networkConfig.create().tvShows(key).enqueue(object: Callback<DataResponse>{
                override fun onFailure(call: Call<DataResponse>, t: Throwable) {
                    Log.d(TAG, t.printStackTrace().toString())
                }

                override fun onResponse(
                    call: Call<DataResponse>,
                    response: Response<DataResponse>
                ) {
                    response.body()?.results?.let { callback.tvResponseCallback(it) }
                    EspressoIdlingResource.decrement()
                }

            })
        }, TIME)
    }

    fun getTvShowDetailDb(id: Int, callback: TvShowDetailCallback){
        EspressoIdlingResource.increment()
        handler.postDelayed({
            networkConfig.create().tvShowDetails(id, key).enqueue(object: Callback<ItemDatatv>{
                override fun onFailure(call: Call<ItemDatatv>, t: Throwable) {
                    Log.d(TAG, t.printStackTrace().toString())
                }

                override fun onResponse(
                    call: Call<ItemDatatv>,
                    response: Response<ItemDatatv>
                ) {
                    response.body()?.let { callback.tvDetailResponseCallback(it)}
                    EspressoIdlingResource.decrement()
                }

            })
        }, TIME)
    }
}