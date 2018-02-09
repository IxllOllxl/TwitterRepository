package com.twitternurtelekom.db

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.twitternurtelekom.db.dao.TwitDao
import com.twitternurtelekom.model.Twit


@Database(entities = [Twit::class],
        version = 30, exportSchema = false)
abstract class TwitterDatabase : RoomDatabase() {
    companion object {
        private val DATABASE_NAME = "twitternurtelekom-db"

        private var sInstance: TwitterDatabase? = null

        @Synchronized
        fun getInstance(context: Context): TwitterDatabase {
            if (sInstance == null) {
                synchronized(TwitterDatabase::class.java) {
                    if (sInstance == null) {
                        sInstance = Room.databaseBuilder(context.applicationContext, TwitterDatabase::class.java, DATABASE_NAME)
                                .fallbackToDestructiveMigration()
                                .addCallback(object : RoomDatabase.Callback() {
                                    override fun onCreate(db: SupportSQLiteDatabase) {
                                    }
                                })
                                .build()
                    }
                }
            }
            return sInstance!!
        }
    }

    abstract fun twitDao(): TwitDao
}