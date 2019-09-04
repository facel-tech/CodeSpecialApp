package tech.reface.refacelibrary

import android.content.Context
import android.content.Intent
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import android.content.ComponentName


class BroadcastUtils {
    companion object {
        const val EXTRA_DATA = "EXTRA_DATA"
        const val EXTRA_GESTRE = "EXTRA_GESTURE"
        const val EXTRA_ACTION = "EXTRA_ACTION"
        const val EXTRA_DEVICE_EVENT = "EXTRA_ACTION"
        private const val EXTRA_ACTIONS_INIT = "EXTRA_ACTIONS_INIT"

        const val BROADCAST_DATA = "BROADCAST_DATA"
        const val BROADCAST_ACTION = "BROADCAST_ACTION"
        const val BROADCAST_GESTURE = "BROADCAST_GESTURE"
        const val BROADCAST_ACTIONS_INIT = "BROADCAST_ACTIONS_INIT"
        const val BROADCAST_RIGHTS_DENIAL = "BROADCAST_RIGHTS_DENIAL"
        const val BROADCAST_DEVICE_EVENT = "BROADCAST_DEVICE_EVENT"
        const val BROADCAST_REQUEST_DEVICE_STATE = "BROADCAST_REQUEST_DEVICE_STATE"

        fun reparseGestureIntent(intent: Intent): SecureGesture {
            val data = intent.getStringExtra(EXTRA_GESTRE)
            return SecureGesture.fromString(data)
        }

        fun reparseDataIntent(intent: Intent): SecureData {
            val data = intent.getStringExtra(EXTRA_DATA)
            return SecureData.fromString(data)
        }

        fun reparseActionIntent(intent: Intent): SecureAction {
            val data = intent.getStringExtra(EXTRA_ACTION)
            return SecureAction.fromString(data)
        }

        fun reparseDeviceEventIntent(intent: Intent): SecureDeviceEvent {
            val data = intent.getStringExtra(EXTRA_DEVICE_EVENT)
            return SecureDeviceEvent.fromString(data)
        }

        fun sendSecureActions(array: ArrayList<SecureAction>, context: Context) {
            val gson = Gson()
            val data = gson.toJson(array)

            val intent = Intent()
            intent.putExtra(EXTRA_ACTIONS_INIT, data)
            intent.addFlags(Intent.FLAG_INCLUDE_STOPPED_PACKAGES)

            intent.action = BROADCAST_ACTIONS_INIT
            intent.component = ComponentName(
                PermissionsFetcher.REFACE_APP,
                "${PermissionsFetcher.REFACE_APP}.receivers.ActionsReceiver"
            )

            context.sendBroadcast(intent)
        }

        fun sendBroadcaastRequestDeviceState(context: Context) {
            val intent = Intent(BROADCAST_REQUEST_DEVICE_STATE)
            context.sendBroadcast(intent)
        }

        fun getSecureActions(intent: Intent): ArrayList<SecureAction> {
            val gson = Gson()
            val data = intent.getStringExtra(EXTRA_ACTIONS_INIT)
            val listType = object : TypeToken<ArrayList<SecureAction>>() { }.type

            return gson.fromJson(data, listType)
        }
    }
}