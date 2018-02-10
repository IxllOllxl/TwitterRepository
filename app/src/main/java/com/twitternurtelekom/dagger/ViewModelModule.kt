package com.twitternurtelekom.dagger

import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import com.twitternurtelekom.ui.tweet.create.CreateTweetViewModel
import com.twitternurtelekom.ui.tweet.tape.TapeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(TapeViewModel::class)
    abstract fun bindTwitsTapeViewModel(viewModel: TapeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CreateTweetViewModel::class)
    abstract fun bindCreateTwitViewModel(viewModel: CreateTweetViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}