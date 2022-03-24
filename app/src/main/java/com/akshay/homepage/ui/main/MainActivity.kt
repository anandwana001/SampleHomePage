/**
 * Created by anandwana001 on
 * 24, March, 2022
 **/
package com.akshay.homepage.ui.main

import android.graphics.Typeface
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.akshay.homepage.R
import com.akshay.homepage.ui.home.HomeFragment
import com.akshay.homepage.ui.home.HomeFragment.Companion.TAG_HOME_FRAGMENT
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

  private val mainViewModel: MainViewModel by viewModels()

  override fun onCreate(savedInstanceState: Bundle?) {
    // Sometime Android failed to update the decor view while recreating the activity.
    window.decorView.layoutDirection = baseContext.resources.configuration.layoutDirection

    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

    val shareTitle = findViewById<TextView>(R.id.share_title)
    shareTitle.setTypeface(
      Typeface.createFromAsset(
        getAssets(),
        "font/galanogrotesque_semibold.otf"
      )
    )

    val shareMessage = findViewById<TextView>(R.id.share_message)
    shareMessage.setTypeface(
      Typeface.createFromAsset(
        getAssets(),
        "font/galanogrotesque_regular.otf"
      )
    )

    val shareLink = findViewById<TextView>(R.id.share_link)
    shareLink.setTypeface(
      Typeface.createFromAsset(
        getAssets(),
        "font/galanogrotesque_semibold.otf"
      )
    )

    val shareBanner = findViewById<TextView>(R.id.textView)
    shareBanner.setTypeface(
      Typeface.createFromAsset(
        getAssets(),
        "font/galanogrotesque_medium.otf"
      )
    )


    if (getHomeFragment() == null) {
      supportFragmentManager.beginTransaction().add(
        R.id.fragment_container_view,
        HomeFragment.newInstance(),
        TAG_HOME_FRAGMENT
      ).commitNow()
    }
  }

  private fun getHomeFragment(): HomeFragment? {
    return supportFragmentManager.findFragmentById(
      R.id.fragment_container_view
    ) as HomeFragment?
  }
}