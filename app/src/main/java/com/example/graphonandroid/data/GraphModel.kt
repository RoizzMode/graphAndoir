package com.example.graphonandroid.data

class GraphModel(private val algorithmDFS: AlgorithmDFS) {
    val listVertex = arrayListOf<Vertex>()

    fun addNewVertex(name: String) {
        listVertex.add(Vertex(name))
    }

    fun addNewNeighbour(position: Int, nameOfNeighbour: String): Boolean {
        val currentVertex = listVertex[position]
        for (i in 0..(listVertex.lastIndex)) {
            if (nameOfNeighbour == listVertex[i].name) {
                currentVertex.neighbours.add(listVertex[i])
                return true
            }
        }
        return false
    }


    fun calculateFirstDepthSearch(name: String): String? {
        val algorithm = algorithmDFS
        val chosenVertex: Vertex? = findStartVertex(name, listVertex)
        if (chosenVertex == null) {
            return null
        } else (listVertex.size != 0)
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

    fun sortVertexes(): List<String> {
        val list = getNames()
        list.sortBy { it.length }
        return list
    }
}