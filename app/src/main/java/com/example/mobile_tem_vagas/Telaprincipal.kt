package com.example.mobile_tem_vagas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class Telaprincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telaprincipal)

//        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#5B00FF")
    }
}