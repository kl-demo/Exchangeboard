package kldemo.exchangeboard.data

import kldemo.entities.Currency
import kldemo.usecases.CurrencyDataSource
import okhttp3.internal.Internal.instance
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class CurrencyNetworkDataSource : CurrencyDataSource {
    private val service: ExchangeratesapiService

    init {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.exchangeratesapi.io/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ExchangeratesapiService::class.java)
    }

    override fun fetchLatestCurrencies(): List<Currency> {
        var curencyList: MutableList<Currency> = mutableListOf()
        val result = service.getLatestRates().execute()
        if (result.isSuccessful) {
            val rates = result.body()?.rates
            if (rates != null) {
                for (rateItem in rates) {
                    curencyList.add(Currency(rateItem.key, rateItem.value.toBigDecimal()))
                }
            }
        }
        return curencyList
    }
}