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
import com.example.graphonandroid.adapters.NeighbourAdapter
import com.example.graphonandroid.contracts.ChooseNeighboursDialogContract
import com.example.graphonandroid.data.DataApplication
import com.example.graphonandroid.data.VertexStringData
import com.example.graphonandroid.presenters.ChooseNeighboursDialogPresenter
import kotlinx.android.synthetic.main.dialog_choose_neighbours.view.*

class ChooseNeighboursDialog: DialogFragment(), ChooseNeighboursDialogContract.DialogView{
    private lateinit var presenter: ChooseNeighboursDialogPresenter
    private lateinit var dialogView: View
    private var position = 0

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        dialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_choose_neighbours, null)
        val dialogBuilder = AlertDialog.Builder(context ?: throw NullPointerException()).setView(dialogView).setTitle(R.string.add_vertex)
        presenter = ChooseNeighboursDialogPresenter((activity?.application as DataApplication).graphModel)
        presenter.attachView(this)
        position = arguments?.getInt("position") ?: throw NullPointerException()
        dialogBuilder.setPositiveButton(android.R.string.ok) { _,_ ->
        }
        dialogBuilder.setNegativeButton(android.R.string.cancel) { _,_ -> }
        presenter.dialogOpened()
        return dialogBuilder.create()
    }

    override fun showNeighboursForThisVertex(items: List<VertexStringData>){
        dialogView.list_of_neighbours.layoutManager = LinearLayoutManager(dialogView.context)
        dialogView.list_of_neighbours.adapter = NeighbourAdapter(getListOfNames(items), items[position].neighbours, position, presenter)
    }

    private fun getListOfNames(items: List<VertexStringData>): List<String>{
        val listOfNames = mutableListOf<String>()
        for (i in 0..items.lastIndex){
            listOfNames.add(items[i].name)
        }
        return listOfNames
    }
}