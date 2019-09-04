package tech.reface.refacelibrary

import com.google.gson.Gson
import java.util.*

open class SecureData(var eeg1: Double?, var eeg2: Double?, var date: Date) {
    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

    companion object {
        fun fromString(model: String): SecureData {
            val gson = Gson()
            return gson.fromJson(model, SecureData::class.java)
        }
    }
}