package com.example.battlecity

import android.app.Activity
import android.content.Context
import android.view.View
import android.widget.FrameLayout

class GridDrawer (val context: Context){
    private  val alllines = mutableListOf<View>()
    fun  removeGrid(){
        val container = (context as Activity).findViewById<FrameLayout>(R.id.main)
        alllines.forEach{
            container.removeView(it)
        }
    }
    fun drawGrid() {
        val container = (context as Activity).findViewById<FrameLayout>(R.id.main)
        drawHorizontalLines(container)
        drawVerticalLines(container)
    }

    private fun drawHorizontalLines(container: FrameLayout?) {
        var topMargin = 0
        while (topMargin <= container!!.layoutParams.height){
            val horisontalLine=View(context)
            val layoutParams=FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT,1)
            topMargin+= CELL_SIZE
            layoutParams.topMargin=topMargin
            horisontalLine.layoutParams=layoutParams
            horisontalLine.setBackgroundColor(context.resources.getColor(android.R.color.white))
            alllines.add(horisontalLine)
            container.addView(horisontalLine)
        }

    }

    private fun drawVerticalLines(container: FrameLayout?) {
        var leftMargin = 0
        while (leftMargin <= container!!.layoutParams.width){
            val verticalLine=View(context)
            val layoutParams=FrameLayout.LayoutParams(1,FrameLayout.LayoutParams.MATCH_PARENT)
            leftMargin+= CELL_SIZE
            layoutParams.leftMargin=leftMargin
            verticalLine.layoutParams=layoutParams
            verticalLine.setBackgroundColor(context.resources.getColor(android.R.color.white))
            alllines.add(verticalLine)
            container.addView(verticalLine)
        }
    }
}