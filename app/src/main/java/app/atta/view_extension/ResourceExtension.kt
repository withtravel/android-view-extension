package app.atta.view_extension

import android.content.Context
import kotlin.math.roundToInt


fun Context.dpToPx(dp: Int): Int {
    val density: Float = this.resources.displayMetrics.density
    return (dp.toFloat() * density).roundToInt()
}