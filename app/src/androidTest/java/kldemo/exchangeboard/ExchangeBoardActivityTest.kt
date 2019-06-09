package kldemo.exchangeboard

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.espresso.matcher.ViewMatchers.withSubstring
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import kldemo.exchangeboard.board.BoardActivity
import kldemo.exchangeboard.board.BoardPresenter
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.Before
import org.junit.Rule


@RunWith(AndroidJUnit4::class)
class ExchangeBoardActivityTest {
    @get:Rule
    var activityRule: ActivityTestRule<BoardActivity> = ActivityTestRule(BoardActivity::class.java)

    @Before
    fun waitFetchLatestCurrencies() {
        Thread.sleep(1000)
    }

    @Test
    fun verifyBoardCurrency1() {
        onView(withId(R.id.board_currency1)).check(matches(withSubstring(BoardPresenter.CURRENCY1)))
    }

    @Test
    fun verifyBoardCurrency2() {
        onView(withId(R.id.board_currency2)).check(matches(withSubstring(BoardPresenter.CURRENCY2)))
    }
}
