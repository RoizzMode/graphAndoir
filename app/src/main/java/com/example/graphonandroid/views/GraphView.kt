package com.example.graphonandroid.views

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import com.example.graphonandroid.data.PaintedVertex
import com.example.graphonandroid.data.VertexStringData

class GraphView(context: Context, attributeSet: AttributeSet) : View(context, attributeSet) {

    private var items = listOf<VertexStringData>()
    private var startPosition = 0
    private val paintedItems = arrayListOf<PaintedVertex>()


    private val pointPaint = Paint().apply {
        color = Color.parseColor("#008577")
        strokeWidth = 20f
        style = Paint.Style.STROKE
    }

    private val pointFillPaint = Paint().apply {
        color = Color.parseColor("#008577")
    }

    private val pointLinePaint = Paint().apply {
        color = Color.parseColor("#008577")
        strokeWidth = 10f
        isAntiAlias = true
    }

    private val writePaint = Paint().apply {
        color = Color.BLACK
        textSize = 20f
    }


    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        var x = 50
        var y = 100

        for (i in 0..items.lastIndex) {
            canvas.drawCircle(x.toFloat(), y.toFloat(), 7f, pointPaint)
            canvas.drawCircle(x.toFloat(), y.toFloat(), 7f, pointFillPaint)
            canvas.drawText(items[i].name, x.toFloat() + 20, y.toFloat() + 20, writePaint)
            setPainted(i, x, y)
            if (i % 2 == 0)
                y += 50
            else
                y -= 50
            x += 200
            if ((i + 1) % 5 == 0){
                y += 200
                x = if ((i + 1) % 10 == 0)
                    100
                else
                    200
            }
        }

        for (i in 0..items.lastIndex){
            x = paintedItems[i].x
            y = paintedItems[i].y
            for (j in 0..items[i].neighbours.lastIndex){
                val position = findPositionInList(items[i].neighbours[j])
                canvas.drawLine(x.toFloat(), y.toFloat(), paintedItems[position].x.toFloat(), paintedItems[position].y.toFloat(), pointLinePaint)
            }
        }
    }

    private fun setPainted(position: Int, x: Int, y: Int){
        paintedItems[position].isPainted = true
        paintedItems[position].x = x
        paintedItems[position].y = y
    }

    private fun findPositionInList(name: String):Int{
        for (i in 0..items.lastIndex){
            if (name == items[i].name)
                return i
        }
        return 0
    }

    fun setData(newItems: List<VertexStringData>, position: Int) {
        startPosition = position
        items = newItems
        paintedItems.clear()
        for (i in 0..items.lastIndex) {
            paintedItems.add(PaintedVertex(items[i].name, 0, 0, false))
        }
        invalidate()
    }
}