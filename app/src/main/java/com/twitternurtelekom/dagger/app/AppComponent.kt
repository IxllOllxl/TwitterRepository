package com.twitternurtelekom.dagger.app

import com.twitternurtelekom.App
import com.twitternurtelekom.dagger.ViewModelModule
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
AppModule::class,
ViewModelModule::class,
AndroidSupportInjectionModule::class,
ActivityBuilder::class,
ServiceBuilder::class])
interface AppComponent : AndroidInjector<App> {

    @Component.Builder
    abstract class Builder : AndroidInjector.Builder<App>()
}