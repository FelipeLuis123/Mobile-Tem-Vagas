package com.example.mobile_tem_vagas

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
class Cadastrorecomendacao : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cadastrorecomendacao)


        Log.d("Cadastrorecomendacao", "onCreate() chamado")
//        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#5B00FF")
    }
}

