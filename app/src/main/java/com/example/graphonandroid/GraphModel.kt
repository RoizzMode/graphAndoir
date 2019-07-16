package com.example.graphonandroid

class GraphModel {
    val listVertex = arrayListOf<Vertex>()

    fun addNewVertex(name:String){
        listVertex.add(Vertex(name))
    }
}