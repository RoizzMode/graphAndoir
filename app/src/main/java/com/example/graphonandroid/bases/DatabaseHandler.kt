package com.example.graphonandroid.bases

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log


class DatabaseHandler(context:Context) : SQLiteOpenHelper(context, "Vertexes", null, 1) {

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $TABLE_NAME ($NAME TEXT PRIMARY KEY, $NEIGHBOURS TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addVertex(item:String, neighbours: String){
        val db = this.writableDatabase
        val values = ContentValues()
        println(item)
        values.put(NAME, item)
        values.put(NEIGHBOURS, neighbours)
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateNeighbour(item:String, neighbours: String){
        println(item)
        val db = this.writableDatabase
        val values = ContentValues()
        values.put(NEIGHBOURS, neighbours)
        db.update(TABLE_NAME, values, "$NAME = \"$item\"" , null)
        db.close()
    }

    fun getAllVertexes(): ArrayList<String>{
        val listVertexNames = arrayListOf<String>()
        val db = this.readableDatabase
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
        val db = this.writableDatabase
        db.execSQL("DELETE from $TABLE_NAME")
    }

    fun getNeighboursForVertex(name:String):String{
        lateinit var neighbours:String
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $NAME = \"$name\"", null)
        if (cursor.count == 0)
            return ""
        cursor.moveToFirst()
        neighbours = cursor.getString(cursor.getColumnIndex(NEIGHBOURS))
        cursor.close()
        return neighbours
    }

    companion object{
        private const val TABLE_NAME = "VERTEXES"
        private const val NAME = "name"
        private const val NEIGHBOURS = "neighbours"
    }
}