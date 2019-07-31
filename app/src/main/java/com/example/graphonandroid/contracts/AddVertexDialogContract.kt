package com.example.graphonandroid.contracts

interface AddVertexDialogContract {
    interface DialogView{
        fun showNameTakenMessage()
    }

    interface DialogPresenter{
        fun addVertexConfirmedClicked()
        fun vertexNameEntered(name: String)
    }
}