package tech.reface.refacelibrary

import com.google.gson.Gson
import com.google.gson.annotations.SerializedName

open class SecureAction(
    @SerializedName("package_id") var packageId: String,
    var action: String,
    var title: String) {

    override fun toString(): String {
        val gson = Gson()
        return gson.toJson(this)
    }

    companion object {
        fun fromString(json: String): SecureAction {
            val gson = Gson()
            return gson.fromJson(json, SecureAction::class.java)
        }
    }
}