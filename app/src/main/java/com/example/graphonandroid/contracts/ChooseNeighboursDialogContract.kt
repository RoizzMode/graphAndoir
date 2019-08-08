package com.example.graphonandroid.contracts

import com.example.graphonandroid.data.VertexStringData

interface ChooseNeighboursDialogContract {
    interface DialogView{
        fun showNeighboursForThisVertex(items: List<VertexStringData>)
    }
}