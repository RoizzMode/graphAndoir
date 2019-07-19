package com.example.graphonandroid.data

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

class GraphModelTest {

    private val algorithm: AlgorithmDFS = mock()
    private val graphModel = GraphModel(algorithm)

    private val first = Vertex("First")
    private val second = Vertex("Second")
    private val third = Vertex("Third")

    private fun initList(){
        graphModel.listVertex.add(first)
        graphModel.listVertex.add(second)
        graphModel.listVertex.add(third)
    }

    @Test
    fun `addNewNeighbour if pass name of existing vertex should add new neighbour to chosen vertex`() {
        initList()
        val expected = listOf(third).toString()
        graphModel.addNewNeighbour(0, "Third")
        val actual = graphModel.listVertex[0].neighbours.toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `addNewNeighbour if we pass name of not existing vertex should leave neighbours of chosen vertex unchanged`(){
        initList()
        val expected = listOf<Vertex>().toString()
        graphModel.addNewNeighbour(0, "Fourth")
        val actual = graphModel.listVertex[0].neighbours.toString()

        assertEquals(expected, actual)
    }

    @Test
    fun `findStartVertex if we pass name of existing vertex should return this vertex`(){
        initList()
        val expected = second
        val actual = graphModel.findStartVertex("Second", graphModel.listVertex)

        assertEquals(expected, actual)
    }

    @Test
    fun `findStartVertex if we pass name of not existing vertex should return null`(){
        val expected = null
        val actual = graphModel.findStartVertex("Fourth", graphModel.listVertex)

        assertEquals(expected, actual)
    }

    @Test
    fun `calculateDepthFirstSearch if pass name of existing vertex should return calculated string for this vertex`() {
        whenever(algorithm.calculateDepthFirstSearchAndReset(first, arrayListOf(), graphModel.listVertex)).thenReturn(
            arrayListOf(first))

        val expected = arrayListOf(first).toString()
        val actual = graphModel.calculateFirstDepthSearch("First")

        assertEquals(expected, actual)
    }

    @Test
    fun `calculateDepthFirstSearch if pass name of not existing vertex should return bull`(){
        whenever(algorithm.calculateDepthFirstSearchAndReset(first, arrayListOf(), graphModel.listVertex)).thenReturn(
            arrayListOf(first))

        val expected = null
        val actual = graphModel.calculateFirstDepthSearch("Fourth")

        assertEquals(expected, actual)
    }

    @Test
    fun `getNames should return string array of vertex list`() {
        val expected = arrayListOf("First", "Second", "Third")
        val actual = graphModel.getNames()

        assertEquals(expected, actual)
    }
}