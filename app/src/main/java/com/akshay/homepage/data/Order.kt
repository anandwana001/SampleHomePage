/**
 * Created by anandwana001 on
 * 22, March, 2022
 **/
package com.akshay.homepage.data

data class Order(
  val id: Int,
  val timestamp: String,
  val item_count: String,
  val price: String,
  val status: String,
  val type: Int,
  val is_new: Boolean,
  val thumbnail: String
)
