package com.example.filmo

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity


class men_principal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_men_principal)

        // Asignar valores a las vistas
        val logo = findViewById<ImageView>(R.id.logo)
        logo.setImageResource(R.drawable.logo)

        val botonHome = findViewById<Button>(R.id.botonHome)
        botonHome.setOnClickListener {
            irAMainActivity()
        }

        val botonListas = findViewById<Button>(R.id.botonListas)
        botonListas.setOnClickListener {
            irAFavoritos()
        }
        val botonPelis = findViewById<Button>(R.id.botonPelis)
        botonPelis.setOnClickListener {
            irAMenPelis()
        }

        val botonSeries = findViewById<Button>(R.id.botonSeries)
        botonSeries.setOnClickListener {
            irAMenSeries()
        }

        val botonModPerfil = findViewById<ImageView>(R.id.logoUsuario)
        botonModPerfil.setOnClickListener {
            irAModPerfil()
        }

        val botonFavoritos = findViewById<Button>(R.id.botonFavoritos)
        botonFavoritos.setOnClickListener {
            irAFavoritos()
        }
    }

    private fun irAMenPelis() {
        val intent = Intent(this, men_pelis::class.java)
        startActivity(intent)
    }

    private fun irAMainActivity() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun irAMenSeries() {
        val intent = Intent(this, men_serie::class.java)
        startActivity(intent)
    }

    private fun irAModPerfil() {
        val intent = Intent(this, menu_modificar_perfil::class.java)
        startActivity(intent)
    }

    private fun irAFavoritos() {
        val intent = Intent(this, FavoritosActivity::class.java)
        startActivity(intent)
    }
}
