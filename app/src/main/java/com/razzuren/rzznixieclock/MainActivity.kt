package com.razzuren.rzznixieclock

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class MainActivity : AppCompatActivity() {

    private lateinit var decMin:ImageView
    private lateinit var unitMin:ImageView
    private lateinit var decSec:ImageView
    private lateinit var unitSec:ImageView
    private lateinit var decHour:ImageView
    private lateinit var unitHour:ImageView

    private lateinit var r_decMin:ImageView
    private lateinit var r_unitMin:ImageView
    private lateinit var r_decSec:ImageView
    private lateinit var r_unitSec:ImageView
    private lateinit var r_decHour:ImageView
    private lateinit var r_unitHour:ImageView

    private var sec:Int = 0
    private var min:Int = 0
    private var hour:Int = 0
    private var images: Array<Int> = Array(10){0}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Hide the status bar.
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_FULLSCREEN

        unitSec = findViewById(R.id.unitSec)
        unitMin = findViewById(R.id.unitMin)
        unitHour = findViewById(R.id.unitHour)
        decSec = findViewById(R.id.decSec)
        decMin = findViewById(R.id.decMin)
        decHour = findViewById(R.id.decHour)

        r_unitSec = findViewById(R.id.R_unitSec)
        r_unitMin = findViewById(R.id.R_unitMin)
        r_unitHour = findViewById(R.id.R_unitHour)
        r_decSec = findViewById(R.id.R_decSec)
        r_decMin = findViewById(R.id.R_decMin)
        r_decHour = findViewById(R.id.R_decHour)


        for(i in 0..9){
            images[i]= resources.getIdentifier("_$i","drawable",packageName)
        }

        val mainHandler = Handler(Looper.getMainLooper())

        mainHandler.post(object : Runnable {
            override fun run() {
                updateClock()
                mainHandler.postDelayed(this, 1000)
            }
        })

    }

    private fun updateClock() {

        val date = Date(System.currentTimeMillis())

        sec = date.seconds

        if (date.minutes != min){
            min = date.minutes
            updateMinutes()
        }

        if (date.hours != hour){
            hour = date.hours
            updateHour()
        }

        //clicked(unitSec)
       updateSec()

    }

    private fun updateSec() {
        unitSec.setImageResource(images[sec%10])
        r_unitSec.setImageResource(images[sec%10])
        decSec.setImageResource(images[sec/10])
        r_decSec.setImageResource(images[sec/10])
    }

    private fun updateHour() {
        unitHour.setImageResource(images[hour%10])
        r_unitHour.setImageResource(images[hour%10])
        decHour.setImageResource(images[hour/10])
        r_decHour.setImageResource(images[hour/10])
    }

    private fun updateMinutes() {
        unitMin.setImageResource(images[min%10])
        r_unitMin.setImageResource(images[min%10])
        decMin.setImageResource(images[min/10])
        r_decMin.setImageResource(images[min/10])
    }

    fun clicked(v: View){
        val fadeIn: Animation = AnimationUtils.loadAnimation(v.context, R.anim.fade_in)
        v.startAnimation(fadeIn)
    }
}