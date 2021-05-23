package com.mkhairulramadhan.core.data

import com.mkhairulramadhan.core.data.remote.ApiResponse
import com.mkhairulramadhan.core.utils.ExecutorApp
import com.mkhairulramadhan.core.valueObject.ResourceData
import kotlinx.coroutines.flow.*

abstract class NetworkBoundResource<TypeResult, TypeRequest>(private val executorApp: ExecutorApp) {

    private var result: Flow<ResourceData<TypeResult>> = flow{
        emit(ResourceData.loading())
        val sourceDb = fromDBLoad().first()
        if (shouldFetch(sourceDb)) {
            emit(ResourceData.loading())
            when (val apiResponse = createCall().first()) {
                is ApiResponse.Success -> {
                    saveCallResult(apiResponse.data)
                    emitAll(fromDBLoad().map { ResourceData.success(it) })
                }
                is ApiResponse.Empty -> {
                    emitAll(fromDBLoad().map { ResourceData.success(it) })
                }
                is ApiResponse.Error -> {
                    onFetchFailed()
                    emit(ResourceData.error<TypeResult>(apiResponse.errorMessage))
                }
            }
        } else {
            emitAll(fromDBLoad().map { ResourceData.success(it) })
        }
    }

    protected abstract fun shouldFetch(data: TypeResult?): Boolean

    protected abstract suspend fun createCall(): Flow<ApiResponse<TypeRequest>>

    protected open fun onFetchFailed() {}

    protected abstract fun fromDBLoad(): Flow<TypeResult>

    protected abstract suspend fun saveCallResult(data: TypeRequest)

    fun asFlow(): Flow<ResourceData<TypeResult>> = result
}