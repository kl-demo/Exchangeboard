package kldemo.exchangeboard

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import kldemo.exchangeboard.di.DaggerAppComponent

class ExchangeBoardApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }
}