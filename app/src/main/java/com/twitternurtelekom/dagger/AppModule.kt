package com.twitternurtelekom.dagger

import android.content.Context
import android.content.res.Resources
import com.twitternurtelekom.App
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
}