package com.twitternurtelekom.ui.tweet.tape

import android.arch.lifecycle.ViewModel
import android.support.annotation.MainThread
import com.twitternurtelekom.repository.TwitRepository
import javax.inject.Inject

class TapeViewModel @Inject constructor(var repo: TwitRepository) : ViewModel() {

    fun updateTweets() {
        repo.fetchTweetsFromServer()
    }

    @MainThread
    fun fetchTweets() = repo.fetchTweets()
}