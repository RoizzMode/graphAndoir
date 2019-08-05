package com.example.graphonandroid.bases

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class DatabaseHandler(context:Context) : SQLiteOpenHelper(context, "Vertexes", null, 1) { // todo

    override fun onCreate(db: SQLiteDatabase?) {
        val createTable = "CREATE TABLE $VERTEX_TABLE_NAME ($VERTEX_NAME TEXT PRIMARY KEY, $VERTEX_NEIGHBOURS TEXT)"
        db?.execSQL(createTable)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $VERTEX_TABLE_NAME")
        // todo migrate
        onCreate(db)
    }

    companion object{
        private const val VERTEX_TABLE_NAME = "VERTEXES"
        private const val VERTEX_NAME = "name"
        private const val VERTEX_NEIGHBOURS = "neighbours"
    }
}