package app.atta.view_extension

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.widget.TextView


class LayoutTextView : TextView {
    constructor(context: Context?) : super(context)
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs)
    constructor(context: Context?, attrs: AttributeSet?, defStyle: Int) : super(
        context,
        attrs,
        defStyle
    )

    interface OnLayoutListener {
        fun onPreDraw(view: TextView?)
    }

    private var layoutListener: OnLayoutListener? = null
    private var oneShot = false

    fun setOnLayoutListener(listener: OnLayoutListener?, oneShot: Boolean = false) {
        layoutListener = listener
        this.oneShot = oneShot
        viewTreeObserver.addOnPreDrawListener {
            Log.e("Layout", "onPreDraw")
            // super.onLayout(changed, left, top, right, bottom)
            layoutListener?.onPreDraw(this)
            if (oneShot) layoutListener = null
            true
        }
    }
}