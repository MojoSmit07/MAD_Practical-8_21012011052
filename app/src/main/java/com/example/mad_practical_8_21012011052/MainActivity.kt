package com.example.mad_practical_8_21012011052

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.google.android.material.card.MaterialCardView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val card1 = findViewById<MaterialCardView>(R.id.card1)
        val btn1 = findViewById<Button>(R.id.CreateBtn)
        val card2 = findViewById<MaterialCardView>(R.id.card2)
        val btn2 = findViewById<Button>(R.id.CancelBtn)


        card2.visibility = View.GONE

        card1.setOnClickListener(){

        }

        btn1.setOnClickListener(){
            card2.visibility = View.VISIBLE
        }

        btn2.setOnClickListener(){
            card2.visibility = View.GONE
        }


    }

}