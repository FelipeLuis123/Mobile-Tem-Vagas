package com.example.mobile_tem_vagas.CRUD

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_tem_vagas.Telaperfil
import com.example.mobile_tem_vagas.databinding.ActivityTelaupdateBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class TelaUpdate: AppCompatActivity(){

    private lateinit var binding: ActivityTelaupdateBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTelaupdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Botaovoltar.setOnClickListener {
            val intent = Intent(this, Telaperfil::class.java)
            startActivity(intent)
            finish()
        }

        binding.btAtualizar.setOnClickListener {
            val UpdateNome = binding.UpdateNome.text.toString()
            val UpdateTelefone = binding.UpdateTelefone.text.toString()
            val UpdateEndereco = binding.UpdateEndereco.text.toString()
            val UpdateNumCasa = binding.UpdateNumCasa.text.toString()
            val UpdatePreco = binding.UpdatePreco.text.toString()
            val UpdateComodos = binding.UpdateComodos.text.toString()

            UpdateData(UpdateNome, UpdateTelefone, UpdateEndereco, UpdateNumCasa, UpdatePreco, UpdateComodos)
        }
    }
    private fun UpdateData(nomeProprietario: String, telefone:String,endereco: String,numeroCasa: String,precoImovel: String,comodos: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Lista de Imoveis")
        val ImovelData = mapOf<String, String>("nomeProprietario" to nomeProprietario,"telefone" to telefone,"endereco" to endereco,"numeroCasa" to numeroCasa,"precoImovel" to precoImovel, "comodos" to comodos )
        databaseReference.child(telefone).updateChildren(ImovelData).addOnSuccessListener {
            binding.UpdateNome.text?.clear()
            binding.UpdateTelefone.text?.clear()
            binding.UpdateEndereco.text?.clear()
            binding.UpdateNumCasa.text?.clear()
            binding.UpdatePreco.text?.clear()
            binding.UpdateComodos.text?.clear()
            Toast.makeText(this, "Atualizado com sucesso!", Toast.LENGTH_SHORT).show()
        }.addOnFailureListener {
            Toast.makeText(this, "Erro ao atualizar!", Toast.LENGTH_SHORT).show()

        }
    }
}