package com.example.graphonandroid.data

import com.example.graphonandroid.bases.VertexBaseHandler
import com.example.graphonandroid.contracts.VertexListener

// todo GraphView, MigrateBase, DataListener
class GraphModel(private val algorithmDFS: AlgorithmDFS, private val vertexBaseHandler: VertexBaseHandler) {


    val listVertex = arrayListOf<Vertex>()
    var startPosition = -1
    lateinit var listen: VertexListener

    fun addNewVertex(name: String) {
        listVertex.add(Vertex(name))
        listen.onDataChanged()

        val vertexName = listVertex[listVertex.lastIndex].name
        val vertexNeighbours = listVertex[listVertex.lastIndex].neighbours.toString()
        vertexBaseHandler.addVertex(vertexName, vertexNeighbours)
    }

    fun addNeighbour(position: Int, name:String) {
        for (i in 0..listVertex.lastIndex) {
            if (name == listVertex[i].name)
                listVertex[position].neighbours.add(listVertex[i])
        }
        listen.onDataChanged()

        updateNeighbours(position)
    }

    fun removeNeighbour(position: Int, name: String){
        var p = 0
        for (i in 0..listVertex[position].neighbours.lastIndex){
            if (name == listVertex[position].neighbours[i].name){
                p = i
                break
            }
        }
        listVertex[position].neighbours.removeAt(p)

        updateNeighbours(position)
    }

    private fun updateNeighbours(position: Int){
        val vertexName  = listVertex[position].name
        val vertexNeighbours = listVertex[position].neighbours.toString()
        vertexBaseHandler.updateNeighbour(vertexName, vertexNeighbours)
    }

    fun clearAll() {
        listVertex.clear()
        deleteAllVertexesInDB()
    }

    fun calculateFirstDepthSearch(): String{
        val algorithm = algorithmDFS
        if (startPosition == -1)
            return "Result"
        val chosenVertex: Vertex = listVertex[startPosition]
        return algorithm.calculateDepthFirstSearchAndReset(chosenVertex, arrayListOf(), listVertex).toString()
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

    private fun getListOfNeighbours(currentVertex:Vertex): List<String>{
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
        vertexBaseHandler.deleteAll()
    }

    fun initModel(){
        val list = vertexBaseHandler.getAllVertexes()
        for (i in 0..list.lastIndex){
            listVertex.add(Vertex(list[i]))
        }
        for (i in 0..listVertex.lastIndex)
            getNeighboursFromDB(listVertex[i])
    }

    private fun getNeighboursFromDB(currentVertex: Vertex){
        val neighboursString = vertexBaseHandler.getNeighboursForVertex(currentVertex.name)
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