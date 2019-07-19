package com.example.graphonandroid.data

class AlgorithmDFS {

    fun calculateDepthFirstSearchAndReset(ver: Vertex, finalArray: ArrayList<Vertex>, listOfVertex: ArrayList<Vertex>): ArrayList<Vertex>{
        val arrayToReturn = calculateDepthFirstSearch(ver, finalArray)
        resetVertexes(listOfVertex)
        return (arrayToReturn)
    }

    fun calculateDepthFirstSearch(ver: Vertex, finalArray: ArrayList<Vertex>): ArrayList<Vertex> {
        finalArray.add(ver)
        ver.isVisited = true
        for (i in 0 until ver.neighbours.size) {
            val v = ver.neighbours[i]
            if (!v.isVisited)
                calculateDepthFirstSearch(v, finalArray)
        }
        return (finalArray)
    }

    private fun resetVertexes(listOfVertex:ArrayList<Vertex>){
        for (i in 0..listOfVertex.lastIndex){
            listOfVertex[i].isVisited = false
        }
    }
}