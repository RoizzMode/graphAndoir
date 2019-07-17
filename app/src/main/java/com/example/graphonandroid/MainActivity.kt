package com.example.graphonandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), VertexContract.VertexView {

    private val presenter = GraphPresenter()
    private var listItems1 = arrayListOf<String>()
    private var adapter = createAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter.attachView(this)
        initAdapterAndButtons()
    }

    private fun initAdapterAndButtons() {
        initButtonForAddingVertexes()
        initButtonForCalculating()
        initEditNewVertexName()

        listOfVertex.layoutManager = LinearLayoutManager(this)
        listOfVertex.adapter = adapter
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

    private fun initButtonForCalculating() {
        calculate_button.setOnClickListener {
            presenter.calculateButtonClicked(start_vertex.text.toString())
        }
    }

    private fun initButtonForAddingVertexes() {
        add_button.setOnClickListener {
            presenter.addVertexButtonClicked()
        }
    }

    override fun showItemsNames(items: ArrayList<String>) {
        adapter = createAdapter(items)
        listOfVertex.adapter = adapter
    }

    override fun showNiceResult(resultGraph: String) {
        result.text = resultGraph
    }

    override fun showNullResult(resultGraph: String) {
        showToast(resultGraph)
    }

    override fun showNeighbourAdded() {
        showToast("Neighbour added")
    }

    private fun showToast(text: String) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
    }
}
