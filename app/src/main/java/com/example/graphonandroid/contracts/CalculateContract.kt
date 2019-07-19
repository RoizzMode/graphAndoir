package com.example.graphonandroid.contracts

interface CalculateContract {
    interface CalculateView{
        fun showCalculateResult(result:String)
        fun showSortResult(result:String)
        fun showNullResult()
    }

    interface CalculatePresenter{
        fun calculateButtonClicked()
        fun sortButtonClicked()
        fun startVertexNameEntered(name:String)
    }
}