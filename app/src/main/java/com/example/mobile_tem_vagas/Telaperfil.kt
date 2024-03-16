package com.example.mobile_tem_vagas
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_tem_vagas.CRUD.TelaDelete
import com.example.mobile_tem_vagas.CRUD.TelaRead
import com.example.mobile_tem_vagas.CRUD.TelaUpdate
import com.example.mobile_tem_vagas.databinding.ActivityPerfilBinding

class Telaperfil : AppCompatActivity() {

private lateinit var binding: ActivityPerfilBinding

        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPerfilBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.buttonViewRecommendation.setOnClickListener         {
        val intent = Intent(this, TelaRead::class.java)
        startActivity(intent)
        finish()
        }

        binding.atualizarRecomendacao.setOnClickListener {
        val intent = Intent(this, TelaUpdate::class.java)
        startActivity(intent)
        finish()
        }

        binding.buttonDeleteRecommendation.setOnClickListener {
        val intent = Intent(this, TelaDelete::class.java)
        startActivity(intent)
        finish()
        }
        binding.Botaovoltar.setOnClickListener {
        val intent = Intent(this, Telaprincipal::class.java)
        startActivity(intent)
        finish()
        }
    }
}
