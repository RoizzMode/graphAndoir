package com.example.graphonandroid

import android.content.Context
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView

class VertexAdapter(private var vertexes: ArrayList<Vertex>, private val context:Context) : RecyclerView.Adapter<VertexAdapter.VertexViewHolder>() {

    class VertexViewHolder(vertexView: View) : RecyclerView.ViewHolder(vertexView) {
        val name: TextView = vertexView.findViewById(R.id.name_of_vertex)
        private val nameOfNeighbourEditText = vertexView.findViewById<EditText>(R.id.name_of_neighbour)
        private val addNeighbourButton = vertexView.findViewById<Button>(R.id.add_neighbour)

        fun buttonForAddingNeighbours(vertex: Vertex, vertexes:ArrayList<Vertex>, context: Context) {
            addNeighbourButton.setOnClickListener {
                for (i in 0..(vertexes.lastIndex)) {
                    if (nameOfNeighbourEditText.text.toString() == vertexes[i].name) {
                        vertex.neighbours.add(vertexes[i])
                        Toast.makeText(context, "Added", Toast.LENGTH_SHORT).show()
                        return@setOnClickListener
                    }
                }
                Toast.makeText(context, "Such vertex doesn't exist", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VertexViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vertex_list, parent, false)
        return VertexViewHolder(view)
    }

    override fun onBindViewHolder(holder: VertexViewHolder, position: Int) {
        holder.name.text = vertexes[position].name
        holder.buttonForAddingNeighbours(vertexes[position], vertexes, context)
    }

    override fun getItemCount(): Int {
        return vertexes.size
    }
}