package com.akshay.homepage.utility

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner
import dagger.hilt.android.testing.HiltTestApplication

/**
 * Created by anandwana001 on
 * 24, March, 2022
 **/
class HomePageRunner : AndroidJUnitRunner() {
  override fun newApplication(
    cl: ClassLoader?,
    className: String?,
    context: Context?
  ): Application {
    return super.newApplication(cl, HiltTestApplication::class.java.name, context)
  }
}