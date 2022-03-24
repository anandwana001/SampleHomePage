/**
 * Created by anandwana001 on
 * 22, March, 2022
 **/

package com.akshay.homepage.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import com.akshay.homepage.data.Filter
import com.akshay.homepage.data.Order
import com.akshay.homepage.data.Response
import com.akshay.homepage.util.CurrencyUtility.getCurrencyOf
import com.akshay.homepage.util.FileRetriever
import com.akshay.homepage.util.MoshiAdapters
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
  private val coroutineScope: CoroutineScope,
  private val fileRetriever: FileRetriever,
  private val moshiAdapters: MoshiAdapters
) : ViewModel() {

  private var computedList = mutableListOf<HomePageViewModel>()
  private val responseMutableLiveData = MutableLiveData<Response>()

  val homePageList: LiveData<List<HomePageViewModel>> by lazy {
    Transformations.map(fetchData(), ::computeData)
  }

  private fun fetchData(): LiveData<Response> {
    coroutineScope.launch {
      val response = computeHomePageData("homepage.json")
      responseMutableLiveData.postValue(response)
    }
    return responseMutableLiveData
  }

  private fun computeData(response: Response): List<HomePageViewModel> {
    response.results.let {
      computedList.add(OverviewTitleItemViewModel())
      computedList.add(OverviewListItemViewModel(computeOverviewList(it.order)))
      computedList.add(OrderTitleItemViewModel())
      computedList.add(FilterChipListItemViewModel(computeFilterList(it.filter)))
      computedList.add(OrderListItemViewModel(computeOrderItemList(it.order)))
    }
    return computedList
  }

  private fun computeOverviewList(order: List<Order>): List<OverviewItemViewModel> {
    val overviewItemList = mutableListOf<OverviewItemViewModel>()
    overviewItemList.add(OverviewItemViewModel("ORDERS", order.size.toString()))
    overviewItemList.add(
      OverviewItemViewModel(
        "TOTAL SALES",
        getCurrencyOf(Locale("en", "in"), computeTotalPrice(order))
      )
    )
    overviewItemList.add(OverviewItemViewModel("STORE VIEWS", order.size.toString()))
    overviewItemList.add(OverviewItemViewModel("PRODUCT VIEWS", order.size.toString()))
    return overviewItemList
  }

  private fun computeOrderItemList(order: List<Order>): List<OrderItemViewModel> {
    val orderItemList = mutableListOf<OrderItemViewModel>()
    order.forEach {
      orderItemList.add(
        OrderItemViewModel(
          it.id,
          it.timestamp,
          it.item_count,
          it.price,
          it.status,
          it.is_new
        )
      )
    }
    return orderItemList
  }

  private fun computeFilterList(
    filter: List<Filter>
  ): List<FilterChipItemViewModel> {
    val filterChipItemList = mutableListOf<FilterChipItemViewModel>()
    filter.forEach {
      filterChipItemList.add(FilterChipItemViewModel(it.name, it.count))
    }
    return filterChipItemList
  }

  private fun computeTotalPrice(order: List<Order>): Int {
    var totalPrice = 0
    order.forEach {
      totalPrice += it.price.removeRange(0, 1).toInt()
    }
    return totalPrice
  }

  private fun computeHomePageData(assetName: String): Response {
    val jsonString = fileRetriever.loadJsonFileFromAssets(assetName)
    val response = checkNotNull(moshiAdapters.fromJson<Response>(jsonString)) {
      "JSON file failed to parse"
    }
    return response
  }
}