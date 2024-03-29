package com.example.graphonandroid.presenters

import android.util.Log
import com.example.graphonandroid.contracts.ListContract
import com.example.graphonandroid.data.GraphModel
import com.example.graphonandroid.contracts.VertexContract
import com.example.graphonandroid.contracts.VertexListener

class MainPresenter(private val graphModel: GraphModel) : VertexContract.VertexPresenter, ListContract {

    private lateinit var view: VertexContract.VertexView
    private lateinit var nameOfNewVertex: String
    private var listener: VertexListener? = null

    fun attachView(currentView: VertexContract.VertexView) {
        view = currentView
    }

    override fun addVertexConfirmedClicked() {
        if (graphModel.checkIfNameUsed(nameOfNewVertex))
            view.showNameTakenMessage()
        else {
            graphModel.addNewVertex(nameOfNewVertex)
            view.showItemsNames(graphModel.getItems())
        }
    }

    override fun dialogClosed() {
        view.showItemsNames(graphModel.getItems())
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

    override fun vertexNameEntered(name: String) {
        nameOfNewVertex = name
    }

    override fun clearAllClicked() {
        graphModel.clearAll()
        view.showItemsNames(graphModel.getItems())
    }

    fun fragmentStarted(){
        listener = object : VertexListener{
            override fun onDataChanged() {
                view.showItemsNames(graphModel.getItems())
            }
        }
        graphModel.setOnChangeVertexListener(listener)
        view.showItemsNames(graphModel.getItems())
    }

    override fun calculateButtonClicked() {
        graphModel.removeOnChangeVertexListener(listener ?: throw NullPointerException())
    }
}