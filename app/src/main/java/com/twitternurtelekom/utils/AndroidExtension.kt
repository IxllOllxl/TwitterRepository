package com.twitternurtelekom.utils

import android.content.Context
import android.graphics.Color
import android.graphics.Rect
import android.graphics.Typeface
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Toast
import com.amulyakhare.textdrawable.TextDrawable
import com.amulyakhare.textdrawable.util.ColorGenerator
import com.facebook.drawee.view.SimpleDraweeView
import com.twitternurtelekom.R

fun Context.toast(message: String, duration: Int = Toast.LENGTH_LONG): Toast {
    return Toast.makeText(this, message, duration).apply { show() }
}

fun View.gone() {
    visibility = View.GONE
}

fun View.visible() {
    visibility = View.VISIBLE
}

fun View.invisible() {
    visibility = View.INVISIBLE
}

fun EditText.text() = text.toString()

fun EditText.subscribe(onAfterTextChanged: ((s: Editable?) -> Unit?)? = null,
                       onBeforeTextChanged: ((s: CharSequence?, start: Int, count: Int, after: Int) -> Unit)? = null,
                       onTextChanged: ((s: CharSequence?, start: Int, before: Int, count: Int) -> Unit)? = null) {
    addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(s: Editable?) {
            onAfterTextChanged?.invoke(s)
        }

        override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            onBeforeTextChanged?.invoke(s, start, count, after)
        }

        override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            onTextChanged?.invoke(s, start, before, count)
        }

    })
}

fun SimpleDraweeView.textCirclePlaceholder(s: String?, size: Int = context.resources.getDimensionPixelSize(R.dimen.avatar_size)) {
    var text = s
    if (text == null) text = ""

    val generator = ColorGenerator.MATERIAL
    val color = generator.getColor(text)

    val builder = TextDrawable.builder()
            .beginConfig()
            .textColor(Color.WHITE)
            .useFont(Typeface.DEFAULT)
            .fontSize(size)
            .bold()
            .toUpperCase()
            .endConfig()
            .round()
    var letters = ""
    text.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
            .asSequence()
            .filter { it.isNotEmpty() && letters.length < 2 }
            .forEach { letters += it.substring(0, 1) }

    val drawable = builder.build(letters, color)
    drawable.setPadding(Rect())
    hierarchy.setPlaceholderImage(drawable)
}