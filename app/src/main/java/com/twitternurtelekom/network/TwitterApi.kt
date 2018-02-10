package com.twitternurtelekom.network

import com.twitter.sdk.android.core.TwitterApiClient
import com.twitter.sdk.android.core.TwitterSession
import com.twitternurtelekom.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class TwitterApi(session: TwitterSession) : TwitterApiClient(session) {

    fun build(): TwitterService {
        return getService(TwitterService::class.java)
    }
}
