package com.example.graphonandroid.routers

import com.example.graphonandroid.R
import com.example.graphonandroid.fragments.MainFragment
import com.example.graphonandroid.fragments.ResultFragment

class MainFragmentRouter(private val mainFragment: MainFragment) {

    fun goToNextScreen(){
        val fragmentTransaction = mainFragment.fragmentManager?.beginTransaction() ?: throw NullPointerException()
        fragmentTransaction.replace(R.id.container, ResultFragment(), "RESULT_FRAGMENT")
        fragmentTransaction.addToBackStack(ResultFragment::class.java.name)
        fragmentTransaction.commit()
    }
}