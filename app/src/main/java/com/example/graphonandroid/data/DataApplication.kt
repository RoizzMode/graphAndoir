package com.example.graphonandroid.data

import android.app.Application
import com.example.graphonandroid.bases.DatabaseHandler
import com.example.graphonandroid.bases.VertexBaseHandler

class DataApplication : Application() {

    private val db = DatabaseHandler(this)
    val graphModel = GraphModel(AlgorithmDFS(), VertexBaseHandler(db))

    override fun onCreate() {
        super.onCreate()
        graphModel.initModel()
    }
}
