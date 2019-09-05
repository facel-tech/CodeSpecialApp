package tech.reface.codespecial.presentation.presenter.activity

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import tech.reface.codespecial.app.MainApp
import tech.reface.codespecial.presentation.view.activity.ViewActivityMain
import tech.reface.refacelibrary.SecureGesture
import timber.log.Timber

@InjectViewState
class PresenterActivityMain: MvpPresenter<ViewActivityMain>() {
    private val complexDisposable = CompositeDisposable()

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        checkIfRequestedPermissions()

        val disposable = MainApp.actionSubject
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                when(it.id) {
                    SecureGesture.GESTURE_EYES_BLINKING -> viewState.scrollDown()
                }
            }, { Timber.e(it)})

        complexDisposable.add(disposable)
    }

    private fun checkIfRequestedPermissions() {
        if (!MainApp.prefs.checkIfIntroCompleted()) {
            viewState.openIntro()
        }
    }

    fun disposed() {
        complexDisposable.dispose()
    }
}