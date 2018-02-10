package com.twitternurtelekom.dagger.app

import com.twitternurtelekom.dagger.Scope
import com.twitternurtelekom.service.TapeService
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ServiceBuilder {

    @Scope
    @ContributesAndroidInjector()
    abstract fun bindTapeService(): TapeService
}