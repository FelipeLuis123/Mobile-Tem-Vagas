package com.example.mobile_tem_vagas

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_tem_vagas.databinding.ActivityTelacadastroBinding
import com.google.android.material.snackbar.Snackbar
import android.content.Intent
import android.view.View

class Telacadastro : AppCompatActivity() {
    // Declare uma propriedade para a instância do binding
    private lateinit var binding: ActivityTelacadastroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar o layout usando o View Binding
        binding = ActivityTelacadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurações da sua activity...
        window.statusBarColor = Color.parseColor("#5B00FF")

        binding.btCadastrar.setOnClickListener {
            val nome = binding.editNome.text.toString()
            val senha = binding.editSenha.text.toString()
            val usuario = binding.editUsuario.text.toString()
            val telefone = binding.editTelefone.text.toString()
            val email = binding.editEmail.text.toString()
            val matricula = binding.editMatricula.text.toString()

            when {
                nome.isEmpty() -> binding.editNome.error = "Preencha o nome completo!"
                senha.isEmpty() -> binding.editSenha.error = "Preencha a Senha!"
                senha.length <= 5 -> {
                    exibirSnackbar("A senha precisa ter pelo menos 6 caracteres!")
                }
                usuario.isEmpty() -> binding.editUsuario.error = "Preencha seu usuário!"
                telefone.isEmpty() -> binding.editTelefone.error = "Preencha o telefone!"
                email.isEmpty() -> binding.editEmail.error = "Preencha o email!"
                matricula.isEmpty() -> binding.editMatricula.error = "Preencha a matrícula!"
                matricula.length <= 9 -> {
                    exibirSnackbar("A Matricula precisa ter pelo menos 10 caracteres!")
                }
                else -> {
                    val intent = Intent(this, Telaprincipal::class.java)
                    startActivity(intent)
                }
            }
        }
        binding.btVoltar.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    private fun exibirSnackbar(mensagem: String) {
        Snackbar.make(binding.root, mensagem, Snackbar.LENGTH_SHORT).show()
    }

    private fun navegarTelaPrincipal() {
        val telaprincipal = Intent(this, Telaprincipal::class.java)
        startActivity(telaprincipal)
        finish()
    }
}