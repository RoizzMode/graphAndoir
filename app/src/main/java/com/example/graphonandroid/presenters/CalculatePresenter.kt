package com.example.graphonandroid.presenters

import com.example.graphonandroid.data.GraphModel
import com.example.graphonandroid.contracts.CalculateContract

class CalculatePresenter(private val graphModel: GraphModel) : CalculateContract.CalculatePresenter {

    private lateinit var view: CalculateContract.CalculateView
    lateinit var nameOfStartVertex: String

    fun attachView(view: CalculateContract.CalculateView) {
        this.view = view
    }

    override fun calculateButtonClicked() {
        val result = graphModel.calculateFirstDepthSearch(nameOfStartVertex)
        if (result == null)
            view.showNullResult()
        else
            view.showCalculateResult(result)
    }

    override fun startVertexNameEntered(name: String) {
        nameOfStartVertex = name
    }

    override fun sortButtonClicked() {
        view.showSortResult(graphModel.sortVertexes().toString())
    }
}