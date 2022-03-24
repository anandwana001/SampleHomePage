package com.akshay.homepage.util

import android.content.res.Resources
import android.util.TypedValue

/**
 * Created by anandwana001 on
 * 24, March, 2022
 **/

val Int.toPx
  get() = TypedValue.applyDimension(
    TypedValue.COMPLEX_UNIT_DIP,
    this.toFloat(),
    Resources.getSystem().displayMetrics
  )