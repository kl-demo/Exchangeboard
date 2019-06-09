package kldemo.usecases

import kldemo.entities.Currency

interface CurrencyDataSource {
    fun fetchLatestCurrencies(): List<Currency>
}