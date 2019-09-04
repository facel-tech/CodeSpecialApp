package tech.reface.codespecial.utils

import android.content.Context
import android.content.SharedPreferences
import io.reactivex.Completable
import io.reactivex.schedulers.Schedulers

class SharedPreferencesUtils(context: Context) {
    private var sharedPreferences: SharedPreferences

    init {
        sharedPreferences = context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)
    }

    fun save(name: String, value: Any): Completable {
        return Completable.fromCallable {
            val e = sharedPreferences.edit()

            when (value) {
                is Boolean -> e.putBoolean(name, value)
                is String -> e.putString(name, value)
                is Int -> e.putInt(name, value)
            }

            e.apply()
        }.subscribeOn(Schedulers.io())
    }

    fun checkIfIntroCompleted(): Boolean {
        return sharedPreferences.getBoolean(INTRO_COMPLETED, false)
    }

    fun completedIntro(): Completable {
        return save(INTRO_COMPLETED, true)
    }

    companion object {
        const val SHARED_PREFERENCES_NAME = "SHARED_PREFERENCES"
        const val INTRO_COMPLETED = "INTRO_COMPLETED"
    }
}