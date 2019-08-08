package com.example.graphonandroid.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.graphonandroid.R
import com.example.graphonandroid.analysers.DeviceSizeChecker
import com.example.graphonandroid.routers.MainActivityInitializer


class MainActivity : AppCompatActivity() {

    private lateinit var activityInitializer: MainActivityInitializer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val deviceAnalyzer = DeviceSizeChecker(this)
        activityInitializer = MainActivityInitializer(this)
        if (deviceAnalyzer.isLarge()) {
            setContentView(R.layout.tablet_layout)
            activityInitializer.initLargeScreen()
        } else {
            setContentView(R.layout.phone_layout)
            activityInitializer.initSmallScreen()
        }
    }
}
