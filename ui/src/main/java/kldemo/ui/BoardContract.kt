package kldemo.ui

import kldemo.entities.Currency

interface BoardContract {
    interface Presenter : BasePresenter<View> {
        fun start()

        fun stop()
    }

    interface View {
        fun showCurrencies(currency1: Currency, currency2: Currency)

        fun showNetworkCoonectionMError()
    }
}