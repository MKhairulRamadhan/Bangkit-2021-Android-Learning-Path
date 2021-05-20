package com.mkhairulramadhan.submission1moviecatalog.valueObject

class ResourceData<T>(val statusData: StatusData, val data: T?, message: String?) {
    companion object{
        fun <T> success(data: T?): ResourceData<T> = ResourceData(StatusData.SUCCESS, data, null)

        fun <T> error(msg: String?, data: T?): ResourceData<T> = ResourceData(StatusData.ERROR, data, msg)

        fun <T> loading(data: T?): ResourceData<T> = ResourceData(StatusData.LOADING, data, null)
    }
}