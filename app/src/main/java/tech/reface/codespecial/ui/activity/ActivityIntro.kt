package tech.reface.codespecial.ui.activity

import android.content.Intent
import android.os.Bundle
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.google.android.material.appbar.AppBarLayout
import kotlinx.android.synthetic.main.activity_intro.*
import onactivityresult.ActivityResult
import onactivityresult.OnActivityResult
import tech.reface.codespecial.R
import tech.reface.codespecial.app.Config
import tech.reface.codespecial.presentation.presenter.activity.PresenterActivityIntro
import tech.reface.codespecial.presentation.view.activity.ViewActivityIntro
import tech.reface.refacelibrary.PermissionsFetcher
import timber.log.Timber
import kotlin.math.abs

class ActivityIntro: MvpAppCompatActivity(), ViewActivityIntro {

    @InjectPresenter
    internal lateinit var presenterActivityIntro: PresenterActivityIntro

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_intro)

        textViewTitle.typeface = Config.MEDIUM_FONT
        textViewCollapsedTitle.typeface = Config.MEDIUM_FONT
        textViewDescription.typeface = Config.REGULAR_FONT

        appBarLayout.addOnOffsetChangedListener(AppBarLayout.OnOffsetChangedListener { appBarLayout, verticalOffset ->
            val totalRange = appBarLayout.totalScrollRange.toFloat()
            val percentage = abs(verticalOffset / totalRange)

            textViewDescription.alpha = 1 - percentage
            textViewTitle.alpha = 1 - percentage
            textViewCollapsedTitle.alpha = percentage
        })
    }

    override fun setTitle(title: String) {
        textViewCollapsedTitle.text = title
        textViewTitle.text = title
    }

    override fun setDescription(description: String) {
        textViewDescription.text = description
    }

    override fun setRightsButton(button: String) {
        textViewButton.text = button
        textViewButton.setOnClickListener {
            val permissions = listOf(
                PermissionsFetcher.GESTURE_DATA_TYPE
            )

            val intent = PermissionsFetcher.getPermissionsIntent(permissions)
            startActivityForResult(intent, FETCH_REFACE_PERMISSIONS)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        ActivityResult.onResult(requestCode, resultCode, data).into(this)
    }

    @OnActivityResult(requestCode = FETCH_REFACE_PERMISSIONS, resultCodes = [PermissionsFetcher.ACTIVITY_RESULT_GRANTED])
    fun fetchedPermissions() {
        Timber.i("Permissions granted")
        presenterActivityIntro.fetchedPermissions()
    }

    @OnActivityResult(requestCode = FETCH_REFACE_PERMISSIONS, resultCodes = [PermissionsFetcher.ACTIVITY_RESULT_FAILED])
    fun failedGettingPermissions() {
        Timber.i("Permissions were not obtained")
        presenterActivityIntro.failedPermissions()
    }

    override fun killActivity() {
        val resultIntent = Intent()
        setResult(ActivityMain.ACTION_OPEN_INTRO_SUCCESS, resultIntent)
        finish()
    }

    companion object {
        const val FETCH_REFACE_PERMISSIONS = 10
    }
}