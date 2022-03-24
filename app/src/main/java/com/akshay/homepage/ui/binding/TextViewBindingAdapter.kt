package com.akshay.homepage.ui.binding

import android.graphics.Typeface
import android.view.View
import android.widget.TextView
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.BindingAdapter
import com.akshay.homepage.R
import com.akshay.homepage.util.formatDateStringToPattern

/**
 * Created by anandwana001 on
 * 22, March, 2022
 **/
@BindingAdapter("app:customTypeface")
fun setTypeface(textView: TextView, fontName: String) {
  val face = Typeface.createFromAsset(
    textView.context.assets,
    "font/$fontName.otf"
  )
  textView.setTypeface(face)
}

@BindingAdapter("app:date")
fun setReadableTimeStamp(textView: TextView, timestamp: String) {
  val parsedDate = formatDateStringToPattern(timestamp, "h:mm a")
  textView.text = parsedDate
}

@BindingAdapter("app:statusBubble")
fun setBubbleForStatus(view: TextView, status: String?) {
  when (status) {
    "paid" -> view.background =
      ResourcesCompat.getDrawable(
        view.context.resources,
        R.drawable.paid_bubble,
        null
      )
    "COD" -> view.background =
      ResourcesCompat.getDrawable(
        view.context.resources,
        R.drawable.cod_bubble,
        null
      )
  }
}

@BindingAdapter("app:status")
fun setStatusText(textView: TextView, status: String) {
  when (status) {
    "paid" -> {
      textView.text = textView.context.resources.getString(R.string.paid_status)
      textView.setTextColor(textView.context.resources.getColor(R.color.new_bubble))
    }
    "COD" -> {
      textView.text = textView.context.resources.getString(R.string.cod_status)
      textView.setTextColor(textView.context.resources.getColor(R.color.cod_text_color))
    }
  }
}