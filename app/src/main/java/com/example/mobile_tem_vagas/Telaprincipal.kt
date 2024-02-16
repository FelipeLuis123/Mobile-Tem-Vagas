package com.example.mobile_tem_vagas

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import android.util.Log

class Telaprincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telaprincipal)

        window.statusBarColor = Color.parseColor("#5B00FF")

        // Obter referência para o botão btAnuncio
        val btAnuncio: AppCompatButton = findViewById(R.id.btAnuncio)

        // Configurar o OnClickListener para o botão btAnuncio
        btAnuncio.setOnClickListener {
            abrirCadastroRecomendacao()
        }
    }

    // Método a ser chamado quando o botão btAnuncio for clicado
    private fun abrirCadastroRecomendacao() {
        val intent = Intent(this, Cadastrorecomendacao::class.java)
        startActivity(intent)
        Log.d("Telaprincipal", "Abrindo Cadastrorecomendacao")
    }
}
