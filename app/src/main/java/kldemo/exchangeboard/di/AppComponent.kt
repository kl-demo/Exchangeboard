package kldemo.exchangeboard.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import kldemo.exchangeboard.ExchangeBoardApplication
import kldemo.exchangeboard.data.CurrencyDataSourceModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    ApplicationModule::class,
    CurrencyDataSourceModule::class,
    ActivityBindingModule::class,
    AndroidSupportInjectionModule::class])
interface AppComponent : AndroidInjector<ExchangeBoardApplication> {
    // Gives us syntactic sugar. we can then do DaggerAppComponent.builder().application(this).build().inject(this);
    // never having to instantiate any modules or say which module we are passing the application to.
    // Application will just be provided into our app graph now.
    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: Application): AppComponent.Builder

        fun build(): AppComponent
    }
}