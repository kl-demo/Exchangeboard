package kldemo.exchangeboard.board

import dagger.Binds
import dagger.Module
import kldemo.exchangeboard.di.ActivityScoped
import kldemo.ui.BoardContract

@Module
abstract class BoardModule {
    @ActivityScoped
    @Binds
    abstract fun tasksPresenter(presenter:BoardPresenter): BoardContract.Presenter
}