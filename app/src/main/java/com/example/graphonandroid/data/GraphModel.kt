package com.example.graphonandroid.data

import com.example.graphonandroid.bases.AppDatabase
import com.example.graphonandroid.bases.NeighbourEntity
import com.example.graphonandroid.bases.VertexEntity
import com.example.graphonandroid.contracts.VertexListener
import com.example.graphonandroid.threads.DbWorkerThread
import java.lang.ref.WeakReference

class GraphModel(private val algorithmDFS: AlgorithmDFS, appDatabase: AppDatabase) {

    private val listVertex = arrayListOf<Vertex>()

    var startPosition = -1
    private val listeners: ArrayList<WeakReference<VertexListener>> = arrayListOf()
    private val vertexDao = appDatabase.vertexDao()
    private val neighbourDao = appDatabase.neighbourDao()
    private val dbWorkerThread = DbWorkerThread("DbThread")

    fun setOnChangeVertexListener(listener: VertexListener?) {
        if (listener != null)
            listeners.add(WeakReference(listener))
    }

    fun removeOnChangeVertexListener(listener: VertexListener) {
        for (i in 0..listeners.lastIndex) {
            if (listeners[i].get() == listener) {
                listeners.removeAt(i)
                return
            }
        }
    }

    fun addNewVertex(name: String) {
        listVertex.add(Vertex(name))

        val vertexName = listVertex[listVertex.lastIndex].name
        val vertexEntity = VertexEntity(listVertex.lastIndex, vertexName)
        val task = Runnable { vertexDao.insert(vertexEntity) }
        dbWorkerThread.postTask(task)

        for (i in 0..listeners.lastIndex) {
            if (listeners[i].get() != null)
                listeners[i].get()?.onDataChanged()
        }
    }

    fun addNeighbour(position: Int, name: String) {
        for (i in 0..listVertex.lastIndex) {
            if (name == listVertex[i].name) {
                listVertex[position].neighbours.add(listVertex[i])
                val neighbourEntity = NeighbourEntity(position, i)
                val task = Runnable {
                    neighbourDao.insert(neighbourEntity)
                }
                dbWorkerThread.postTask(task)
                break
            }
        }
        for (i in 0..listeners.lastIndex) {
            if (listeners[i].get() != null)
                listeners[i].get()?.onDataChanged()
        }
    }

    fun removeNeighbour(position: Int, name: String) {
        var p = 0
        for (i in 0..listVertex[position].neighbours.lastIndex) {
            if (name == listVertex[position].neighbours[i].name) {
                p = i
                break
            }
        }

        listVertex[position].neighbours.removeAt(p)
        val task = Runnable {
            neighbourDao.removeNeighbour(position, findPosition(name))
        }
        dbWorkerThread.postTask(task)

        for (i in 0..listeners.lastIndex) {
            if (listeners[i].get() != null)
                listeners[i].get()?.onDataChanged()
        }
    }

    private fun findPosition(name: String): Int {
        for (i in 0..listVertex.lastIndex) {
            if (name == listVertex[i].name)
                return i
        }
        return 0
    }

    fun clearAll() {
        listVertex.clear()
        deleteAllVertexesInDB()
    }

    fun calculateFirstDepthSearch(): String {
        val algorithm = algorithmDFS
        if (startPosition == -1)
            return "Result"
        val chosenVertex: Vertex = listVertex[startPosition]

        return algorithm.calculateDepthFirstSearchAndReset(chosenVertex, arrayListOf(), listVertex).toString()
    }

//    fun findStartVertex(name: String, listVertex: ArrayList<Vertex>): Vertex? {
//        for (i in 0..listVertex.lastIndex) {
//            if (name == listVertex[i].name)
//                return listVertex[i]
//        }
//        return null
//    }

    fun getNames(): List<String> {
        return getNamesChangeable()
    }

    private fun getNamesChangeable(): ArrayList<String>{
        val listNames = arrayListOf<String>()
        for (i in 0..listVertex.lastIndex) {
            listNames.add(listVertex[i].name)
        }
        return (listNames)
    }

    fun getItems(): List<VertexStringData> {
        val listNames = mutableListOf<VertexStringData>()
        for (i in 0..listVertex.lastIndex) {
            listNames.add(VertexStringData(listVertex[i].name, getListOfNeighbours(listVertex[i])))
        }
        return (listNames)
    }

    private fun getListOfNeighbours(currentVertex: Vertex): List<String> {
        val listOfNeighbours = mutableListOf<String>()
        for (i in 0..currentVertex.neighbours.lastIndex) {
            listOfNeighbours.add(currentVertex.neighbours[i].name)
        }
        return listOfNeighbours
    }

    fun sortVertexes(): List<String> {
        val list = getNamesChangeable()
        list.sortBy { it.length }
        return list
    }

    private fun deleteAllVertexesInDB() {
        val task = Runnable {
            vertexDao.deleteAll()
            neighbourDao.deleteAll()
        }
        dbWorkerThread.postTask(task)
    }

    fun initModel() {
        dbWorkerThread.start()
        val task = Runnable {
            val list = vertexDao.getAllVertexes()
            for (i in 0..list.lastIndex) {
                listVertex.add(Vertex(list[i].name))
            }
            for (i in 0..listVertex.lastIndex){
                getNeighboursFromDB(i)
            }
        }
        dbWorkerThread.postTask(task)

        for (i in 0..listVertex.lastIndex) {
            getNeighboursFromDB(i)
        }
    }

    private fun getNeighboursFromDB(position: Int) {
        val list = neighbourDao.getAllNeighboursForThisVertex(position)
        for (i in 0..list.lastIndex) {
            val neighbourPosition = list[i].finish
            listVertex[position].neighbours.add(listVertex[neighbourPosition])
        }
    }

    fun checkIfNameUsed(name: String): Boolean {
        for (i in 0..listVertex.lastIndex) {
            if (name == listVertex[i].name)
                return true
        }
        return false
    }

    fun getSortedVertexesResult() = sortVertexes().toString()

    fun release(){
        dbWorkerThread.quit()
    }
}