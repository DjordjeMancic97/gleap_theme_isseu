package com.example.idnow_test

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.idnow_test.R

class FirstActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_first)

        findViewById<Button>(R.id.firstActivityButton).setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}