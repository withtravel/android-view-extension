package app.atta.view_extension

import android.app.Activity
import android.content.Context
import android.os.Build
import android.view.View
import android.view.WindowInsets
import android.view.inputmethod.InputMethodManager

fun Context.showSoftKeyboard(view: View) {
    if (view.requestFocus()) {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
    }
}

fun Context.hideSoftKeyboard(view: View) {
    if (view.requestFocus()) {
        val imm = this.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, InputMethodManager.HIDE_IMPLICIT_ONLY)
    }
}

fun Activity.hideSystemUI() {
    try {
        with(window) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
                setDecorFitsSystemWindows(true)
                insetsController?.hide(WindowInsets.Type.navigationBars())
            } else {
                // systemUiVisibility was deprecated in API level 30.
                decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                        or View.SYSTEM_UI_FLAG_FULLSCREEN
                        or View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY)
            }
        }
    }catch (e: Exception) {
    }
}

fun Activity.showSystemUI() {
    with(window) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            setDecorFitsSystemWindows(true)
            insetsController?.show(WindowInsets.Type.navigationBars())
        } else {
            // systemUiVisibility was deprecated in API level 30.
            decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
        }
    }
}
