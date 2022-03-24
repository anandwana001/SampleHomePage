package com.akshay.homepage.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.akshay.homepage.databinding.FragmentHomeBinding
import com.akshay.homepage.databinding.FilterChipBinding
import com.akshay.homepage.databinding.FilterChipsListBinding
import com.akshay.homepage.databinding.OrderItemBinding
import com.akshay.homepage.databinding.OrderListBinding
import com.akshay.homepage.databinding.OrdersTitleBinding
import com.akshay.homepage.databinding.OverviewItemBinding
import com.akshay.homepage.databinding.OverviewListBinding
import com.akshay.homepage.databinding.OverviewTitleItemBinding
import com.akshay.homepage.ui.recyclerview.GridSpacingItemDecoration
import com.akshay.homepage.ui.recyclerview.MultiViewTypeBuilder
import com.akshay.homepage.ui.recyclerview.RoomAccRecyclerViewAdapter
import com.akshay.homepage.ui.recyclerview.SingleViewTypeBuilder
import com.akshay.homepage.util.toPx
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

  /**
   * Another approach to perform Fragment Transaction is to use the [FragmentResultListener].
   */
  companion object {
    const val TAG_HOME_FRAGMENT = "HOME_FRAGMENT"

    fun newInstance(): HomeFragment {
      return HomeFragment()
    }
  }

  private lateinit var binding: FragmentHomeBinding
  private val homeViewModel: HomeViewModel by viewModels()

  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View {
    binding = FragmentHomeBinding.inflate(layoutInflater, container, false)
    binding.apply {
      viewModel = homeViewModel
      lifecycleOwner = this@HomeFragment
    }
    binding.homeRecyclerview.apply {
      adapter = createRecyclerViewAdapter()
    }
    return binding.root
  }

  private enum class ViewType {
    OVERVIEW_TITLE,
    OVERVIEW_LIST,
    ORDER_TITLE,
    FILTER_CHIP_LIST,
    ORDER_LIST
  }

  private fun createRecyclerViewAdapter(): RoomAccRecyclerViewAdapter<HomePageViewModel> {
    return MultiViewTypeBuilder
      .newBuilder<HomePageViewModel, ViewType> { viewModel ->
        when (viewModel) {
          is OverviewTitleItemViewModel -> ViewType.OVERVIEW_TITLE
          is OverviewListItemViewModel -> ViewType.OVERVIEW_LIST
          is OrderTitleItemViewModel -> ViewType.ORDER_TITLE
          is FilterChipListItemViewModel -> ViewType.FILTER_CHIP_LIST
          is OrderListItemViewModel -> ViewType.ORDER_LIST
          else -> throw IllegalArgumentException("Encountered unexpected view model: $viewModel")
        }
      }
      .registerViewDataBinder(
        viewType = ViewType.OVERVIEW_TITLE,
        inflateDataBinding = OverviewTitleItemBinding::inflate,
        setViewModel = OverviewTitleItemBinding::setViewModel,
        transformViewModel = { it as OverviewTitleItemViewModel }
      )
      .registerViewDataBinder(
        viewType = ViewType.OVERVIEW_LIST,
        inflateDataBinding = OverviewListBinding::inflate,
        setViewModel = this::bindOverviewList,
        transformViewModel = { it as OverviewListItemViewModel }
      )
      .registerViewDataBinder(
        viewType = ViewType.ORDER_TITLE,
        inflateDataBinding = OrdersTitleBinding::inflate,
        setViewModel = OrdersTitleBinding::setViewModel,
        transformViewModel = { it as OrderTitleItemViewModel }
      )
      .registerViewDataBinder(
        viewType = ViewType.FILTER_CHIP_LIST,
        inflateDataBinding = FilterChipsListBinding::inflate,
        setViewModel = this::bindFilterList,
        transformViewModel = { it as FilterChipListItemViewModel }
      )
      .registerViewDataBinder(
        viewType = ViewType.ORDER_LIST,
        inflateDataBinding = OrderListBinding::inflate,
        setViewModel = this::bindOrderList,
        transformViewModel = { it as OrderListItemViewModel }
      )
      .build()
  }

  private fun bindFilterList(
    binding: FilterChipsListBinding,
    viewModel: FilterChipListItemViewModel
  ) {
    binding.viewModel = viewModel

    binding.filterRecyclerview.apply {
      layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
      adapter = createFilterRecyclerViewAdapter()
    }
  }

  private fun bindOrderList(
    binding: OrderListBinding,
    viewModel: OrderListItemViewModel
  ) {
    binding.viewModel = viewModel

    binding.orderRecyclerview.apply {
      adapter = createOrderRecyclerViewAdapter()
    }
  }

  private fun bindOverviewList(
    binding: OverviewListBinding,
    viewModel: OverviewListItemViewModel
  ) {
    binding.viewModel = viewModel

    binding.overviewRecyclerview.apply {
      layoutManager = GridLayoutManager(context, 2)
      adapter = createOverviewRecyclerViewAdapter()
    }
    binding.overviewRecyclerview.addItemDecoration(
      GridSpacingItemDecoration(
        2,
        16.toPx.toInt(),
        false
      )
    )
  }

  private fun createFilterRecyclerViewAdapter(): RoomAccRecyclerViewAdapter<FilterChipItemViewModel> {
    return SingleViewTypeBuilder
      .newBuilder<FilterChipItemViewModel>()
      .setLifecycleOwner(this)
      .registerViewDataBinderWithSameModelType(
        inflateDataBinding = FilterChipBinding::inflate,
        setViewModel = FilterChipBinding::setViewModel
      )
      .build()
  }

  private fun createOrderRecyclerViewAdapter(): RoomAccRecyclerViewAdapter<OrderItemViewModel> {
    return SingleViewTypeBuilder
      .newBuilder<OrderItemViewModel>()
      .setLifecycleOwner(this)
      .registerViewDataBinderWithSameModelType(
        inflateDataBinding = OrderItemBinding::inflate,
        setViewModel = OrderItemBinding::setViewModel
      )
      .build()
  }

  private fun createOverviewRecyclerViewAdapter(): RoomAccRecyclerViewAdapter<OverviewItemViewModel> {
    return SingleViewTypeBuilder
      .newBuilder<OverviewItemViewModel>()
      .setLifecycleOwner(this)
      .registerViewDataBinderWithSameModelType(
        inflateDataBinding = OverviewItemBinding::inflate,
        setViewModel = OverviewItemBinding::setViewModel
      )
      .build()
  }
}
