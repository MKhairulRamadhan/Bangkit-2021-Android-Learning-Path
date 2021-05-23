package com.mkhairulramadhan.core.valueObject

class ResourceData<T>(val statusData: StatusData, val data: T?, message: String?) {
    companion object{
        fun <T> success(data: T?): ResourceData<T> = ResourceData(StatusData.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T? = null): ResourceData<T> = ResourceData(StatusData.ERROR, data, msg)

        fun <T> loading(data: T? = null): ResourceData<T> = ResourceData(StatusData.LOADING, data, null)
    }
}