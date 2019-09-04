package tech.reface.codespecial.ui.activity

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import kotlinx.android.synthetic.main.activity_main.*
import tech.reface.codespecial.R
import tech.reface.codespecial.presentation.presenter.activity.PresenterActivityMain
import tech.reface.codespecial.presentation.view.activity.ViewActivityMain
import timber.log.Timber

class ActivityMain : MvpAppCompatActivity(), ViewActivityMain {
    @InjectPresenter
    internal lateinit var presenterActivityMain: PresenterActivityMain

    companion object {
        const val STARTING_URL = "https://thecode.media/andri-oxa/"
        const val SCROLLING_DISTANCE = 100
        var CURRENT_POSITION = 0
        const val ACTION_OPEN_INTRO = 19
        const val ACTION_OPEN_INTRO_SUCCESS = 20
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        webView.settings?.javaScriptEnabled = true
        webView.loadUrl(STARTING_URL)
    }

    override fun openIntro() {
        val intent = Intent(this, ActivityIntro::class.java)
        startActivityForResult(intent, ACTION_OPEN_INTRO)
    }

    override fun scrollUp() {
        Timber.i("Scrolling up")

        CURRENT_POSITION -= SCROLLING_DISTANCE
        reparseCoordinate()
        goToCoordinate()
    }

    override fun scrollDown() {
        Timber.i("Scrolling down")

        CURRENT_POSITION += SCROLLING_DISTANCE
        reparseCoordinate()
        goToCoordinate()
    }

    private fun reparseCoordinate() {
        if (CURRENT_POSITION < 0) {
            CURRENT_POSITION = 0
        }
    }

    private fun goToCoordinate() {
        webView.scrollTo(0, CURRENT_POSITION)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenterActivityMain.disposed()
    }
}