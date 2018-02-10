package com.twitternurtelekom.network.api

class Resource<out T>(
        var status: Status = Status.DEFAULT,
        val data: T? = null,
        var message: String = "")

