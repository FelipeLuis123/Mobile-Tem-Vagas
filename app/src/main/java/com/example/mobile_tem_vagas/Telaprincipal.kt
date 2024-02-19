package com.example.mobile_tem_vagas

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import android.util.Log
import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class Telaprincipal : AppCompatActivity() {
    private lateinit var fusedLocationProviderClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("TAG", "Telaprincipal onCreate")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_telaprincipal)

        val btAnuncio: AppCompatButton = findViewById(R.id.btAnuncio)
        btAnuncio.setOnClickListener {
            abrirCadastroRecomendacao()
        }

        // Configurar o OnClickListener para o novo botão btVerLocalizacao
        val btVerLocalizacao: AppCompatButton = findViewById(R.id.btVerLocalizacao)
        btVerLocalizacao.setOnClickListener {
            verificarPermissoesELocalizacao()
        }

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this)
    }

    // Método a ser chamado quando o botão btAnuncio for clicado
    private fun abrirCadastroRecomendacao() {
        // Seu código para abrir a tela de cadastro de recomendação
        val intent = Intent(this, Cadastrorecomendacao::class.java)
        startActivity(intent)
    }

    // Método para verificar permissões e obter a localização atual
    private fun verificarPermissoesELocalizacao() {
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            obterLocalizacao()
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                LOCATION_PERMISSION_REQUEST_CODE
            )
        }
    }

    // Método para obter a localização atual
    private fun obterLocalizacao() {
        Log.d("TAG", "Entrou em obterLocalizacao")
        if (ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                this,
                android.Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.ACCESS_FINE_LOCATION), 100
            )
            return
        }

        fusedLocationProviderClient.lastLocation
            .addOnSuccessListener { location ->
                if (location != null) {
                    // Chame o método para mostrar o mapa com a localização atual
                    mostrarMapaMinhaLocalizacao(location.latitude, location.longitude)
                }
            }
    }

    private fun mostrarMapaMinhaLocalizacao(latitude: Double, longitude: Double) {
        val uri = Uri.parse("geo:$latitude,$longitude?q=$latitude,$longitude")
        val mapIntent = Intent(Intent.ACTION_VIEW, uri)
        mapIntent.setPackage("com.google.android.apps.maps")
        Log.d("TAG", "Localização obtida com sucesso - Lat: $latitude, Long: $longitude")

        startActivity(mapIntent)
    }

    // Lidar com o resultado da solicitação de permissão
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == LOCATION_PERMISSION_REQUEST_CODE) {
            if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                obterLocalizacao()
            }
        }
    }

    companion object {
        const val LOCATION_PERMISSION_REQUEST_CODE = 1
    }
}
