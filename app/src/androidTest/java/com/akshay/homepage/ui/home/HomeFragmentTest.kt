/**
 * Created by anandwana001 on
 * 24, March, 2022
 **/
package com.akshay.homepage.ui.home

import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ActivityScenario
import com.akshay.homepage.HiltTestActivity
import com.akshay.homepage.R
import com.akshay.homepage.utility.checkTextOnRecyclerViewItem
import com.akshay.homepage.utility.launchFragmentInHiltContainer
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test

/**
 * Tests for [HomeFragment].
 */
@HiltAndroidTest
class HomeFragmentTest {

  @get:Rule
  var hiltRule = HiltAndroidRule(this)

  @Before
  fun init() {
    hiltRule.inject()
  }

  @Test
  fun test_listIsVisible() {
    launchHomeFragment().use {
    }
  }

  /**
   * Launch the  [HomeFragment] in isolation.
   */
  private fun launchHomeFragment(): ActivityScenario<HiltTestActivity> {
    return launchFragmentInHiltContainer<HomeFragment> {
      this.view?.findViewById<RecyclerView>(R.id.home_recyclerview)?.itemAnimator = null
    }
  }

  /**
   * Helper function to call another helper function for home_recyclerview.
   */
  private fun checkTextOnHomeRecyclerView(itemId: Int, text: String, position: Int) {
    checkTextOnRecyclerViewItem(
      recyclerViewId = R.id.home_recyclerview,
      itemId = itemId,
      text = text,
      position = position
    )
  }
}