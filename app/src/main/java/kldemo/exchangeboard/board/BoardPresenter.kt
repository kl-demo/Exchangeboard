package kldemo.exchangeboard.board

import android.app.Application
import android.os.Looper
import kldemo.ui.BoardContract
import kldemo.usecases.CurrencyDataSource
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit
import android.os.Handler
import android.util.Log
import kldemo.exchangeboard.util.NetworkUtils
import java.util.concurrent.ScheduledExecutorService
import javax.inject.Inject

class BoardPresenter @Inject constructor(currencyDataSource: CurrencyDataSource, context: Application) : BoardContract.Presenter {
    companion object {
        const val CURRENCY1: String = "USD"
        const val CURRENCY2: String = "PLN"
        const val FETCH_ERROR_TAG: String = "FETCH_LATEST_CURENCIES_ERROR"
    }
    private var view: BoardContract.View? = null
    private val currencyDataSource = currencyDataSource
    private val context = context
    private var scheduler: ScheduledExecutorService? = null
    private val uiHandler: Handler

    init {
        uiHandler = Handler(Looper.getMainLooper())
    }

    override fun start() {
        scheduler = Executors.newSingleThreadScheduledExecutor()
        scheduler?.scheduleAtFixedRate(Runnable {
            if (NetworkUtils.isConnected(context)) {
                try {
                    val latestCurrencies = currencyDataSource.fetchLatestCurrencies()
                    val currency1 = latestCurrencies.find { x -> x.name == CURRENCY1 }
                    val currency2 = latestCurrencies.find { x -> x.name == CURRENCY2 }
                    uiHandler.post(Runnable {
                        if (currency1 != null && currency2 != null) {
                            view?.showCurrencies(currency1, currency2)
                        }
                    })
                } catch (ex: Exception) {
                    Log.e(FETCH_ERROR_TAG, ex.message)
                }
            } else {
                uiHandler.post(Runnable {
                    view?.showNetworkCoonectionMError()
                })
            }
        }, 0, 1, TimeUnit.MINUTES)
    }

    override fun stop() {
        scheduler?.shutdown()
    }

    override fun takeView(view: BoardContract.View) {
        this.view = view
    }

    override fun dropView() {
        this.view = null
    }
}