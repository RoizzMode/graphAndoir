package com.example.graphonandroid.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import androidx.recyclerview.widget.RecyclerView
import com.example.graphonandroid.R
import com.example.graphonandroid.contracts.CheckBoxListener

class NeighbourAdapter(private val items: List<String>, private val neighboursData: List<String>, private val checkboxListener: CheckBoxListener) : RecyclerView.Adapter<NeighbourAdapter.NeighbourViewHolder>() {

    class NeighbourViewHolder(neighbourView: View) : RecyclerView.ViewHolder(neighbourView) {
        val name: CheckBox = neighbourView.findViewById(R.id.neighbour_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NeighbourViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.neighbour_list, parent, false)
        return NeighbourViewHolder(view)
    }

    override fun onBindViewHolder(holder: NeighbourViewHolder, position: Int) {
        holder.name.text = items[position]
        initCheckBoxes(holder, position)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    private fun initCheckBoxes(holder: NeighbourViewHolder, position: Int) {
        if (neighbourAlreadyAdded(items[position]))
            holder.name.isChecked = true
        holder.name.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked)
                checkboxListener.neighbourCheckBoxChecked(items[position])
            else
                checkboxListener.neighbourCheckBoxUnchecked(items[position])
        }
    }

    private fun neighbourAlreadyAdded(currentItem:String):Boolean{
        for (i in 0..neighboursData.lastIndex){
            if (currentItem == neighboursData[i])
               return true
        }
        return false
    }
}