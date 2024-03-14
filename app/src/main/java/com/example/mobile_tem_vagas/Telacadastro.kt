package com.example.mobile_tem_vagas

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.graphics.Color
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.mobile_tem_vagas.databinding.ActivityTelacadastroBinding
import com.google.android.material.snackbar.Snackbar
import android.content.Intent
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.mobile_tem_vagas.TelaLogin.MainActivity
import com.google.firebase.FirebaseNetworkException
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.FirebaseAuthUserCollisionException
import com.google.firebase.auth.FirebaseAuthWeakPasswordException

class Telacadastro : AppCompatActivity() {

    private lateinit var binding: ActivityTelacadastroBinding

    private val auth = FirebaseAuth.getInstance()

    private val CHANNEL_ID = "TemVagasChannel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityTelacadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.statusBarColor = Color.parseColor("#5B00FF")

        createNotificationChannel()

        binding.btCadastrar.setOnClickListener {view ->
            val nome = binding.editNome.text.toString()
            val senha = binding.editSenha.text.toString()
            val usuario = binding.editUsuario.text.toString()
            val telefone = binding.editTelefone.text.toString()
            val email = binding.editEmail.text.toString()
            val matricula = binding.editMatricula.text.toString()

            if (nome.isEmpty() || senha.isEmpty() || usuario.isEmpty() || telefone.isEmpty() || email.isEmpty() || matricula.isEmpty()) {
               val snackbar = Snackbar.make(view, "Preencha todos os campos!", Snackbar.LENGTH_SHORT)
                snackbar.setBackgroundTint(Color.RED)
                snackbar.show()
                } else {
                    auth.createUserWithEmailAndPassword(email, senha).addOnCompleteListener {cadastro ->
                        if (cadastro.isSuccessful){
//                            val snackbar = Snackbar.make(view, "Usuário cadastrado com sucesso!", Snackbar.LENGTH_SHORT)
//                            snackbar.setBackgroundTint(Color.GREEN)
//                            snackbar.show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    }.addOnFailureListener{exception ->
                        val mensagemError = when(exception){
                            is FirebaseAuthWeakPasswordException -> "A senha precisa ter pelo menos 6 caracteres!"
                            is FirebaseAuthInvalidCredentialsException -> "Preencha um email valido!"
                            is FirebaseAuthUserCollisionException -> "Esta conta ja foi cadastrada!"
                            is FirebaseNetworkException -> "sem conexão com a internet!"
                            else -> "Erro ao cadastrar usuário!"
                    }
                        val snackbar = Snackbar.make(view, mensagemError, Snackbar.LENGTH_SHORT)
                        snackbar.setBackgroundTint(Color.RED)
                        snackbar.show()
                }
            }
        }
    }
    // Exibir notificação push
    private fun showNotification() {
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_casa)
            .setContentTitle(getString(R.string.notification_title))
            .setContentText(getString(R.string.notification_text))
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)

        with(NotificationManagerCompat.from(this)) {
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                return
            }
            notify(1, builder.build())
        }
    }
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "Primeiro canal",
                NotificationManager.IMPORTANCE_DEFAULT)
            channel.description = "Testando meu primeiro canal"
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }
}