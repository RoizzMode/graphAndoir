package com.example.graphonandroid.adapters

import android.text.Editable
import android.text.TextWatcher
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.graphonandroid.R
import com.example.graphonandroid.contracts.ListContract

class VertexAdapter(private val items: List<String>, private val list: ListContract) : RecyclerView.Adapter<VertexAdapter.VertexViewHolder>() {

    class VertexViewHolder(vertexView: View) : RecyclerView.ViewHolder(vertexView) {
        val name: TextView = vertexView.findViewById(R.id.name_of_vertex)
        val nameOfNeighbourEditText: EditText = vertexView.findViewById(R.id.name_of_neighbour)
        val addNeighbourButton: Button = vertexView.findViewById(R.id.add_neighbour)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VertexViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.vertex_list, parent, false)
        return VertexViewHolder(view)
    }

    override fun onBindViewHolder(holder: VertexViewHolder, position: Int) {
        holder.name.text = items[position]
        initEditTextAddNeighbour(holder)
        initButtonForAddingNeighbours(holder, position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun initEditTextAddNeighbour(holder: VertexViewHolder) {
        holder.nameOfNeighbourEditText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                list.neighbourNameEntered(holder.nameOfNeighbourEditText.text.toString())
            }
        })
    }

    private fun initButtonForAddingNeighbours(holder: VertexViewHolder, position: Int) {
        holder.addNeighbourButton.setOnClickListener{
            list.addNeighbourButtonClicked(position)
        }
    }
}