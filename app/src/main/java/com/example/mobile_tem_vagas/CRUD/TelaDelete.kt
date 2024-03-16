package com.example.mobile_tem_vagas.CRUD

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_tem_vagas.Telaperfil
import com.example.mobile_tem_vagas.databinding.ActivityTeladeleteBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class TelaDelete :AppCompatActivity(){

    private lateinit var binding: ActivityTeladeleteBinding
    private lateinit var databaseReference: DatabaseReference
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTeladeleteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.Botaovoltar.setOnClickListener {
            val intent = Intent(this, Telaperfil::class.java)
            startActivity(intent)
            finish()
        }
        binding.btExcluir.setOnClickListener{
            val telefone = binding.DeleteRecomendacao.text.toString()
            if (telefone.isNotEmpty()){
                deleteData(telefone)
            }else{
                Toast.makeText(this, "Digite seu numero de telefone para excluir uma recomendação!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun deleteData(telefone: String){
        databaseReference = FirebaseDatabase.getInstance().getReference("Lista de Imoveis")
        databaseReference.child(telefone).removeValue().addOnSuccessListener {
            binding.DeleteRecomendacao.text?.clear()
        }.addOnFailureListener {
            Toast.makeText(this, "Recomendação excluida com sucesso!", Toast.LENGTH_SHORT).show()
        }
    }
}