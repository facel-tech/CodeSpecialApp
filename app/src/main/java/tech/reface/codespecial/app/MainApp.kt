package tech.reface.codespecial.app

import android.app.Application
import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import io.reactivex.subjects.PublishSubject
import tech.reface.codespecial.receivers.GesturesReceiver
import tech.reface.codespecial.ui.activity.ActivityIntro
import tech.reface.codespecial.utils.SharedPreferencesUtils
import tech.reface.refacelibrary.BroadcastUtils
import tech.reface.refacelibrary.SecureGesture
import timber.log.Timber

class MainApp : Application() {

    override fun onCreate() {
        super.onCreate()
        appContext = this

        prefs = SharedPreferencesUtils(this)
        actionSubject = PublishSubject.create()

        registerGesturesReceiver()

        Timber.plant(Timber.DebugTree())
    }

    fun registerGesturesReceiver() {
        registerReceiver(GesturesReceiver(), IntentFilter(BroadcastUtils.BROADCAST_GESTURE))
    }

    companion object {
        lateinit var prefs: SharedPreferencesUtils
        lateinit var appContext: Context
        lateinit var actionSubject: PublishSubject<SecureGesture>

        fun openIntro() {
            val intent = Intent(appContext, ActivityIntro::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            appContext.startActivity(intent)
        }
    }
}