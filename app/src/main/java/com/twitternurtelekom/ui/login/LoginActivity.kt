package com.twitternurtelekom.ui.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.twitter.sdk.android.core.Callback
import com.twitter.sdk.android.core.Result
import com.twitter.sdk.android.core.TwitterException
import com.twitter.sdk.android.core.TwitterSession
import com.twitternurtelekom.R
import com.twitternurtelekom.ui.tweet.tape.TapeActivity
import com.twitternurtelekom.utils.setToken
import com.twitternurtelekom.utils.toast
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        login.setOnClickListener { twitter.performClick() }
        twitter.callback = callbackTwitterSession
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        twitter.onActivityResult(requestCode, resultCode, data)
    }

    private var callbackTwitterSession = object : Callback<TwitterSession>() {
        override fun success(result: Result<TwitterSession>) {
            setToken(result.data.authToken.token)
            TapeActivity.start(this@LoginActivity)
            finish()
        }

        override fun failure(exception: TwitterException) {
            toast(exception.localizedMessage)
        }
    }

    companion object {
        fun start(context: Context) {
            context.startActivity(Intent(context, LoginActivity::class.java)
                    .setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    .addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION))
        }
    }
}
