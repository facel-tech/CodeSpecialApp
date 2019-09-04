package tech.reface.refacelibrary

import com.google.gson.Gson
import java.util.*

open class SecureDeviceEvent(var state: Int, var date: Date) {
    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

    companion object {
        const val STATE_CONNECTED = 0
        const val STATE_CONNECTING = 1
        const val STATE_DISCONNECTED = 2
        const val STATE_STRANGE = 3

        fun fromString(resource: String): SecureDeviceEvent {
            val gson = Gson()
            return gson.fromJson(resource, SecureDeviceEvent::class.java)
        }
    }
}