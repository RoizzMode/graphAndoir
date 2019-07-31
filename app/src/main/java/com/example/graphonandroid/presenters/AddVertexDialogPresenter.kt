package com.example.graphonandroid.presenters

import com.example.graphonandroid.contracts.AddVertexDialogContract
import com.example.graphonandroid.data.GraphModel

class AddVertexDialogPresenter(private val graphModel: GraphModel) : AddVertexDialogContract.DialogPresenter {

    private lateinit var view: AddVertexDialogContract.DialogView
    private lateinit var nameOfNewVertex: String

    fun attachView(currentView: AddVertexDialogContract.DialogView) {
        view = currentView
    }

    override fun vertexNameEntered(name: String) {
        nameOfNewVertex = name
    }

    override fun addVertexConfirmedClicked() {
        if (graphModel.checkIfNameUsed(nameOfNewVertex))
            view.showNameTakenMessage()
        else {
            graphModel.addNewVertex(nameOfNewVertex)
        }
    }
}