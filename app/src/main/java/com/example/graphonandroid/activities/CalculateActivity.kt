package com.example.graphonandroid.activities

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.graphonandroid.data.DataApplication
import com.example.graphonandroid.R
import com.example.graphonandroid.contracts.CalculateContract
import com.example.graphonandroid.presenters.CalculatePresenter

import kotlinx.android.synthetic.main.activity_result.*

class CalculateActivity : AppCompatActivity(), CalculateContract.CalculateView {

    private lateinit var presenter: CalculatePresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        presenter = CalculatePresenter((application as DataApplication).graphModel)
        presenter.attachView(this)
        initButtons()
    }

    private fun initButtons() {
        initButtonForCalculating()
        initButtonForSorting()
        initEditStartVertex()
    }

    private fun initButtonForCalculating() {
        calculate_graph_button.setOnClickListener {
            presenter.calculateButtonClicked()
        }
    }

    private fun initButtonForSorting() {
        sort_button.setOnClickListener {
            presenter.sortButtonClicked()
        }
    }

    private fun initEditStartVertex() {
        name_of_start_vertex.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                presenter.startVertexNameEntered(name_of_start_vertex.text.toString())
            }
        })
    }

    override fun showCalculateResult(result: String) {
        result_text.text = result
    }

    override fun showSortResult(result: String) {
        result_text.text = result
    }

    override fun showNullResult() {
        Toast.makeText(this, getText(R.string.vertex_does_not_exist).toString(), Toast.LENGTH_SHORT).show()
    }
}
