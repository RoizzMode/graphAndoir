package com.example.graphonandroid.contracts

interface ListContract {
    fun showNeighboursButtonClicked(position:Int)
    fun addNeighbourButtonClicked(position: Int)
    fun neighbourCheckBoxChecked(currentName: String, currentPosition:Int)
    fun neighbourCheckBoxUnchecked(currentName: String, currentPosition: Int)
}