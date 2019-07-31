package com.example.graphonandroid.presenters

import com.example.graphonandroid.data.GraphModel
import com.example.graphonandroid.dialogs.ChooseItemToStartDialog

class ChooseItemToStartDialogPresenter(private val graphModel: GraphModel) {

    private lateinit var dialogView: ChooseItemToStartDialog

    fun attachView(currentView: ChooseItemToStartDialog){
        dialogView = currentView
    }
    fun dialogOpened(){
        dialogView.showItems(graphModel.getNames())
    }

    fun itemClicked(position: Int) {
        graphModel.calculateFirstDepthSearch(position)
    }
}