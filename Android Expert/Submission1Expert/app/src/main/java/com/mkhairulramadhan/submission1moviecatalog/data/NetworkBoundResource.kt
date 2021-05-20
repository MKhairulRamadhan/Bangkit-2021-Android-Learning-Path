package com.mkhairulramadhan.submission1moviecatalog.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.mkhairulramadhan.submission1moviecatalog.data.remote.ApiResponse
import com.mkhairulramadhan.submission1moviecatalog.data.remote.StatusResponse
import com.mkhairulramadhan.submission1moviecatalog.utils.ExecutorApp
import com.mkhairulramadhan.submission1moviecatalog.valueObject.ResourceData

abstract class NetworkBoundResource<TypeResult, TypeRequest>(private val executorApp: ExecutorApp) {

    private val result = MediatorLiveData<ResourceData<TypeResult>>()

    init{
        result.value = ResourceData.loading(null)

        @Suppress("LeakingThis")
        val sourceDb = fromDBLoad()

        result.addSource(sourceDb){ d ->
            result.removeSource(sourceDb)
            if(shouldFetch(d)){
                fetchFromNetwork(sourceDb)
            }else{
                result.addSource(sourceDb){
                    result.value = ResourceData.success(it)
                }
            }
        }
    }


    protected abstract fun shouldFetch(data: TypeResult?): Boolean

    protected abstract fun createCall(): LiveData<ApiResponse<TypeRequest>>

    protected open fun onFetchFailed() {}

    protected abstract fun fromDBLoad(): LiveData<TypeResult>

    protected abstract fun saveCallResult(data: TypeRequest)

    private fun fetchFromNetwork(dbSource: LiveData<TypeResult>) {

        val apiResponse = createCall()

        result.addSource(dbSource) { newData ->
            result.value = ResourceData.loading(newData)
        }
        result.addSource(apiResponse) { response ->
            result.removeSource(apiResponse)
            result.removeSource(dbSource)
            when (response.status) {
                StatusResponse.SUCCESS ->
                    executorApp.diskIO().execute {
                        saveCallResult(response.body)
                        executorApp.mainThread().execute {
                            result.addSource(fromDBLoad()) { newData ->
                                result.value = ResourceData.success(newData)
                            }
                        }
                    }
                StatusResponse.EMPTY -> executorApp.mainThread().execute {
                    result.addSource(fromDBLoad()) { newData ->
                        result.value = ResourceData.success(newData)
                    }
                }
                StatusResponse.ERROR -> {
                    onFetchFailed()
                    result.addSource(dbSource) { newData ->
                        result.value = ResourceData.error(response.message, newData)
                    }
                }
            }
        }
    }

    fun asLiveData(): LiveData<ResourceData<TypeResult>> = result
}