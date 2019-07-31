package com.example.graphonandroid.contracts

interface CalculateContract {
    interface CalculateView {
        fun showListOfVertex(list: ArrayList<String>)
        fun showCalculateResult(result: String)
        fun showSortResult(result: String)
        fun cleanResult()
    }

    interface CalculatePresenter {
        fun calculateButtonClicked()
        fun sortButtonClicked()
        fun startVertexNameEntered(name: String)
        fun clearAllClicked()
        fun resultCalculated(result: String)
    }
}