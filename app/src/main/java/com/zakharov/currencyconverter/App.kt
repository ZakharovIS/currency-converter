package com.zakharov.currencyconverter

import android.app.Application
import android.content.Context
import androidx.fragment.app.Fragment
import com.zakharov.currencyconverter.di.BaseComponent
import com.zakharov.currencyconverter.di.DaggerBaseComponent

class App: Application() {

    val baseComponent: BaseComponent by lazy {
        DaggerBaseComponent.factory().create(this)
    }

    companion object {
        @JvmStatic
        fun baseComponent(context: Context) =
            (context.applicationContext as App).baseComponent

    }
}

fun Fragment.baseComponent() = App.baseComponent(requireContext())
