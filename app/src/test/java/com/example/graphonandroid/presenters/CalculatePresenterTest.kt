package com.example.graphonandroid.presenters

import com.example.graphonandroid.contracts.CalculateContract
import com.example.graphonandroid.data.GraphModel
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Before
import org.junit.Test
import org.junit.Assert.*

class CalculatePresenterTest {

    private val graphModel:GraphModel = mock()
    private val presenter = CalculatePresenter(graphModel)
    private val view: CalculateContract.CalculateView = mock()

    @Before
    fun setUp() {
        presenter.attachView(view)
    }

    private fun initStartVertexName(){
        presenter.startVertexNameEntered("First")
    }

    @Test
    fun `startVertexNameEntered should define nameOfStartVertex with passed string`(){
        assertEquals(presenter.nameOfStartVertex, "First")
    }

    @Test
    fun `calculateButtonClicked if pass name of existing vertex should show result string`() {
        whenever(graphModel.calculateFirstDepthSearch("First")).thenReturn(arrayListOf("First", "Second").toString())
        initStartVertexName()

        presenter.calculateButtonClicked()

        verify(graphModel).calculateFirstDepthSearch(presenter.nameOfStartVertex)
        verify(view).showCalculateResult(arrayListOf("First", "Second").toString())
    }

    @Test
    fun `calculateButtonClicked if pass name of not existing vertex should show null result`(){
        val notExisting = "Fourth"
        presenter.startVertexNameEntered(notExisting)

        presenter.calculateButtonClicked()

        verify(graphModel).calculateFirstDepthSearch(presenter.nameOfStartVertex)
        verify(view).showNullResult()
    }

    @Test
    fun `sortButtonClicked should show string result of sorted array`() {
        whenever(graphModel.sortVertexes()).thenReturn(arrayListOf("First", "Second"))

        presenter.sortButtonClicked()

        verify(graphModel).sortVertexes()
        verify(view).showSortResult(arrayListOf("First", "Second").toString())
    }
}