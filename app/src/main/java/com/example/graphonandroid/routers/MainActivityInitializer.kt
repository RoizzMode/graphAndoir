package com.example.graphonandroid.routers

import androidx.fragment.app.Fragment
import com.example.graphonandroid.R
import com.example.graphonandroid.activities.MainActivity
import com.example.graphonandroid.fragments.MainFragment
import com.example.graphonandroid.fragments.ResultFragment

class MainActivityInitializer(currentActivity: MainActivity) {
    private val fragmentManager = currentActivity.supportFragmentManager
    private val mainFragment = MainFragment()
    private val mainTag = "MAIN_FRAGMENT"
    private val resultTag = "RESULT_FRAGMENT"

    fun initLargeScreen() {
        val resultFragment = ResultFragment()
        addContainer(R.id.container_left, mainFragment, mainTag)
        addContainer(R.id.container_right, resultFragment, resultTag)
    }

    fun initSmallScreen() {
        addContainer(R.id.container, mainFragment, mainTag)
    }

    private fun addContainer(container:Int, fragment: Fragment, tag:String){
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(container, fragment, tag)
        fragmentTransaction.commit()
    }
}