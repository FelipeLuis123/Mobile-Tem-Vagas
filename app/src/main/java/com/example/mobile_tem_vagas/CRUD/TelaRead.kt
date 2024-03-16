package com.example.mobile_tem_vagas.CRUD

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_tem_vagas.Telaperfil
import com.example.mobile_tem_vagas.databinding.ActivityTelareadBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TelaRead : AppCompatActivity() {

    private lateinit var binding: ActivityTelareadBinding
    private lateinit var databaseReference: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelareadBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Botaovoltar.setOnClickListener {
            val intent = Intent(this, Telaperfil::class.java)
            startActivity(intent)
            finish()
        }

        binding.buttonViewRecommendation.setOnClickListener {
            val campoPesquisa: String = binding.campoPesquisa.text.toString()
            if (campoPesquisa.isNotEmpty()){
                readData(campoPesquisa)
            }else {
                Toast.makeText(this, "Por favor entre com o seu telefone!", Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun readData(telefone: String) {
        databaseReference = FirebaseDatabase.getInstance().getReference("Lista de Imoveis")
        databaseReference.child(telefone).get().addOnSuccessListener {
            if (it.exists()) {
                val nomeProprietario = it.child("nomeProprietario").value
                val endereco = it.child("endereco").value
                val numCasa = it.child("numeroCasa").value
                val preco = it.child("precoImovel").value
                val comodos = it.child("comodos").value
                Toast.makeText(this, "Pesquisa Encontrada", Toast.LENGTH_SHORT).show()
                binding.campoPesquisa.text?.clear()
                binding.lerNome.text = nomeProprietario.toString()
                binding.lerEndereco.text = endereco.toString()
                binding.lerNumCasa.text = numCasa.toString()
                binding.lerPreco.text = preco.toString()
                binding.lerComodos.text = comodos.toString()
            }else{
                Toast.makeText(this, "Pesquisa não encontrada!", Toast.LENGTH_SHORT).show()
            }
        }.addOnFailureListener{
            Toast.makeText(this, "Algo deu errado!", Toast.LENGTH_SHORT).show()
        }
    }
}