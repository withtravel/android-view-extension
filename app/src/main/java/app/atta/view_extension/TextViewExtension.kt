package app.atta.view_extension

import android.widget.TextView


fun TextView.getSpecificLine(line: Int): String {
    val s = this.layout.getLineStart(line)
    val e = this.layout.getLineEnd(line)
    return this.text.subSequence(s, e).toString()
}

fun TextView.getSpecificLines(startLine: Int, endLine: Int): String {
    val s = this.layout.getLineStart(startLine)
    val e = this.layout.getLineEnd(endLine)
    return this.text.subSequence(s, e).toString()
}