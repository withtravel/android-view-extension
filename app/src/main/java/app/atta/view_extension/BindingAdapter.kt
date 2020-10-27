package app.atta.view_extension

import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.databinding.BindingAdapter
import androidx.lifecycle.ViewTreeLifecycleOwner
import androidx.lifecycle.lifecycleScope

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

    /**
     * After function call, ignore next invocations for certain amount of time.
     * Set ViewTreeLifecycleOwner.set(window.decorView, this) before use in activity or fragment.
     * For example:
     * override fun onCreate(savedInstanceState: Bundle?) {
     *      super.onCreate(savedInstanceState)
     *      val binding = DataBindingUtil.setContentView<ActivityMainBinding>(
     *      this, R.layout.activity_main
     *      )
     *      binding.activity = this
     *      ViewTreeLifecycleOwner.set(window.decorView, this)
     * }
     */
    @BindingAdapter("app:onDebounceListener")
    fun onDebounceListener(view: View, onClickListener: View.OnClickListener) {
        val scope = ViewTreeLifecycleOwner.get(view)!!.lifecycleScope
        val clickWithDebounce: (view: View) -> Unit =
            debounce(scope = scope) {
                onClickListener.onClick(it)
            }
        view.setOnClickListener(clickWithDebounce)
    }
}