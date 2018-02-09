package com.twitternurtelekom.dagger

import com.twitternurtelekom.dagger.app.PerActivity
import com.twitternurtelekom.ui.login.LoginActivity
import com.twitternurtelekom.ui.twit.create.CreateTwitActivity
import com.twitternurtelekom.ui.splash.SplashActivity
import com.twitternurtelekom.ui.twit.tape.TwitsTapeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @PerActivity
    @ContributesAndroidInjector()
    abstract fun bindSplashScreen(): SplashActivity

    @PerActivity
    @ContributesAndroidInjector()
    abstract fun bindLogin(): LoginActivity

    @PerActivity
    @ContributesAndroidInjector()
    abstract fun bindTwitsTape(): TwitsTapeActivity

    @PerActivity
    @ContributesAndroidInjector()
    abstract fun bindCreateTwit(): CreateTwitActivity
}