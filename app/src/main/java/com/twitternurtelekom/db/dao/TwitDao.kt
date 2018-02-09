package com.twitternurtelekom.db.dao

import android.arch.paging.DataSource
import android.arch.persistence.room.Dao
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy
import android.arch.persistence.room.Query
import com.twitternurtelekom.model.Twit

@Dao
interface TwitDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addTwit(twit: Twit): Long

    @Query("SELECT * FROM twits")
    fun fetchTwits(): DataSource.Factory<Int, Twit>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(twits: List<Twit>)
}