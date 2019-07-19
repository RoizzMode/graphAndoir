package com.example.graphonandroid.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.graphonandroid.data.DataApplication
import com.example.graphonandroid.R
import com.example.graphonandroid.adapters.VertexAdapter
import com.example.graphonandroid.contracts.VertexContract
import com.example.graphonandroid.presenters.MainPresenter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), VertexContract.VertexView {

    private lateinit var presenter: MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = MainPresenter((application as DataApplication).graphModel)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)
        initAdapterAndButtons()
    }

    private fun initAdapterAndButtons() {
        initButtonForAddingVertexes()
        initEditNewVertexName()
        initGoToCalculationsButton()

        listOfVertex.layoutManager = LinearLayoutManager(this)
        listOfVertex.adapter = createAdapter()
    }

    private fun createAdapter(items: List<String> = emptyList()): VertexAdapter {
        val adapter = VertexAdapter(items, presenter)
        return (adapter)
    }

    private fun initEditNewVertexName() {
        name_of_new_vertex.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.vertexNameEntered(name_of_new_vertex.text.toString())
            }
        })
    }

    private fun initGoToCalculationsButton(){
        go_to_operations.setOnClickListener {
            val intent = Intent(this, CalculateActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initButtonForAddingVertexes() {
        add_button.setOnClickListener {
            presenter.addVertexButtonClicked()
        }
    }

    override fun showItemsNames(items: ArrayList<String>) {
        val adapter = createAdapter(items)
        listOfVertex.adapter = adapter
    }

    override fun showNullResult() {
        showToast(getText(R.string.vertex_does_not_exist).toString())
    }

    override fun showNeighbourAdded() {
        showToast(getText(R.string.neighbour_added).toString())
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
