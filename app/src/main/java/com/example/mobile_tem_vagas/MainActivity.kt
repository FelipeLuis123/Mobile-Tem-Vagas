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
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#FFFFFF")

        binding.btEntrar.setOnClickListener{view ->

            val email = binding.editUser.text.toString()
            val senha = binding.editSenha.text.toString()

            if (email.isEmpty() || senha.isEmpty()){
                val snackbar = Snackbar.make(view, "Preencha todos os campos",Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
            }else{
                auth.signInWithEmailAndPassword(email, senha).addOnCompleteListener{autenticacao ->
                    if (autenticacao.isSuccessful){
                        navegarTelaPrincipal()
                    }
                }.addOnFailureListener{
                    val snackbar = Snackbar.make(view, "Erro: Usuário não existe!",Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()
                }
            }
        }
        binding.txtTelaCadastro.setOnClickListener {
            val intent = Intent(this, Telacadastro::class.java)
            startActivity(intent)
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
        val intent = Intent(this, Telaprincipal::class.java)
        startActivity(intent)
        finish()
    }
}