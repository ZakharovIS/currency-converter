package com.zakharov.currencyconverter.di

import android.content.Context
import com.zakharov.currencyconverter.App
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [BaseModule::class, NetworkModule::class]
)
interface BaseComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): BaseComponent
    }


    fun inject(app: App)
}