package com.twitternurtelekom.service

import android.app.Service
import android.content.Intent
import android.os.Handler
import android.os.IBinder
import com.twitternurtelekom.repository.TwitRepository
import dagger.android.AndroidInjection
import javax.inject.Inject

/**
 * Получаем список последних 20 твитов каждые 60 секунд
 * и записываем их в базу данных
 */
class TapeService : Service() {

    @Inject lateinit var repo: TwitRepository

    private var handler = Handler()
    private var timer: Runnable? = createTimer()

    override fun onBind(intent: Intent?): IBinder? {
        return null
    }

    override fun onCreate() {
        AndroidInjection.inject(this)
        super.onCreate()
        handler.post(timer)
    }

    override fun onDestroy() {
        handler.removeCallbacks(timer)
    }

    private fun createTimer() = Runnable {
        repo.fetchTweetsFromServer()
        handler.postDelayed(timer, 60000)
    }
}