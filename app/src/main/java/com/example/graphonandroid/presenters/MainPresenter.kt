package com.example.graphonandroid.presenters

import com.example.graphonandroid.data.GraphModel
import com.example.graphonandroid.contracts.VertexContract

class MainPresenter(private val graphModel: GraphModel) : VertexContract.VertexPresenter {

    private lateinit var view: VertexContract.VertexView
    lateinit var nameOfNewNeighbour :String
    lateinit var nameOfNewVertex : String
    private var added = false

    fun attachView(currentView: VertexContract.VertexView) {
        view = currentView
    }

    override fun addVertexButtonClicked() {
        graphModel.addNewVertex(nameOfNewVertex)
        view.showItemsNames(graphModel.getNames())
    }

    override fun addNeighbourButtonClicked(position: Int) {
        added = graphModel.addNewNeighbour(position, nameOfNewNeighbour)
        if (!added)
            view.showNullResult()
        else
            view.showNeighbourAdded()
    }

    override fun neighbourNameEntered(name: String) {
        nameOfNewNeighbour = name
    }

    override fun vertexNameEntered(name: String) {
        nameOfNewVertex = name
    }
}