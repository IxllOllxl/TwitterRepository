package com.twitternurtelekom.db.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.twitternurtelekom.model.Tweet

@Dao
interface TweetDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTweet(tweet: Tweet): Long

    @Query("SELECT * FROM tweets ORDER BY id DESC")
    fun fetchTweets(): DataSource.Factory<Int, Tweet>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(tweets: List<Tweet>)
}