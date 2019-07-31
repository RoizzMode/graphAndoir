package com.example.graphonandroid.presenters

import com.example.graphonandroid.contracts.ChooseNeighboursDialogContract
import com.example.graphonandroid.data.GraphModel

class ChooseNeighboursDialogPresenter(private val graphModel: GraphModel): ChooseNeighboursDialogContract.DialogPresenter {

    private lateinit var dialogView: ChooseNeighboursDialogContract.DialogView

    fun attachView(currentView: ChooseNeighboursDialogContract.DialogView){
        dialogView = currentView
    }

    override fun neighbourCheckBoxChecked(currentName:String, currentPosition: Int) {
        graphModel.addNeighbour(currentPosition, currentName)
    }

    override fun neighbourCheckBoxUnchecked(currentName: String, currentPosition: Int) {
        graphModel.removeNeighbour(currentPosition, currentName)
    }

    fun dialogOpened(){
        dialogView.showNeighboursForThisVertex(graphModel.getItems())
    }
}