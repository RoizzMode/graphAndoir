package com.example.graphonandroid.contracts

import com.example.graphonandroid.data.VertexStringData

interface CalculateContract {
    interface CalculateView {
        fun showListOfVertex(list: List<String>)
        fun showCalculateResult(result: String)
        fun showSortResult(result: String)
        fun cleanResult()
        fun paintGraph(items: List<VertexStringData>, position: Int)
    }

    interface CalculatePresenter {
        fun calculateButtonClicked()
        fun sortButtonClicked()
        fun startVertexNameEntered(name: String)
        fun clearAllClicked()
        fun calculateWindowClosed()
    }
}