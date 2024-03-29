package com.example.mobile_tem_vagas.CRUD

import android.annotation.SuppressLint
import android.content.ActivityNotFoundException
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.os.Build
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.mobile_tem_vagas.R
import com.example.mobile_tem_vagas.Telaprincipal
import com.example.mobile_tem_vagas.databinding.ActivityCadastrorecomendacaoBinding
import com.example.mobile_tem_vagas.databinding.ActivityPerfilBinding
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase


class Cadastrorecomendacao : AppCompatActivity() {

    private lateinit var binding: ActivityCadastrorecomendacaoBinding
    private lateinit var databaseReference: DatabaseReference

    lateinit var imageView: ImageView
    lateinit var button: Button
    val REQUEST_IMAGE_CAPTURE = 100
    private val CAMERA_PERMISSION_REQUEST_CODE = 101

    @SuppressLint("SuspiciousIndentation")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCadastrorecomendacaoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        imageView = findViewById(R.id.image_save)
        button = findViewById(R.id.btCamera)

        button.setOnClickListener {
            // Verificar permissões antes de iniciar a câmera
            if (checkCameraPermission()) {
                startCamera()
            } else {
                requestCameraPermission()
            }
        }
        binding.btCadastrar.setOnClickListener {
            val nomeProprietario = binding.editNomeProprietario.text.toString()
            val telefone = binding.editTelefoneProprietario.text.toString()
            val endereco = binding.editEndereco.text.toString()
            val numeroCasa = binding.editNumerocasa.text.toString()
            val precoImovel = binding.editPreco.text.toString()
            val comodos = binding.editComodos.text.toString()

            databaseReference = FirebaseDatabase.getInstance().getReference("Lista de Imoveis")
            val ImovelData = ImovelData(nomeProprietario, telefone, endereco, numeroCasa, precoImovel, comodos)
            databaseReference.child(telefone).setValue(ImovelData).addOnSuccessListener {
                binding.editNomeProprietario.text?.clear()
                binding.editEndereco.text?.clear()
                binding.editNomeProprietario.text?.clear()
                binding.editNomeProprietario.text?.clear()
                binding.editNomeProprietario.text?.clear()
                binding.editComodos.text?.clear()

                Toast.makeText(this, "Salvo com sucesso!", Toast.LENGTH_SHORT).show()
                val intent = Intent(this, Telaprincipal::class.java)
                startActivity(intent)
                finish()
            }.addOnFailureListener{
                Toast.makeText(this, "Erro ao cadastrar recomendação", Toast.LENGTH_SHORT).show()
            }
        }

    }

    private fun startCamera() {
        val takePictureIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        try {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(this, "Error: " + e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }
    private fun checkCameraPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            this,
            android.Manifest.permission.CAMERA
        ) == PackageManager.PERMISSION_GRANTED
    }
    private fun requestCameraPermission() {
        ActivityCompat.requestPermissions(
            this,
            arrayOf(android.Manifest.permission.CAMERA),
            CAMERA_PERMISSION_REQUEST_CODE
        )
    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                startCamera()
            } else {
                Toast.makeText(this, "Permissão de câmera negada", Toast.LENGTH_SHORT).show()
            }
        }
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            val imageBitmap = data?.extras?.get("data") as? Bitmap
            imageView.setImageBitmap(imageBitmap)
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}


