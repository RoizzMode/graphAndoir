package com.example.graphonandroid.data

import org.junit.Before
import org.junit.Test

import org.junit.Assert.*

class AlgorithmDFSTest {

    private val algorithm = AlgorithmDFS()
    private var actual = arrayListOf<Vertex>()

    private val first = Vertex("First")
    private val second = Vertex("Second")
    private val third = Vertex("Third")
    private val fourth = Vertex("Fourth")
    private val fifth = Vertex("Fifth")

    @Before
    internal fun setUp() {
        first.neighbours.add(second)
        first.neighbours.add(fourth)
        third.neighbours.add(second)
        fourth.neighbours.add(first)
        fourth.neighbours.add(fifth)
        fifth.neighbours.add(fourth)
    }

    @Test
    fun `calculateDepthFirstSearch Simple example`() {
        second.neighbours.add(first)
        second.neighbours.add(third)

        actual = algorithm.calculateDepthFirstSearch(first, actual)
        val expected = arrayListOf(first, second, third, fourth, fifth)
        assertEquals(expected, actual)
    }

    @Test
    fun `calculateDepthFirstSearch Simple example two`() {
        second.neighbours.add(first)
        second.neighbours.add(third)

        actual = algorithm.calculateDepthFirstSearch(second, actual)
        val expected = arrayListOf(second, first, fourth, fifth, third)

        assertEquals(expected, actual)
    }

    @Test
    fun `calculateDepthFirstSearch Graph with one vertex`() {
        val ver = Vertex("Only")

        val expected = arrayListOf(ver)
        assertEquals(algorithm.calculateDepthFirstSearch(ver, arrayListOf()), expected)
    }

    @Test
    fun `calculateDepthFirstSearch Not connected graph`() {
        actual = algorithm.calculateDepthFirstSearch(second, actual)
        val expected = arrayListOf(second)

        assertEquals(expected, actual)
    }

    @Test
    fun `calculateDepthFirstSearch Circle graph`() {
        second.neighbours.add(first)
        second.neighbours.add(third)
        third.neighbours.add(fifth)
        fifth.neighbours.add(third)

        actual = algorithm.calculateDepthFirstSearch(first, actual)
        val expected = arrayListOf(first, second, third, fifth, fourth)

        assertEquals(expected, actual)
    }

    @Test
    fun `calculateDepthFirstSearch Graph with one loop around first`() {
        first.neighbours.add(first)
        second.neighbours.add(first)
        second.neighbours.add(third)

        actual = algorithm.calculateDepthFirstSearch(first, actual)
        val expected = arrayListOf(first, second, third, fourth, fifth)

        assertEquals(expected, actual)
    }

    @Test
    fun `calculateDepthFirstSearch Graph with crossed connections between some vertex`() {
        val sixth = Vertex("Sixth")

        second.neighbours.add(first)
        second.neighbours.add(third)
        third.neighbours.add(fifth)
        fifth.neighbours.add(third)
        fifth.neighbours.add(sixth)


        actual = algorithm.calculateDepthFirstSearch(first, actual)
        val expected = arrayListOf(first, second, third, fifth, fourth, sixth)

        assertEquals(expected, actual)
    }
}