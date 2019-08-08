package com.example.graphonandroid.bases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "VERTEXES")
class VertexEntity(
    @PrimaryKey val id: Int,
    val name: String
)