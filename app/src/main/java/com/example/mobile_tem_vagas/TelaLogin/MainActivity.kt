package com.example.mobile_tem_vagas.TelaLogin


import android.graphics.Color
import android.os.Bundle
import android.os.Looper
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_tem_vagas.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar
import android.os.Handler
import android.content.Intent
import com.example.mobile_tem_vagas.Telacadastro
import com.example.mobile_tem_vagas.Telaprincipal
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException


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
                }.addOnFailureListener{exception ->
                    val mensagemError = when(exception){
                        is FirebaseAuthWeakPasswordException -> "A senha precisa ter pelo menos 6 caracteres!"
                        is FirebaseAuthInvalidCredentialsException -> "Preencha um email valido!"
                        is FirebaseNetworkException -> "sem conexão com a internet!"
                        else -> "Erro ao cadastrar usuário!"
                    }
                    val snackbar = Snackbar.make(view, mensagemError, Snackbar.LENGTH_SHORT)
                    snackbar.setBackgroundTint(Color.RED)
                    snackbar.show()
//                    val snackbar = Snackbar.make(view, "Erro:Verifique se suas credenciais estão corretas!",Snackbar.LENGTH_SHORT)
//                    snackbar.setBackgroundTint(Color.RED)
//                    snackbar.show()
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

    override fun onStart() {
        super.onStart()

        val usuarioAtual = FirebaseAuth.getInstance().currentUser

        if (usuarioAtual != null){
            navegarTelaPrincipal()
        }
    }
}