package com.example.graphonandroid.fragments

import android.content.res.Configuration
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graphonandroid.R
import com.example.graphonandroid.adapters.VertexAdapter
import com.example.graphonandroid.contracts.VertexContract
import com.example.graphonandroid.data.DataApplication
import com.example.graphonandroid.data.VertexStringData
import com.example.graphonandroid.dialogs.AddVertexDialog
import com.example.graphonandroid.dialogs.ChooseNeighboursDialog
import com.example.graphonandroid.presenters.MainPresenter
import com.example.graphonandroid.routers.MainFragmentRouter
import kotlinx.android.synthetic.main.main_fragment.*

class MainFragment : Fragment(), VertexContract.VertexView {

    private lateinit var presenter: MainPresenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        presenter = MainPresenter((activity?.application as DataApplication).graphModel)
        if (activity == null)
            throw NullPointerException()
        presenter.attachView(this)
        initAdapterAndButtons()
        applicationStarted()
    }

    fun clearAll() {
        presenter.clearAllClicked()
    }

    private fun initAdapterAndButtons() {
        initButtonForAddingVertexes()
        if ((resources.configuration.screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK) < Configuration.SCREENLAYOUT_SIZE_LARGE) {
            initGoToCalculationsButton()
        } else {
            go_to_operations.visibility = View.GONE
        }
        listOfVertex.layoutManager = LinearLayoutManager(activity)
        presenter.viewCreated()
    }

    private fun createAdapter(items: List<VertexStringData>): VertexAdapter {
        val adapter = VertexAdapter(items, presenter)
        return (adapter)
    }

    private fun initGoToCalculationsButton() {
        val mainFragmentRouter = MainFragmentRouter(this)
        go_to_operations.setOnClickListener {
            mainFragmentRouter.goToNextScreen()
        }
    }

    private fun initButtonForAddingVertexes() {
        add_button.setOnClickListener {
            presenter.addVertexButtonClicked()
        }
    }

    override fun showItemsNames(items: List<VertexStringData>) {
        val adapter = createAdapter(items)
        listOfVertex.adapter = adapter
    }

    private fun applicationStarted(){
        presenter.fragmentStarted()
    }

    override fun showNameTakenMessage() {
        Toast.makeText(activity, (getText(R.string.name_used).toString()), Toast.LENGTH_SHORT).show()
    }

    override fun letEnterNewVertex() {
        val addVertexDialog = AddVertexDialog()
        addVertexDialog.show(activity?.supportFragmentManager ?: throw NullPointerException(), "ADD_VERTEX_DIALOG")
    }

    override fun showNeighboursForThisVertex(position: Int, items: List<VertexStringData>) {
        arguments = Bundle().apply{putInt("position", position)}
        val chooseNeighboursDialog = ChooseNeighboursDialog()
        chooseNeighboursDialog.arguments = arguments
        chooseNeighboursDialog.show(activity?.supportFragmentManager ?: throw NullPointerException(), "CHOOSE_NEIGHBOURS_DIALOG")
    }
}