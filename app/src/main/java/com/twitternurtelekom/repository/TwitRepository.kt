package com.twitternurtelekom.repository

import com.twitternurtelekom.db.TwitterDatabase
import javax.inject.Inject

class TwitRepository @Inject constructor(private val room: TwitterDatabase) {

}