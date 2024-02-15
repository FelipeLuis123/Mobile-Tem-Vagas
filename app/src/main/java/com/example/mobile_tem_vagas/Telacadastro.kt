package com.example.mobile_tem_vagas

import android.graphics.Color
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.mobile_tem_vagas.ui.theme.MobileTemVagasTheme
import com.example.mobile_tem_vagas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import android.os.Handler
import android.content.Intent

class Telacadastro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telaprincipal)

//        supportActionBar?.hide()
        window.statusBarColor = Color.parseColor("#5B00FF")

        binding.btEntrar.setOnClickListener{

            val nome = binding.editNome.text.toString()
            val senha = binding.editSenha.text.toString()
            val usuario = binding.editUsuario.text.toString()
            val telefone = binding.editTelefone.text.toString()
            val email = binding.editEmail.text.toString()
            val matricula = binding.editMatricula.text.toString()

            when {
                nome.isEmpty() -> {
                    binding.editNome.error = "Preencha o nome completo!"
                }
                senha.isEmpty() -> {
                    binding.editSenha.error = "Preencha a Senha!"
                }
                senha.length <= 5 -> {
                    val snackbar = Snackbar.make(it, "A senha precisa ter pelo menos 6 caracteres!", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
                usuario.isEmpty() ->{
                    binding.editUsuario.error = "preencha seu usuário!"
                }
                telefone.isEmpty() -> {
                    binding.editTelefone.error = "Preencha o telefone!"
                }
                email.isEmpty() -> {
                    binding.editEmail.error = "Preencha o email!"
                }
                matricula.isEmpty() -> {
                    binding.editMatricula.error = "Preencha a matrícula!"
                }
                matricula.length <= 9 -> {
                    val snackbar = Snackbar.make(it, "A Matricula precisa ter pelo menos 10 caracteres!", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
                else -> {
                    login(it)
                }
            }
        }
    }
    private fun navegarTelaPrincipal(){
        val Telacadastro = Intent(this, Telaprincipal::class.java)
        startActivity(Telacadastro)
        finish()
    }
}