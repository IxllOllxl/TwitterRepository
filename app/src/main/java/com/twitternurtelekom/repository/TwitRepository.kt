package com.twitternurtelekom.repository

import android.arch.lifecycle.LiveData
import android.arch.paging.LivePagedListBuilder
import android.arch.paging.PagedList
import com.twitternurtelekom.db.TwitterDatabase
import com.twitternurtelekom.manager.ErrorHandle
import com.twitternurtelekom.model.Tweet
import com.twitternurtelekom.network.TwitterService
import com.twitternurtelekom.network.api.Resource
import com.twitternurtelekom.network.api.Status
import kotlinx.coroutines.experimental.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber
import javax.inject.Inject


class TwitRepository @Inject constructor(private val room: TwitterDatabase,
                                         private val service: TwitterService,
                                         private val errorHandler: ErrorHandle) {

    private var isAllDataLoaded = false

    fun fetchTweets(): LiveData<PagedList<Tweet>> {
        return LivePagedListBuilder<Int, Tweet>(room.tweetDao().fetchTweets(), 30)
                .setBoundaryCallback(object : PagedList.BoundaryCallback<Tweet>() {
                    override fun onItemAtEndLoaded(item: Tweet) {
                        if (isAllDataLoaded) return
                        fetchTweetsFromServer(item.id)
                    }
                }).build()
    }

    fun fetchTweetsFromServer(lastTweetId: String? = null) {
        service.fetchTweets(lastTweetId = lastTweetId)
                .enqueue(object : Callback<List<Tweet>> {
                    override fun onResponse(call: Call<List<Tweet>>?, response: Response<List<Tweet>>?) {
                        Timber.tag(this.javaClass.simpleName).e("saveTweet: $response")
                        response?.body()?.let {
                            if (it.size < 20) isAllDataLoaded = true
                            launch {
                                room.tweetDao().insert(it)
                            }
                        }
                    }

                    override fun onFailure(call: Call<List<Tweet>>?, t: Throwable?) {
                        Timber.tag(this.javaClass.simpleName).e("onFailure: $t")
                        errorHandler.handle(t ?: Throwable())
                    }
                })
    }

    fun saveTweet(status: String, callback: (Resource<String>) -> Unit) {
        service.saveTweet(status).enqueue(object : Callback<Any> {
            override fun onResponse(call: Call<Any>?, response: Response<Any>?) {
                Timber.tag(this.javaClass.simpleName).e("saveTweet: $response")
                if (response?.isSuccessful == true) {
                    callback(Resource(status = Status.SUCCESS, data = response.message()))
                } else {
                    callback(Resource(status = Status.ERROR, data = response?.message()))
                }
            }

            override fun onFailure(call: Call<Any>?, t: Throwable?) {
                Timber.tag(this.javaClass.simpleName).e("onFailure: $t")
                errorHandler.handle(t ?: Throwable())
                callback(Resource(status = Status.ERROR, message = t!!.localizedMessage))
            }
        })
    }
}