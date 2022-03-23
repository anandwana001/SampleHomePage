package com.akshay.homepage.ui.binding

import android.graphics.Typeface
import android.widget.TextView
import androidx.databinding.BindingAdapter

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
