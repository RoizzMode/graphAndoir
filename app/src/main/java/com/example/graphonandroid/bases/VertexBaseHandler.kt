package com.example.graphonandroid.bases

import android.content.ContentValues

class VertexBaseHandler(private val database: DatabaseHandler) {
// todo

    fun addVertex(item:String, neighbours: String){
        val db = database.writableDatabase
        val values = ContentValues()
        println(item)
        values.put(NAME, item)
        values.put(NEIGHBOURS, neighbours)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateNeighbour(item:String, neighbours: String){
        println(item)
        val db = database.writableDatabase
        val values = ContentValues()
        values.put(NEIGHBOURS, neighbours)
        db.update(TABLE_NAME, values, "$NAME = \"$item\"" , null)
        db.close()
    }

    fun getAllVertexes(): ArrayList<String>{
        val listVertexNames = arrayListOf<String>()
        val db = database.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME", null)
        if (cursor.count == 0)
            return listVertexNames
        cursor.moveToFirst()
        listVertexNames.add(cursor.getString(cursor.getColumnIndex(NAME)))
        while (cursor.moveToNext()) {
            listVertexNames.add(cursor.getString(cursor.getColumnIndex(NAME)))
        }
        cursor.close()
        return listVertexNames
    }

    fun deleteAll(){
        val db = database.writableDatabase
        db.execSQL("DELETE from $TABLE_NAME")
    }

    fun getNeighboursForVertex(name:String):String{
        lateinit var neighbours:String
        val db = database.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $NAME = \"$name\"", null)
        if (cursor.count == 0)
            return ""
        cursor.moveToFirst()
        neighbours = cursor.getString(cursor.getColumnIndex(NEIGHBOURS))
        cursor.close()
        return neighbours
    }

    companion object{
        private const val TABLE_NAME = "VERTEXES" // todo
        private const val NAME = "name"
        private const val NEIGHBOURS = "neighbours"
    }
}