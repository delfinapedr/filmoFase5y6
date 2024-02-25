package com.example.filmo

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button

class menu_modificar_perfil : AppCompatActivity() {
    fun irAMainActivity(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    fun irAMenuPrincipal(view: View) {
        val intent = Intent(this, men_principal::class.java)
        startActivity(intent)
    }
    fun irAFavoritos() {
        val intent = Intent(this, FavoritosActivity::class.java)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_modificar_perfil)

        val botonListas = findViewById<Button>(R.id.myListsButton)
        botonListas.setOnClickListener {
            irAFavoritos()
        }
        val botonCerrarSesion = findViewById<Button>(R.id.logoutButton)
        botonCerrarSesion.setOnClickListener {
            irAMainActivity(it)
        }
        val botonAtras = findViewById<Button>(R.id.backButton)
        botonAtras.setOnClickListener {
            irAMenuPrincipal(it)
        }

    }

}