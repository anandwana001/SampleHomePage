package com.akshay.homepage.data

import com.akshay.homepage.MockData
import com.akshay.homepage.util.MoshiAdapters
import com.google.common.truth.Truth.assertThat
import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.HiltTestApplication
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import javax.inject.Inject

/**
 * Created by anandwana001 on
 * 24, March, 2022
 **/
@HiltAndroidTest
@RunWith(RobolectricTestRunner::class)
@Config(application = HiltTestApplication::class)
class ResponseTest {

  @Inject
  lateinit var moshiAdapters: MoshiAdapters

  @get:Rule
  var hiltRule = HiltAndroidRule(this)

  @Before
  fun setUp() {
    hiltRule.inject()
  }

  @Test
  fun test_responseParsingIsCorrect() {
    val jsonData = moshiAdapters.fromJson<Response>(MockData.jsonHomePageData)
    assertThat(jsonData).isEqualTo(MockData.response)
  }
}