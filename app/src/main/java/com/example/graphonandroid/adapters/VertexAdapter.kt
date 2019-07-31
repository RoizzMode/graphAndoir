package com.example.graphonandroid.adapters

import android.annotation.SuppressLint
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.graphonandroid.R
import com.example.graphonandroid.contracts.ListContract
import com.example.graphonandroid.data.VertexStringData

class VertexAdapter(private val items: List<VertexStringData>, private val list: ListContract) : RecyclerView.Adapter<VertexAdapter.VertexViewHolder>() {

    class VertexViewHolder(val vertexView: View) : RecyclerView.ViewHolder(vertexView) {
        val name: TextView = vertexView.findViewById(R.id.name_of_vertex)
        val neighbours: TextView = vertexView.findViewById(R.id.current_neighbours)
        val addNeighbourButton: Button = vertexView.findViewById(R.id.add_neighbour)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VertexViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vertex_list, parent, false)
        return VertexViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: VertexViewHolder, position: Int) {
        holder.name.text = items[position].name
        holder.neighbours.text = "${holder.vertexView.resources.getText(R.string.current_neighbours)} ${items[position].neighbours}"
        initButtonForAddingNeighbours(holder, position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun initButtonForAddingNeighbours(holder: VertexViewHolder, position: Int) {
        holder.addNeighbourButton.setOnClickListener {
            list.showNeighboursButtonClicked(position)
        }
    }
}