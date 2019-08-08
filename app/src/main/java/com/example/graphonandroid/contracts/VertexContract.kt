package com.example.graphonandroid.contracts

import com.example.graphonandroid.data.VertexStringData

interface VertexContract {

    interface VertexView {
        fun showItemsNames(items: List<VertexStringData>)
        fun showNameTakenMessage()
        fun letEnterNewVertex()
        fun showNeighboursForThisVertex(position: Int, items: List<VertexStringData>)
    }

    interface VertexPresenter {
        fun calculateButtonClicked()
        fun addVertexConfirmedClicked()
        fun addVertexButtonClicked()
        fun vertexNameEntered(name: String)
        fun clearAllClicked()
        fun dialogClosed()
    }
}