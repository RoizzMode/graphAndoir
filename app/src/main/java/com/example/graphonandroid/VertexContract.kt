package com.example.graphonandroid

interface VertexContract {

    interface VertexView {
        fun showNiceResult(resultGraph: String)
        fun showNullResult(resultGraph: String)
        fun showNeighbourAdded()
        fun showItemsNames(items:ArrayList<String>)
    }

    interface VertexPresenter {
        fun addVertexButtonClicked()
        fun calculateButtonClicked(name: String)
        fun addNeighbourButtonClicked(position: Int)
        fun neighbourNameEntered(name:String)
        fun vertexNameEntered(name:String)
    }
}