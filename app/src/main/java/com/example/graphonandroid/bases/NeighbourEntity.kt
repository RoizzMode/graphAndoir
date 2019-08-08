package com.example.graphonandroid.bases

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "CONNECTIONS")
class NeighbourEntity(
    val start: Int,
    val finish: Int,
    @PrimaryKey(autoGenerate = true) var id: Int = 0
)
//){
//    @PrimaryKey(autoGenerate = true) var id: Int = 0
//}