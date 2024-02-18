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
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class Telacadastro : AppCompatActivity() {
    private lateinit var binding: ActivityTelacadastroBinding

    private val CHANNEL_ID = "TemVagasChannel"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Inflar o layout usando o View Binding
        binding = ActivityTelacadastroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Configurações da sua activity...
        window.statusBarColor = Color.parseColor("#5B00FF")

        // Criar o canal de notificação
        createNotificationChannel()

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
                    showNotification()
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
    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(CHANNEL_ID, "Primeiro canal",
                NotificationManager.IMPORTANCE_DEFAULT)
                channel.description = "Testando meu primeiro canal"

            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
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
            // notificationId é um número único para cada notificação que você deve definir
            if (ActivityCompat.checkSelfPermission(
                    applicationContext,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                // TODO: Consider calling
                //    ActivityCompat#requestPermissions
                // here to request the missing permissions, and then overriding
                //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                //                                          int[] grantResults)
                // to handle the case where the user grants the permission. See the documentation
                // for ActivityCompat#requestPermissions for more details.
                return
            }
            notify(1, builder.build())
        }
    }
}