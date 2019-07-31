package com.example.graphonandroid.data

import com.example.graphonandroid.bases.DatabaseHandler
import com.example.graphonandroid.contracts.CalculateContract
import com.example.graphonandroid.contracts.VertexContract


class GraphModel(private val algorithmDFS: AlgorithmDFS, private val db:DatabaseHandler) {

    val listVertex = arrayListOf<Vertex>()
    private lateinit var mainPresenter: VertexContract.VertexPresenter
    private lateinit var resultPresenter: CalculateContract.CalculatePresenter

    fun attachMainPresenter(currentMainPresenter: VertexContract.VertexPresenter){
        mainPresenter = currentMainPresenter
    }

    fun attachResultPresenter(currentResultPresenter: CalculateContract.CalculatePresenter){
        resultPresenter = currentResultPresenter
    }

    fun addNewVertex(name: String) {
        listVertex.add(Vertex(name))
        db.addVertex(listVertex[listVertex.lastIndex].name, listVertex[listVertex.lastIndex].neighbours.toString())
        mainPresenter.dataChanged()
    }

    fun addNeighbour(position: Int, name:String) {
        for (i in 0..listVertex.lastIndex) {
            if (name == listVertex[i].name)
                listVertex[position].neighbours.add(listVertex[i])
        }
        db.updateNeighbour(listVertex[position].name, listVertex[position].neighbours.toString())
        mainPresenter.dataChanged()
    }

    fun removeNeighbour(position: Int, name: String){
        var p = 0
        for (i in 0..listVertex[position].neighbours.lastIndex){
            if (name == listVertex[position].neighbours[i].name){
                println("Size:")
                println(listVertex[position].neighbours.size)
                p = i
            }
        }
        listVertex[position].neighbours.removeAt(p)
        db.updateNeighbour(listVertex[position].name, listVertex[position].neighbours.toString())
        mainPresenter.dataChanged()
    }

    fun clearAll() {
        listVertex.clear()
        deleteAllVertexesInDB()
    }

    fun clearCurrentList(){
        listVertex.clear()
    }

    fun calculateFirstDepthSearch(position: Int){
        val algorithm = algorithmDFS
        val chosenVertex: Vertex = listVertex[position]
        resultPresenter.resultCalculated(algorithm.calculateDepthFirstSearchAndReset(chosenVertex, arrayListOf(), listVertex).toString())
    }

    fun findStartVertex(name: String, listVertex: ArrayList<Vertex>): Vertex? {
        for (i in 0..listVertex.lastIndex) {
            if (name == listVertex[i].name)
                return listVertex[i]
        }
        return null
    }

    fun getNames(): ArrayList<String> {
        val listNames = arrayListOf<String>()
        for (i in 0..listVertex.lastIndex) {
            listNames.add(listVertex[i].name)
        }
        return (listNames)
    }

    fun getItems(): List<VertexStringData> {
        val listNames = mutableListOf<VertexStringData>()
        for (i in 0..listVertex.lastIndex) {
            listNames.add(VertexStringData(listVertex[i].name, getListOfNeighbours(listVertex[i])))
        }
        return (listNames)
    }

    fun getListOfNeighbours(currentVertex:Vertex): List<String>{
        val listOfNeighbours = mutableListOf<String>()
        for (i in 0..currentVertex.neighbours.lastIndex){
            listOfNeighbours.add(currentVertex.neighbours[i].name)
        }
        return listOfNeighbours
    }

    fun sortVertexes(): List<String> {
        val list = getNames()
        list.sortBy { it.length }
        return list
    }

    private fun deleteAllVertexesInDB(){
        db.deleteAll()
    }

    fun applicationStarted(){
        val list = db.getAllVertexes()
        for (i in 0..list.lastIndex){
            listVertex.add(Vertex(list[i]))
        }
        for (i in 0..listVertex.lastIndex)
            getNeighboursFromDB(listVertex[i])
    }

    private fun getNeighboursFromDB(currentVertex: Vertex){
        val neighboursString = db.getNeighboursForVertex(currentVertex.name)
        val neighboursList = neighboursString.split("[", "]", ", ")
        for (i in 0..neighboursList.lastIndex){
            for (j in 0..listVertex.lastIndex){
                if (neighboursList[i] == listVertex[j].name)
                    currentVertex.neighbours.add(listVertex[j])
            }
        }
    }

    fun checkIfNameUsed(name: String): Boolean{
        for (i in 0..listVertex.lastIndex){
            if (name == listVertex[i].name)
                return true
        }
        return false
    }

    fun getSortedVertexesResult() = sortVertexes().toString()
}