package com.twitternurtelekom.repository

import com.twitternurtelekom.db.TwitterDatabase
import javax.inject.Inject

class AuthenticationRepository @Inject constructor(private val room: TwitterDatabase) {

}