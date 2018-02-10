package com.twitternurtelekom.dagger.app

import android.content.Context
import android.content.res.Resources
import com.twitter.sdk.android.core.TwitterCore
import com.twitter.sdk.android.core.TwitterSession
import com.twitternurtelekom.App
import com.twitternurtelekom.db.TwitterDatabase
import com.twitternurtelekom.manager.ErrorHandle
import com.twitternurtelekom.network.TwitterService
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AppModule {

    @Provides
    @Singleton
    internal fun provideContext(application: App): Context = application

    @Provides
    @Singleton
    internal fun provideResources(context: Context): Resources = context.resources

    @Provides
    @Singleton
    internal fun provideTwitterServise(session: TwitterSession): TwitterService {
        return com.twitternurtelekom.network.TwitterApi(session).build()
    }

    @Provides
    internal fun provideTwitterSession(): TwitterSession {
        return TwitterCore.getInstance().sessionManager.activeSession
    }

    @Singleton
    @Provides
    internal fun provideErrorHandler(): ErrorHandle {
        return ErrorHandle()
    }

    @Provides
    @Singleton
    internal fun provideRoomDb(application: App) = TwitterDatabase.getInstance(application)
}