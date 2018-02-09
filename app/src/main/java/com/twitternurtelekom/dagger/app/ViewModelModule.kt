package com.twitternurtelekom.dagger.app

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.twitternurtelekom.ui.login.LoginViewModel
import com.twitternurtelekom.ui.splash.SplashViewModel
import com.twitternurtelekom.ui.twit.create.CreateTwitViewModel
import com.twitternurtelekom.ui.twit.tape.TwitsTapeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SplashViewModel::class)
    abstract fun bindSplashViewModel(viewModel: SplashViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(TwitsTapeViewModel::class)
    abstract fun bindTwitsTapeViewModel(viewModel: CreateTwitViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateTwitViewModel::class)
    abstract fun bindCreateTwitViewModel(viewModel: CreateTwitViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}