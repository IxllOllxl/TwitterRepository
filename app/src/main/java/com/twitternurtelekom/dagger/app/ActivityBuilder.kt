package com.twitternurtelekom.dagger.app

import com.twitternurtelekom.dagger.Scope
import com.twitternurtelekom.ui.tweet.create.CreateTweetActivity
import com.twitternurtelekom.ui.tweet.tape.TapeActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @Scope
    @ContributesAndroidInjector()
    abstract fun bindTwitsTape(): TapeActivity

    @Scope
    @ContributesAndroidInjector()
    abstract fun bindCreateTwit(): CreateTweetActivity
}