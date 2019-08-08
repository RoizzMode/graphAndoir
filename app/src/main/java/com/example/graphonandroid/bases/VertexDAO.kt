package com.example.graphonandroid.bases

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface VertexDAO {
    @Query("SELECT * FROM VERTEXES")
    fun getAllVertexes(): List<VertexEntity>

    @Insert
    fun insert(vertexEntity: VertexEntity)

    @Query("DELETE FROM VERTEXES")
    fun deleteAll()
}