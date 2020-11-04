package app.atta.view_extension

import android.graphics.PointF
import android.view.MotionEvent
import android.view.View


class MoveTouchEvent(private val callback: (state: MoveState) -> Unit) :
    View.OnTouchListener {

    private var previousPos = PointF(0f, 0f)
    private val limitedPos = PointF(LIMITED_X, LIMITED_Y)

    enum class MoveState {
        LEFT_TO_RIGHT,
        RIGHT_TO_LEFT,
        TOP_TO_BOTTOM,
        BOTTOM_TO_TOP
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        val x = event?.x ?: 0f
        val y = event?.y ?: 0f
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                previousPos = PointF(x, y)
            }
            MotionEvent.ACTION_MOVE -> {
                if (!isAvailable()) return false
                when {
                    (previousPos.x + limitedPos.x) < x -> {
                        resetPos()
                        callback.invoke(MoveState.LEFT_TO_RIGHT)
                        return false
                    }
                    (previousPos.x - limitedPos.x) > x -> {
                        resetPos()
                        callback.invoke(MoveState.RIGHT_TO_LEFT)
                        return false
                    }
                    (previousPos.y + limitedPos.y) < y -> {
                        resetPos()
                        callback.invoke(MoveState.TOP_TO_BOTTOM)
                        return false
                    }
                    (previousPos.y - limitedPos.y) > y -> {
                        resetPos()
                        callback.invoke(MoveState.BOTTOM_TO_TOP)
                        return false
                    }
                }
            }
        }
        return true
    }

    fun setLimitedPos(x: Float, y: Float) {
        with(limitedPos) {
            this.x = x
            this.y = y
        }
    }

    private fun resetPos() {
        previousPos = PointF(0f, 0f)
    }

    private fun isAvailable(): Boolean {
        return (previousPos.x != 0f && previousPos.y != 0f)
    }

    companion object {
        private const val LIMITED_X = 100f
        private const val LIMITED_Y = 100f
    }
}