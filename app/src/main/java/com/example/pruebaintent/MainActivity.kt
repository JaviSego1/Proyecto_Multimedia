package com.example.pruebaintent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var btnFirst : Button
    private lateinit var btnSecond : Button
    private lateinit var intent : Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initEvent()

    }

    private fun initEvent() {
        btnFirst = findViewById(R.id.button1)
        btnSecond = findViewById(R.id.button2)

        btnFirst.setOnClickListener{
            intent = Intent(this, ConfActivity::class.java)
                .apply {
                    putExtra("name", "Javier")
                }
            startActivity(intent)
        }


    }
}