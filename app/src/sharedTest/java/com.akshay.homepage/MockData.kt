package com.akshay.homepage

import com.akshay.homepage.data.Filter
import com.akshay.homepage.data.Order
import com.akshay.homepage.data.Response
import com.akshay.homepage.data.Result

object MockData {

  val jsonHomePageData = getHomeDataInJsonFormat()
  val response = getResponse()

  private fun getHomeDataInJsonFormat(): String {
    return """
      {
        "count": 2,
        "next": null,
        "previous": null,
        "results": {
          "filter": [
            {
              "id": 0,
              "count": 129,
              "name": "Pending"
            },
            {
              "id": 1,
              "count": 13,
              "name": "Accepted"
            },
            {
              "id": 2,
              "count": 5,
              "name": "Shipped"
            },
            {
              "id": 3,
              "count": 15,
              "name": "delivered"
            }
          ],
          "order": [
            {
              "id": 181210,
              "timestamp": "2021-01-23T02:43:31.000Z",
              "item_count": "12",
              "price": "₹79",
              "status": "paid",
              "type": 0,
              "is_new": true,
              "thumbnail": ""
            }
          ]
        }
      }
    """.trimIndent()
  }

  @JvmName("getResponse1")
  private fun getResponse(): Response {
    return Response(
      count = 2, next = null, previous = null,
      results = Result(
        filter = listOf(
          Filter(id = 0, count = 129, name = "Pending"),
          Filter(id = 1, count = 13, name = "Accepted"),
          Filter(id = 2, count = 5, name = "Shipped"),
          Filter(id = 3, count = 15, name = "delivered")
        ),
        order = listOf(
          Order(
            id = 181210,
            timestamp = "2021-01-23T02:43:31.000Z",
            item_count = "12",
            price = "₹79",
            status = "paid",
            type = 0,
            is_new = true,
            thumbnail = ""
          )
        )
      )
    )
  }
}