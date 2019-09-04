@file:JvmName("ViewCompatUtils")
@file:Suppress("NOTHING_TO_INLINE")

package tech.reface.codespecial.presentation.model.extensions

import android.view.View

const val DEFAULT_DELAY_MS = 750L

inline fun View.onClickDebounce(delayMs: Long = DEFAULT_DELAY_MS, crossinline l: (View?) -> Unit) {
    setOnClickListener(object : View.OnClickListener {
        private var notClicked = true
        override fun onClick(view: View) {
            if (notClicked) {
                notClicked = false
                l(view)
                view.postDelayed({ notClicked = true }, delayMs)
            }
        }
    })
}

inline fun View.onClick(crossinline l: (View?) -> Unit) = onClickDebounce(DEFAULT_DELAY_MS, l)