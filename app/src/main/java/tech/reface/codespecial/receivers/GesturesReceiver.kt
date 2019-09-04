package tech.reface.codespecial.receivers

import android.content.Intent
import android.content.BroadcastReceiver
import android.content.Context
import tech.reface.codespecial.app.MainApp
import tech.reface.refacelibrary.BroadcastUtils
import timber.log.Timber

class GesturesReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent) {
        Timber.i("Received gesture")

        val action = BroadcastUtils.reparseGestureIntent(intent)
        MainApp.actionSubject.onNext(action)
    }
}