package com.example.graphonandroid.contracts

import com.example.graphonandroid.data.VertexStringData

interface ChooseNeighboursDialogContract {
    interface DialogView{
        fun showNeighboursForThisVertex(items: List<VertexStringData>)
    }

    interface DialogPresenter{
        fun neighbourCheckBoxChecked(currentName:String)
        fun neighbourCheckBoxUnchecked(currentName: String)
    }
}