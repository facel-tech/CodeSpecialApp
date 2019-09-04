package tech.reface.codespecial.presentation.presenter.activity

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import tech.reface.codespecial.app.MainApp
import tech.reface.codespecial.presentation.view.activity.ViewActivityIntro
import timber.log.Timber

@InjectViewState
class PresenterActivityIntro : MvpPresenter<ViewActivityIntro>() {

    override fun onFirstViewAttach() {
        super.onFirstViewAttach()

        viewState.setTitle("Забота о здоровье")
        viewState.setDescription("Чтобы продолжить, разрешите права доступа к данным Reface")
        viewState.setRightsButton("Получить права")

        Timber.d("Did everything in onFirstViewAttach")
    }

    fun fetchedPermissions() {
        val disposable = MainApp.prefs.completedIntro().subscribe {
            viewState.killActivity()
        }
    }

    fun failedPermissions() {
        viewState.setDescription("Пожалуйста, предоставьте права к данным Reface")
    }
}