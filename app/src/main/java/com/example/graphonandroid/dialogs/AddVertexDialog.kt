package com.example.graphonandroid.dialogs

import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.example.graphonandroid.R
import com.example.graphonandroid.contracts.AddVertexDialogContract
import com.example.graphonandroid.data.DataApplication
import com.example.graphonandroid.presenters.AddVertexDialogPresenter
import kotlinx.android.synthetic.main.dialog_add_vertex.view.*

class AddVertexDialog: DialogFragment(), AddVertexDialogContract.DialogView {

    private lateinit var presenter: AddVertexDialogPresenter
    private lateinit var mDialogView: View

    @SuppressLint("InflateParams")
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        mDialogView = LayoutInflater.from(activity).inflate(R.layout.dialog_add_vertex, null)
        val dialogBuilder = AlertDialog.Builder(context ?: throw NullPointerException()).setView(mDialogView).setTitle(R.string.add_vertex)
        presenter = AddVertexDialogPresenter((activity?.application as DataApplication).graphModel)
        presenter.attachView(this)
        initEditNewVertexName(mDialogView)
        dialogBuilder.setPositiveButton(android.R.string.ok) { _,_ ->
            presenter.addVertexConfirmedClicked()
        }
        dialogBuilder.setNegativeButton(android.R.string.cancel) { _,_ -> }
        return dialogBuilder.create()
    }

    private fun initEditNewVertexName(view: View) {
        view.name_of_new_vertex.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.vertexNameEntered(view.name_of_new_vertex.text.toString())
            }
        })
    }

    override fun showNameTakenMessage(){
        Toast.makeText(mDialogView.context, (getText(R.string.name_used).toString()), Toast.LENGTH_SHORT).show()
    }
}