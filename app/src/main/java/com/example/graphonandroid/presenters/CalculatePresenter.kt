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
        view.showListOfVertex(graphModel.getNames())
    }

    override fun startVertexNameEntered(name: String) {
        nameOfStartVertex = name
    }

    override fun sortButtonClicked() {
        view.showSortResult(
            graphModel.getSortedVertexesResult()
        )
    }

    override fun clearAllClicked() {
        graphModel.clearAll()
        view.cleanResult()
    }

    override fun calculateWindowClosed() {
        view.showCalculateResult(graphModel.calculateFirstDepthSearch())
        view.paintGraph(graphModel.getItems(), graphModel.startPosition)
    }

    fun clearAll(){
        graphModel.clearAll()
    }
}