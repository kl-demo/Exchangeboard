package kldemo.exchangeboard.di

import dagger.Module
import dagger.android.ContributesAndroidInjector
import kldemo.exchangeboard.board.BoardActivity
import kldemo.exchangeboard.board.BoardModule

@Module
abstract class ActivityBindingModule {
    @ActivityScoped
    @ContributesAndroidInjector(modules = [BoardModule::class])
    abstract fun boardActivity(): BoardActivity
}