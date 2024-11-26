package com.example.battlecity

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.KeyEvent
import android.view.KeyEvent.KEYCODE_DPAD_DOWN
import android.view.KeyEvent.KEYCODE_DPAD_DOWN_LEFT
import android.view.KeyEvent.KEYCODE_DPAD_LEFT
import android.view.KeyEvent.KEYCODE_DPAD_RIGHT
import android.view.KeyEvent.KEYCODE_DPAD_UP
import android.view.Menu
import android.view.MenuItem
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
const val CELL_SIZE=50
const val VERTICAL_CELL_AMOUNT=28
const val HORISONTAL_CELL_AMOUNT=15
const val VERTICAL_MAX_SIZE= CELL_SIZE* VERTICAL_CELL_AMOUNT
const val HORISONTAL_MAX_SIZE= CELL_SIZE* HORISONTAL_CELL_AMOUNT
class MainActivity : AppCompatActivity() {
private var editMode=false
private val gridDrawer by lazy {
    GridDrawer(this )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        val container=findViewById<FrameLayout>(R.id.main)
        container.layoutParams=FrameLayout.LayoutParams(VERTICAL_MAX_SIZE, HORISONTAL_MAX_SIZE)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.settings,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
       return when(item.itemId) {
           R.id.menu_settings->{
               switchEditMode()
               return true
           }
           else -> super.onOptionsItemSelected(item)
       }
    }
private fun switchEditMode(){
    val materials_container=findViewById<LinearLayout>(R.id.materials_container)
    if (editMode){
        gridDrawer.removeGrid()
        materials_container.visibility= GONE
    } else{
        gridDrawer.drawGrid()
        materials_container.visibility=VISIBLE
    }
    editMode=!editMode
}
    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        when(keyCode){
            KEYCODE_DPAD_UP->move(Direction.UP)
                KEYCODE_DPAD_DOWN->move(Direction.DOWN)
                    KEYCODE_DPAD_LEFT->move(Direction.LEFT)
                        KEYCODE_DPAD_RIGHT->move(Direction.RIGHT)
        }
        return super.onKeyDown(keyCode, event)
    }
    private fun move(direction: Direction){
        val myTank:ImageView=findViewById(R.id.MyTank)
        val layoutParams = myTank.layoutParams as FrameLayout.LayoutParams
when(direction){
    Direction.UP->{
        myTank.rotation=0f

        if(layoutParams.topMargin>0) {
            (myTank.layoutParams as FrameLayout.LayoutParams).topMargin += -CELL_SIZE
        }
    }
    Direction.DOWN->{
        myTank.rotation=180f
        if (layoutParams.topMargin+myTank.height< HORISONTAL_MAX_SIZE) {
            (myTank.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE
        }
    }
    Direction.LEFT->{
        myTank.rotation=90f
        if(layoutParams.leftMargin>0) {
            (myTank.layoutParams as FrameLayout.LayoutParams).topMargin += CELL_SIZE
        }
    }
    Direction.RIGHT->{
        myTank.rotation=270f
        if (layoutParams.leftMargin+myTank.width< VERTICAL_MAX_SIZE) {
            (myTank.layoutParams as FrameLayout.LayoutParams).topMargin -= CELL_SIZE
        }
    }
}
    }
}