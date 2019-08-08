package com.example.graphonandroid.presenters

import com.example.graphonandroid.contracts.CheckBoxListener
import com.example.graphonandroid.contracts.ChooseNeighboursDialogContract
import com.example.graphonandroid.data.GraphModel

class ChooseNeighboursDialogPresenter(private val graphModel: GraphModel): CheckBoxListener {

    private lateinit var dialogView: ChooseNeighboursDialogContract.DialogView
    private var currentPosition = 0

    fun attachView(currentView: ChooseNeighboursDialogContract.DialogView){
        dialogView = currentView
    }

    override fun neighbourCheckBoxChecked(currentName:String) {
        graphModel.addNeighbour(currentPosition, currentName)
    }

    override fun neighbourCheckBoxUnchecked(currentName: String) {
        graphModel.removeNeighbour(currentPosition, currentName)
    }

    fun dialogOpenedForThisPosition(position: Int){
        currentPosition = position
        dialogView.showNeighboursForThisVertex(graphModel.getItems())
    }
}