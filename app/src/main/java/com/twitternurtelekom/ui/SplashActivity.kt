package com.twitternurtelekom.ui

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.twitternurtelekom.ui.login.LoginActivity
import com.twitternurtelekom.ui.tweet.tape.TapeActivity
import com.twitternurtelekom.utils.getToken

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (getToken().isEmpty())
            LoginActivity.start(this)
        else {
            TapeActivity.start(this)
        }
    }
}