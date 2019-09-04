package tech.reface.codespecial.app

import android.graphics.Typeface

object Config {
    val LIGHT_FONT: Typeface = Typeface.createFromAsset(MainApp.appContext.assets, "fonts/Roboto-Light.ttf")
    val REGULAR_FONT: Typeface = Typeface.createFromAsset(MainApp.appContext.assets, "fonts/Roboto-Regular.ttf")
    val MEDIUM_FONT: Typeface = Typeface.createFromAsset(MainApp.appContext.assets, "fonts/Roboto-Medium.ttf")
    val BOLD_FONT: Typeface = Typeface.createFromAsset(MainApp.appContext.assets, "fonts/Roboto-Bold.ttf")
}
