/**
 * Created by anandwana001 on
 * 24, March, 2022
 **/
package com.akshay.homepage.util


import java.text.NumberFormat
import java.util.*

/**
 * Utility related to currency.
 */
object CurrencyUtility {

  /**
   * Generate the given amount into currency.
   *
   * @param locale is the geographical place which currency type is required.
   * @param amount is the amount to modify.
   */
  fun getCurrencyOf(locale: Locale, amount: Int): String {
    return NumberFormat.getCurrencyInstance(locale).format(amount)
  }
}