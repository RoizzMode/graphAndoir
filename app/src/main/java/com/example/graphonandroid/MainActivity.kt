package com.example.graphonandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listVertex = arrayListOf<Vertex>()
        val adapter = createList(listVertex)
        initButtonForAddingVertexes(listVertex, adapter)
        initButtonForCalculating(listVertex)
    }

    private fun createList(listVertex:ArrayList<Vertex>):VertexAdapter {
        val layoutManage = LinearLayoutManager(this)
        val adapter = VertexAdapter(listVertex, this)
        listOfVertex.adapter = adapter
        listOfVertex.layoutManager = layoutManage
        return(adapter)
    }

    private fun initButtonForCalculating(listVertex: ArrayList<Vertex>) {
        val algorithm = AlgorithmDFS()
        calculate_button.setOnClickListener {
            val chosenVertex:Vertex? = findStartVertex(start_vertex.text.toString(), listVertex)
            if (chosenVertex == null){
                Toast.makeText(this, "Such vertex doesn't exist", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            else (listVertex.size != 0)
                result.text = algorithm.depthFirstSearchAndReset(chosenVertex, arrayListOf(), listVertex).toString()
        }
    }

    private fun initButtonForAddingVertexes(listVertex:ArrayList<Vertex>, adapter:VertexAdapter) {
        add_button.setOnClickListener {
            listVertex.add(Vertex(name_of_new_vertex.text.toString()))
            adapter.notifyDataSetChanged()
        }
    }

    private fun findStartVertex(name:String, listVertex:ArrayList<Vertex>):Vertex?{
        for (i in 0..listVertex.lastIndex){
            if (name == listVertex[i].name)
                return listVertex[i]
        }
        return null
    }
}
