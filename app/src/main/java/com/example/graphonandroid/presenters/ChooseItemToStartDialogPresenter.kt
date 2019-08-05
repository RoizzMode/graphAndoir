package com.example.graphonandroid.presenters

import com.example.graphonandroid.contracts.IListItemClickListener
import com.example.graphonandroid.data.GraphModel
import com.example.graphonandroid.dialogs.ChooseItemToStartDialog

class ChooseItemToStartDialogPresenter(private val graphModel: GraphModel) :
    IListItemClickListener {

    private lateinit var dialogView: ChooseItemToStartDialog

    fun attachView(currentView: ChooseItemToStartDialog){
        dialogView = currentView
    }
    fun dialogOpened(){
        dialogView.showItems(graphModel.getNames())
    }

    override fun itemClicked(position: Int) {
        graphModel.startPosition = position
        dialogView.dismiss()
    }
}