package com.example.filmo

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class men_perfil : AppCompatActivity() {
    fun irAMenuPrincipal(view: View) {
        val email = findViewById<EditText>(R.id.editTextEmail).text.toString()
        val contraseña = findViewById<EditText>(R.id.editTextPassword).text.toString()

        val baseDatos = BaseDatosUser(this)
        val credencialesValidas = baseDatos.verificarCredenciales(email, contraseña)

        if (credencialesValidas) {
            val intent = Intent(this, men_principal::class.java)
            startActivity(intent)
        } else {
            // Mostrar mensaje de error o hacer algo en caso de credenciales inválidas
            Toast.makeText(this, "Credenciales inválidas", Toast.LENGTH_SHORT).show()
        }
    }
    fun irACrearCuenta(view: View) {
        val intent = Intent(this, men_crearCuenta::class.java)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_men_perfil)

        val botonInicioSesion = findViewById<Button>(R.id.buttonLogin)
        botonInicioSesion.setOnClickListener{
            irAMenuPrincipal(it)
        }
        val botonRegistro = findViewById<Button>(R.id.buttonRegister)
        botonRegistro.setOnClickListener{
            irACrearCuenta(it)
        }
    }

}