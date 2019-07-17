package com.example.graphonandroid

class GraphPresenter : VertexContract.VertexPresenter {

    private lateinit var view: VertexContract.VertexView
    private lateinit var nameOfNewNeighbour :String
    private val graphModel = GraphModel()
    private lateinit var nameOfNewVertex : String

    fun attachView(currentView: VertexContract.VertexView) {
        view = currentView
    }

    override fun addVertexButtonClicked() {
        graphModel.addNewVertex(nameOfNewVertex)
        view.showItemsNames(graphModel.getNames())
    }

    override fun calculateButtonClicked(name: String) {
        val result = graphModel.calculate(name)
        if (result == null)
            view.showNullResult("Such vertex doesn't exist")
        else
            view.showNiceResult(result)
    }

    override fun addNeighbourButtonClicked(position: Int) {
        if (!graphModel.addNewNeighbour(graphModel.listVertex[position], nameOfNewNeighbour))
            view.showNullResult("Such vertex doesn't exist")
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