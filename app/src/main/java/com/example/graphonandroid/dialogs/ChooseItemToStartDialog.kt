package com.example.graphonandroid.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graphonandroid.R
import com.example.graphonandroid.adapters.ChooseVertexAdapter
import com.example.graphonandroid.data.DataApplication
import com.example.graphonandroid.presenters.ChooseItemToStartDialogPresenter
import kotlinx.android.synthetic.main.dialog_start_vertex.view.*

class ChooseItemToStartDialog : DialogFragment() {

    private lateinit var presenter: ChooseItemToStartDialogPresenter
    private lateinit var dialogView: View

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_start_vertex, null)
        val dialogBuilder = AlertDialog.Builder(context ?: throw NullPointerException()).setView(dialogView).setTitle(R.string.add_vertex)
        presenter = ChooseItemToStartDialogPresenter((activity?.application as DataApplication).graphModel)
        presenter.attachView(this)
        dialogBuilder.setNegativeButton(android.R.string.cancel) { _,_ -> }
        presenter.dialogOpened()
        return dialogBuilder.create()
    }

    fun showItems(items: List<String>){
        dialogView.start_vertexes.layoutManager = LinearLayoutManager(dialogView.context)
        dialogView.start_vertexes.adapter = ChooseVertexAdapter(items, presenter)
    }
}