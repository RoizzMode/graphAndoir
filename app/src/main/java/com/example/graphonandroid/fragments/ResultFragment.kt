package com.example.graphonandroid.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.graphonandroid.R
import com.example.graphonandroid.contracts.CalculateContract
import com.example.graphonandroid.data.DataApplication
import com.example.graphonandroid.dialogs.ChooseItemToStartDialog
import com.example.graphonandroid.presenters.CalculatePresenter
import kotlinx.android.synthetic.main.result_fragment.*
import kotlin.NullPointerException

class ResultFragment : Fragment(), CalculateContract.CalculateView {

    private lateinit var presenter: CalculatePresenter
    private lateinit var chooseItemToStartDialog: ChooseItemToStartDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.result_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = CalculatePresenter((activity?.application as DataApplication).graphModel)
        presenter.attachView(this)
        initButtons()
    }

    private fun initButtons() {
        initButtonForCalculating()
        initButtonForSorting()
    }

    private fun initButtonForCalculating() {
        calculate_graph_button.setOnClickListener {
            presenter.calculateButtonClicked()
        }
    }

    override fun showListOfVertex(list: ArrayList<String>) {
        chooseItemToStartDialog = ChooseItemToStartDialog()
        chooseItemToStartDialog.show(activity?.supportFragmentManager ?: throw NullPointerException(), "CHOOSE_NEIGHBOURS_DIALOG")
    }

    private fun initButtonForSorting() {
        sort_button.setOnClickListener {
            presenter.sortButtonClicked()
        }
    }

    override fun showCalculateResult(result: String) {
        chooseItemToStartDialog.dismiss()
        result_text.text = result
    }

    override fun showSortResult(result: String) {
        result_text.text = result
    }

    override fun cleanResult() {
        result_text.text = ""
    }
}