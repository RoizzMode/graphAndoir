package com.example.graphonandroid.data

class Vertex(val name: String) {
    var isVisited = false
    val neighbours: ArrayList<Vertex> = arrayListOf()

    override fun toString(): String {
        return name
    }
}