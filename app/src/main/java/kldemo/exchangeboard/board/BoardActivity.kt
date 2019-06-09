package kldemo.exchangeboard.board

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import dagger.android.support.DaggerAppCompatActivity
import kldemo.entities.Currency
import kldemo.exchangeboard.R
import kldemo.ui.BoardContract
import kotlinx.android.synthetic.main.activity_board.*
import javax.inject.Inject

class BoardActivity : DaggerAppCompatActivity(), BoardContract.View {
    @Inject
    lateinit var presenter: BoardContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_board)
    }

    override fun onResume() {
        super.onResume()
        presenter.takeView(this)
        presenter.start()
    }

    override fun onPause() {
        super.onPause()
        presenter.stop()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.dropView()
    }

    override fun showCurrencies(currency1: Currency, currency2: Currency) {
        board_currency1.text = formatCurrency(currency1)
        board_currency2.text = formatCurrency(currency2)
    }

    override fun showNetworkCoonectionMError() {
        val toast = Toast.makeText(this, R.string.device_is_offline, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    private fun formatCurrency(currency: Currency) = currency.name + ": " + currency.rate
}
