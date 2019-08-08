package com.example.graphonandroid.bases

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [VertexEntity::class, NeighbourEntity::class], version = 1, exportSchema = false)
abstract class AppDatabase: RoomDatabase() {
    abstract fun vertexDao(): VertexDAO
    abstract fun neighbourDao(): NeighbourDAO
}