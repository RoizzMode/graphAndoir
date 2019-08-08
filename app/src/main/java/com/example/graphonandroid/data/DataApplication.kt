package com.example.graphonandroid.data

import android.app.Application
import androidx.room.Room
import com.example.graphonandroid.bases.AppDatabase

class DataApplication : Application() {

    private lateinit var db: AppDatabase
    lateinit var graphModel: GraphModel

    override fun onCreate() {
        super.onCreate()
        db = Room.databaseBuilder(this, AppDatabase::class.java, "appDB").build()
        graphModel = GraphModel(AlgorithmDFS(), db)
        graphModel.initModel()
    }

    override fun onTerminate() {
        super.onTerminate()
        graphModel.release()
    }
}
