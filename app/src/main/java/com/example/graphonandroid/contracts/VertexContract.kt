package com.example.graphonandroid.contracts

interface VertexContract {

    interface VertexView {
        fun showNullResult()
        fun showNeighbourAdded()
        fun showItemsNames(items: ArrayList<String>)
    }

    interface VertexPresenter : ListContract {
        fun addVertexButtonClicked()
        fun vertexNameEntered(name: String)
    }
}