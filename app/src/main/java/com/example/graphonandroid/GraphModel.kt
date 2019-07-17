package com.example.graphonandroid

class GraphModel {
    val listVertex = arrayListOf<Vertex>()

    fun addNewVertex(name: String) {
        listVertex.add(Vertex(name))
    }

    fun addNewNeighbour(currentVertex: Vertex, nameOfNeighbour: String): Boolean {
        for (i in 0..(listVertex.lastIndex)) {
            if (nameOfNeighbour == listVertex[i].name) {
                currentVertex.neighbours.add(listVertex[i])
                return true
            }
        }
        return false
    }

    fun calculate(name: String): String? {
        val algorithm = AlgorithmDFS()
        val chosenVertex: Vertex? = findStartVertex(name, listVertex)
        if (chosenVertex == null) {
            return null
        } else (listVertex.size != 0)
        return algorithm.depthFirstSearchAndReset(chosenVertex, arrayListOf(), listVertex).toString()
    }

    private fun findStartVertex(name: String, listVertex: ArrayList<Vertex>): Vertex? {
        for (i in 0..listVertex.lastIndex) {
            if (name == listVertex[i].name)
                return listVertex[i]
        }
        return null
    }

    fun getNames():ArrayList<String>{
        val listNames = arrayListOf<String>()
        for (i in 0..listVertex.lastIndex){
            listNames.add(listVertex[i].name)
        }
        return (listNames)
    }
}
