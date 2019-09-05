package tech.reface.codespecial.app

import android.graphics.Typeface

object Config {
    val REGULAR_FONT: Typeface = Typeface.createFromAsset(MainApp.appContext.assets, "fonts/Roboto-Regular.ttf")
    val MEDIUM_FONT: Typeface = Typeface.createFromAsset(MainApp.appContext.assets, "fonts/Roboto-Medium.ttf")
}
