package tech.reface.refacelibrary

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import java.util.*

open class SecureGesture(
    @SerializedName("date") var date: Date,
    @SerializedName("exactitude") var exactitude: Float,
    @SerializedName("id") var id: Int,
    @SerializedName("unique_id") var uniqueId: String?) {

    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

    companion object {
        fun fromString(model: String): SecureGesture {
            val gson = Gson()
            return gson.fromJson(model, SecureGesture::class.java)
        }

        const val GESTURE_EYES_UP = 0
        const val GESTURE_EYES_DOWN = 1
        const val GESTURE_EYES_LEFT = 2
        const val GESTURE_EYES_RIGHT = 3
        const val GESTURE_EYES_BLINKING = 4
        const val GESTURE_EYES_STRONG_BLINKING = 5
        const val GESTURE_JAW_CLOSE = 6
        const val GESTURE_HEAD_LEFT = 7
        const val GESTURE_EYES_RIGHT_BLINKING = 8
    }
}