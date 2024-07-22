package com.zakharov.currencyconverter.di

import android.app.Application
import com.zakharov.currencyconverter.App
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class BaseModule {

    @Binds
    @Singleton
    abstract fun bindApplication(application: App): Application
}