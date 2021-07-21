package com.hafidh.cataloguemovie.utils

class Resource<T>(
    val status: Status,
    val data: T?
) {

    companion object {
        fun <T> success(data: T?): Resource<T> = Resource(Status.SUCCESS, data)
        fun <T> error(msg: String, data: T?): Resource<T> = Resource(Status.ERROR, data)
        fun <T> loading(data: T?): Resource<T> = Resource(Status.LOADING, data)
    }
}