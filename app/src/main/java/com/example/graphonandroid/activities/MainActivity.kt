package com.example.graphonandroid.activities

import android.content.res.Configuration
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.graphonandroid.R
import com.example.graphonandroid.routers.MainActivityInitializer


class MainActivity : AppCompatActivity() {

    private lateinit var activityInitializer: MainActivityInitializer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityInitializer = MainActivityInitializer(this)
        if (isLarge()) {
            setContentView(R.layout.tablet_layout)
            activityInitializer.initLargeScreen()
        } else {
            setContentView(R.layout.phone_layout)
            activityInitializer.initSmallScreen()
        }
    }

    private fun isLarge(): Boolean {
        if ((resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) > Configuration.SCREENLAYOUT_SIZE_LARGE)
            return true
        return false
    }
}
