package com.example.graphonandroid.data

import android.app.Application

class DataApplication: Application() {

    val graphModel = GraphModel(AlgorithmDFS())
}
