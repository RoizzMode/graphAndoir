package com.example.graphonandroid.data

import android.app.Application
import com.example.graphonandroid.bases.DatabaseHandler

class DataApplication : Application() {

    private val db = DatabaseHandler(this)
    val graphModel = GraphModel(AlgorithmDFS(), db)
}
