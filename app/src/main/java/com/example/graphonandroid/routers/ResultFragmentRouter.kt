package com.example.graphonandroid.routers

import com.example.graphonandroid.fragments.ResultFragment

class ResultFragmentRouter(private val resultFragment: ResultFragment) {

    fun goToTheFirstScreen(){
        resultFragment.activity?.supportFragmentManager?.popBackStack() ?: throw NullPointerException()
    }
}