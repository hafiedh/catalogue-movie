package com.hafidh.cataloguemovie.utils

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.hafidh.cataloguemovie.ui.repository.model.remote.ApiResponse
import com.hafidh.cataloguemovie.ui.repository.model.remote.StatusResponse
@Suppress("LeakingThis")
abstract class NetworkBoundResource<ResultType, RequestType>(private val executors: AppExecutors) {

    private val dataResult = MediatorLiveData<Resource<ResultType>>()

    private fun onFetchFailed() {}
    protected abstract fun loadDataFromDB(): LiveData<ResultType>
    protected abstract fun shouldFetch(data: ResultType): Boolean?
    protected abstract fun createCall(): LiveData<ApiResponse<RequestType>>?
    protected abstract fun saveCallResult(data: RequestType)


    init {
        dataResult.value = Resource.loading(null)
        val db = loadDataFromDB()
        dataResult.addSource(db) { data ->
            dataResult.removeSource(db)
            if (shouldFetch(data) == true) fetchFromNetwork(db)
            else dataResult.addSource(db) { newData ->
                dataResult.setValue(Resource.success(newData))
            }
        }
    }

    private fun fetchFromNetwork(dbSource: LiveData<ResultType>) {
        val apiResponse = createCall()

        dataResult.addSource(dbSource) { newData ->
            dataResult.setValue(Resource.loading(newData))
        }

            dataResult.addSource(apiResponse!!) { response ->
                dataResult.removeSource(apiResponse)
                dataResult.removeSource(dbSource)

                when (response.status) {
                    StatusResponse.SUCCESS -> executors.diskIO().execute {

                        response.body?.let { saveCallResult(it) }

                        executors.mainThread().execute {
                            dataResult.addSource(
                                loadDataFromDB()
                            ) { newData -> dataResult.setValue(Resource.success(newData)) }
                        }
                    }

                    StatusResponse.EMPTY -> executors.mainThread().execute {
                        dataResult.addSource(
                            loadDataFromDB()
                        ) { newData -> dataResult.setValue(Resource.success(newData)) }
                    }

                    StatusResponse.ERROR -> {
                        onFetchFailed()
                        dataResult.addSource(
                            dbSource
                        ) { newData ->
                            dataResult.setValue(response.message?.let {
                                Resource.error(
                                    it,
                                    newData
                                )
                            })
                        }
                    }
                }
            }
        }

        fun asLiveData(): LiveData<Resource<ResultType>> = dataResult

    }
