/**
 * Created by anandwana001 on
 * 24, March, 2022
 **/

package com.akshay.homepage.ui.binding

import android.widget.ArrayAdapter
import androidx.appcompat.widget.AppCompatSpinner
import androidx.databinding.BindingAdapter
import com.akshay.homepage.R

/**
 * General BindingAdapter for Supporting Views.
 */
@BindingAdapter("app:dropDownList")
fun setDropDownList(
  spinner: AppCompatSpinner,
  string: Array<String>
) {
  val adapter =
    ArrayAdapter(spinner.context, R.layout.spinner_item, string)
  spinner.adapter = adapter
}