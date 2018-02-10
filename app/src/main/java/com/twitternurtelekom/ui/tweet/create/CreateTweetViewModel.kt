package com.twitternurtelekom.ui.tweet.create

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.twitternurtelekom.network.api.Status
import com.twitternurtelekom.repository.TwitRepository
import javax.inject.Inject

class CreateTweetViewModel @Inject constructor(var repo: TwitRepository) : ViewModel() {

    var eventUtil = MutableLiveData<Pair<String, Any>>()

    fun saveTweet(text: String) {
        repo.saveTweet(text, {
            when (it.status) {
                Status.SUCCESS -> {
                    eventUtil.value = Pair(tweet_created, it.data!!)
                }
                Status.ERROR -> {
                    if (it.data != null) {
                        eventUtil.value = Pair(tweet_failed, it.data)
                    } else eventUtil.value = Pair(tweet_system_failed, it.message)
                }
            }
        })
    }

    companion object {
        const val tweet_created = "tweet created"
        const val tweet_failed = "tweet failed"
        const val tweet_system_failed = "tweet failed"
    }
}