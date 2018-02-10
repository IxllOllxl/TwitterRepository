package com.twitternurtelekom.manager

import retrofit2.HttpException

class ErrorHandle {

    fun handle(e: Throwable) {
        if (e is HttpException) {
            if (e.response() != null && e.response().code() == 403) authorization()
        } else
            e.printStackTrace()
    }

    private fun authorization() {
        //TODO нужно авторизоваться снова, если не получится выйти
    }
}
