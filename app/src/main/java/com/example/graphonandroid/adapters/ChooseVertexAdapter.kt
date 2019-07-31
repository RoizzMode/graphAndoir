package com.example.graphonandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.graphonandroid.R
import com.example.graphonandroid.presenters.ChooseItemToStartDialogPresenter

class ChooseVertexAdapter(private val items: List<String>, private val presenter: ChooseItemToStartDialogPresenter) : RecyclerView.Adapter<ChooseVertexAdapter.ChooseVertexViewHolder>() {

    class ChooseVertexViewHolder(val chooseVertexView: View) : RecyclerView.ViewHolder(chooseVertexView) {
        val name: TextView = chooseVertexView.findViewById(R.id.name_of_start)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseVertexViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.choose_vertex_list, parent, false)
        return ChooseVertexViewHolder(view)
    }

    override fun onBindViewHolder(holder: ChooseVertexViewHolder, position: Int) {
        holder.name.text = items[position]
        initItemClickListener(holder, position)
    }

    private fun initItemClickListener(holder: ChooseVertexViewHolder, position: Int) {
        holder.chooseVertexView.setOnClickListener {
            presenter.itemClicked(position)
        }
    }

    override fun getItemCount(): Int {
        return items.size
    }
}