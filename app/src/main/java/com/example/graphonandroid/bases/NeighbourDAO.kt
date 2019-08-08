package com.example.graphonandroid.bases

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface NeighbourDAO {
    @Insert
    fun insert(neighbourEntity: NeighbourEntity)

    @Query("SELECT * FROM CONNECTIONS WHERE start == :start")
    fun getAllNeighboursForThisVertex(start: Int): List<NeighbourEntity>

    @Query("DELETE FROM CONNECTIONS")
    fun deleteAll()

    @Query("DELETE FROM CONNECTIONS WHERE start == :start AND finish == :finish")
    fun removeNeighbour(start: Int, finish: Int)
}