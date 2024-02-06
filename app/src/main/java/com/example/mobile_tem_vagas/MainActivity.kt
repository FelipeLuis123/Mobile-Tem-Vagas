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


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#FFFFFF")

        binding.btEntrar.setOnClickListener{

            val user = binding.editUser.text.toString()
            val senha = binding.editSenha.text.toString()

            when{
                user.isEmpty() -> {
                    binding.editUser.error="Preencha o Usuário!"
                }
                senha.isEmpty() -> {
                    binding.editSenha.error="Preencha a Senha!"
                }
                senha.length <= 5 -> {
                    val snackbar = Snackbar.make(it, "A senha precisa ter pelo menos 6 caracteres!", Snackbar.LENGTH_SHORT)
                    snackbar.show()
                }
                else ->{
                    login(it)
                }
            }
        }
    }
    private fun login(view :View) {
        val progressbar = binding.progressBar
        progressbar.visibility = View.VISIBLE

        binding.btEntrar.isEnabled = false
        binding.btEntrar.setTextColor(Color.parseColor("#FFFFFF"))

        Handler(Looper.getMainLooper()).postDelayed({
            navegarTelaPrincipal()
            val snackbar = Snackbar.make(view, "Login efetuado com sucesso!", Snackbar.LENGTH_SHORT)
            snackbar.show()
        }, 3000)
    }
    private fun navegarTelaPrincipal(){
        val segundaTela = Intent(this, Telaprincipal::class.java)
        startActivity(segundaTela)
        finish()
    }
}

