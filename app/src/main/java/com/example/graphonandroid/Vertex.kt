package com.example.graphonandroid

class Vertex(val name: String) {
    var isVisited = false
    val neighbours: ArrayList<Vertex> = arrayListOf()

    override fun toString(): String {
        return name
    }
}

class AlgorithmDFS {

    fun depthFirstSearchAndReset(ver:Vertex, finalArray: ArrayList<Vertex>, listOfVertex: ArrayList<Vertex>): ArrayList<Vertex>{
        val arrayToReturn = depthFirstSearch(ver, finalArray)
        resetVertexes(listOfVertex)
        return (arrayToReturn)
    }

    private fun depthFirstSearch(ver: Vertex, finalArray: ArrayList<Vertex>): ArrayList<Vertex> {
        finalArray.add(ver)
        ver.isVisited = true
        for (i in 0 until ver.neighbours.size) {
            val v = ver.neighbours[i]
            if (!v.isVisited)
                depthFirstSearch(v, finalArray)
        }
        return (finalArray)
    }

    private fun resetVertexes(listOfVertex:ArrayList<Vertex>){
        for (i in 0..listOfVertex.lastIndex){
            listOfVertex[i].isVisited = false
        }
    }
}