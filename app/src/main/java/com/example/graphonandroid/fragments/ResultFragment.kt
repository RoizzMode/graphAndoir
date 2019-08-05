package com.example.graphonandroid.fragments

import android.content.DialogInterface
import android.content.res.Configuration
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import com.example.graphonandroid.R
import com.example.graphonandroid.contracts.CalculateContract
import com.example.graphonandroid.data.DataApplication
import com.example.graphonandroid.data.VertexStringData
import com.example.graphonandroid.dialogs.ChooseItemToStartDialog
import com.example.graphonandroid.presenters.CalculatePresenter
import com.example.graphonandroid.routers.ResultFragmentRouter
import kotlinx.android.synthetic.main.result_fragment.*

class ResultFragment : Fragment(), CalculateContract.CalculateView, DialogInterface.OnDismissListener {


    private lateinit var presenter: CalculatePresenter
    private lateinit var chooseItemToStartDialog: ChooseItemToStartDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        if (!isLarge())
            setHasOptionsMenu(true)
        return inflater.inflate(R.layout.result_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = CalculatePresenter((activity?.application as DataApplication).graphModel)
        presenter.attachView(this)
        initButtons()
    }


    override fun paintGraph(items: List<VertexStringData>, position: Int){
        graph_view.setData(items, position)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.itemId) {
        R.id.clear_all -> {
            presenter.clearAll()
            if (!isLarge()) {
                val router = ResultFragmentRouter(this)
                router.goToTheFirstScreen()
            }
            true
        }
        else -> {
            super.onOptionsItemSelected(item)
        }
    }

    private fun isLarge(): Boolean { // todo
        if ((resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) > Configuration.SCREENLAYOUT_SIZE_LARGE)
            return true
        return false
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

    override fun onDismiss(p0: DialogInterface?) {
        presenter.calculateWindowClosed()
    }

    override fun showListOfVertex(list: List<String>) {
        chooseItemToStartDialog = ChooseItemToStartDialog()
        chooseItemToStartDialog.show(childFragmentManager, "CHOOSE_NEIGHBOURS_DIALOG")
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