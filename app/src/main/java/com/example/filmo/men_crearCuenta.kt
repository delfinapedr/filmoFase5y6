package com.example.filmo

import android.content.ContentValues
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class men_crearCuenta : AppCompatActivity() {
    fun irAMenuPrincipal(view: View) {
        val intent = Intent(this, men_principal::class.java)
        startActivity(intent)
    }
    fun irAMenPerfil(view: View) {
        val intent = Intent(this, men_perfil::class.java)
        startActivity(intent)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_men_crear_cuenta)

        val usuario = findViewById<EditText>(R.id.editTextName)
        val mail = findViewById<EditText>(R.id.editTextEmail)
        val pass = findViewById<EditText>(R.id.editTextPassword)
        val btnAgregarUsuario = findViewById<Button>(R.id.buttonSignUp)

        btnAgregarUsuario.setOnClickListener {
            val nombreUsuario = usuario.text.toString()
            val correo = mail.text.toString()
            val contraseña = pass.text.toString()

            if (nombreUsuario.isNotEmpty() && correo.isNotEmpty() && contraseña.isNotEmpty()) {
                // Verificar si el usuario ya existe en la base de datos
                if (!usuarioExiste(nombreUsuario)) {
                    Toast.makeText(
                        this,
                        "¡El usuario se ha agregado correctamente!",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Resto del código para agregar el usuario a la base de datos
                    val admin = BaseDatosUser(this)
                    val bd = admin.writableDatabase

                    val reg = ContentValues()

                    reg.put("EMAIL", correo)
                    reg.put("PASSWORD", contraseña)

                    bd.insert("Usuarios", null, reg)
                    bd.close()

                    usuario.setText("")
                    mail.setText("")
                    pass.setText("")

                    val intent = Intent(this, men_crearCuenta::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(
                        this,
                        "¡Este usuario ya existe!",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            } else {
                Toast.makeText(
                    this,
                    "¡Por favor, completa todos los campos!",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun usuarioExiste(nombreUsuario: String): Boolean {
        val admin = BaseDatosUser(this)
        val bd = admin.readableDatabase

        val cursor = bd.rawQuery("SELECT * FROM Usuarios WHERE NOMBRE_USUARIO = ?", arrayOf(nombreUsuario))

        val existe = cursor.count > 0

        cursor.close()
        bd.close()

        return existe
    }
}
