/**
 * Created by anandwana001 on
 * 24, March, 2022
 **/

package com.akshay.homepage.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Utility for parsing or formatting the Date.
 */

/**
 * Modify the [date] pattern to another pattern.
 *
 * @param date string in the format `yyyy-MM-dd` | `2021-02-25`
 * @param pattern to which [date] converts to.
 *
 * @return converted date format.
 */
fun formatDateStringToPattern(date: String, pattern: String): String {
  val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'")
  val outputFormat = SimpleDateFormat("dd MMM yyyy")
  val parsedDate: Date = inputFormat.parse(date)
  val formattedDate: String = outputFormat.format(parsedDate)
  return formattedDate
}