package app.atta.view_extension

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter

object BindingAdapters {

    @BindingAdapter("app:isSelected")
    @JvmStatic
    fun isSelected(button: Button, selected: Boolean) {
        button.isSelected = selected
    }

    @BindingAdapter("app:showTextIfNotEmpty")
    @JvmStatic
    fun showTextIfNotEmpty(view: TextView, text: String?) {
        view.visibility = if (text.isNullOrEmpty()) {
            view.text = ""
            View.GONE
        } else {
            view.text = text
            View.VISIBLE
        }
    }

    @BindingAdapter("app:isClickable")
    @JvmStatic
    fun isClickable(view: View, isAvailable: Boolean) {
        with(view) {
            isClickable = isAvailable
            isEnabled = isAvailable
            alpha = if (isAvailable) 1.0f else 0.5f
        }
    }
}