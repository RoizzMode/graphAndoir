package com.example.graphonandroid.analysers

import android.content.Context
import android.content.res.Configuration

class DeviceSizeChecker(private val context: Context) {
    fun isLarge(): Boolean {
        if ((context.resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) > Configuration.SCREENLAYOUT_SIZE_LARGE)
            return true
        return false
    }
}