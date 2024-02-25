package com.example.filmo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

import com.example.filmo.controller.FavoritosAdapter
import com.example.filmo.data.SessionManager

class FavoritosActivity : AppCompatActivity() {

    private lateinit var baseDatosUser: BaseDatosUser
    private lateinit var favoritosAdapter: FavoritosAdapter
    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        baseDatosUser = BaseDatosUser(this)
        sessionManager = SessionManager(this)

        // Verificar si el usuario está autenticado
        val usuarioActual = sessionManager.username
        if (usuarioActual == null) {
            // Si no hay usuario autenticado, redirigir al usuario al inicio de sesión
            // Aquí puedes iniciar la actividad de inicio de sesión o hacer lo que sea necesario
            // en tu aplicación para autenticar al usuario.
            // Por ejemplo:
            // startActivity(Intent(this, LoginActivity::class.java))
            // finish() // Termina esta actividad para que el usuario no pueda volver aquí sin iniciar sesión
            return
        }

        // Resto del código para configurar la actividad con el usuario autenticado...
    }
}