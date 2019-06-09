package kldemo.exchangeboard.data

import dagger.Module
import dagger.Provides
import kldemo.usecases.CurrencyDataSource
import javax.inject.Singleton

@Module
class CurrencyDataSourceModule {
    @Singleton
    @Provides
    fun currencyDataSource(): CurrencyDataSource = CurrencyNetworkDataSource()
}