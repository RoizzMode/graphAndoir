package com.example.graphonandroid.presenters

import com.example.graphonandroid.contracts.ListContract
import com.example.graphonandroid.data.GraphModel
import com.example.graphonandroid.contracts.VertexContract

class MainPresenter(private val graphModel: GraphModel) : VertexContract.VertexPresenter, ListContract {

    private lateinit var view: VertexContract.VertexView
    private lateinit var nameOfNewVertex: String

    fun attachView(currentView: VertexContract.VertexView) {
        view = currentView
        graphModel.attachMainPresenter(this)
    }

    override fun viewCreated() {
        view.showItemsNames(graphModel.getItems())
    }

    override fun addVertexConfirmedClicked() {
        if (graphModel.checkIfNameUsed(nameOfNewVertex))
            view.showNameTakenMessage()
        else {
            graphModel.addNewVertex(nameOfNewVertex)
            view.showItemsNames(graphModel.getItems())
        }
    }

    override fun showNeighboursButtonClicked(position: Int) {
        view.showNeighboursForThisVertex(position, graphModel.getItems())
    }

    override fun addVertexButtonClicked(){
        view.letEnterNewVertex()
    }

    override fun addNeighbourButtonClicked(position: Int) {
        view.showItemsNames(graphModel.getItems())
    }

    override fun neighbourCheckBoxChecked(currentName:String, currentPosition: Int) {
        graphModel.addNeighbour(currentPosition, currentName)
    }

    override fun neighbourCheckBoxUnchecked(currentName: String, currentPosition: Int) {
        graphModel.removeNeighbour(currentPosition, currentName)
    }

    override fun vertexNameEntered(name: String) {
        nameOfNewVertex = name
    }

    override fun clearAllClicked() {
        graphModel.clearAll()
        view.showItemsNames(graphModel.getItems())
    }

    fun fragmentStarted(){
        graphModel.clearCurrentList()
        graphModel.applicationStarted()
        view.showItemsNames(graphModel.getItems())
    }

    override fun dataChanged() {
        view.showItemsNames(graphModel.getItems())
    }
}