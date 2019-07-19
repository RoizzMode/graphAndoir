package com.example.graphonandroid.presenters

import com.example.graphonandroid.contracts.VertexContract
import com.example.graphonandroid.data.GraphModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test
import org.junit.Assert.*
import org.junit.Before

class MainPresenterTest {

    private val graphModel: GraphModel = mock()
    private val presenter = MainPresenter(graphModel)
    private val view: VertexContract.VertexView = mock()

    @Before
    fun setUp() {
        presenter.attachView(view)
    }

    private fun initNames(){
        presenter.vertexNameEntered("First")
        presenter.neighbourNameEntered("Second")
    }

    @Test
    fun `vertexNameEntered should define nameOfNewVertex`(){
        assertEquals(presenter.nameOfNewVertex, "First")
    }

    @Test
    fun `neighbourNameEntered should define nameOfNewNeighbour`(){
        assertEquals(presenter.nameOfNewNeighbour, "Second")
    }

    @Test
    fun `addVertexButtonClicked should call add method from graphModel and receive full list of its names`() {
        whenever(graphModel.getNames()).thenReturn(arrayListOf("First"))
        initNames()

        presenter.addVertexButtonClicked()

        verify(view).showItemsNames(arrayListOf("First"))
    }

    @Test
    fun `addNeighbourButtonClicked if pass name of existing item should receive true and show that neighbour added`() {
        whenever(graphModel.addNewNeighbour(0, "Second")).thenReturn(true)
        initNames()

        presenter.addNeighbourButtonClicked(0)

        verify(graphModel).addNewNeighbour(0, presenter.nameOfNewNeighbour)
        verify(view).showNeighbourAdded()
    }

    @Test
    fun `addNeighbourButtonClicked if pass name of not existing item should receive false and show that neighbour doesn't exist`() {
        whenever(graphModel.addNewNeighbour(0, "Second")).thenReturn(false)
        initNames()

        presenter.addNeighbourButtonClicked(0)

        verify(graphModel).addNewNeighbour(0, presenter.nameOfNewNeighbour)
        verify(view).showNullResult()
    }
}