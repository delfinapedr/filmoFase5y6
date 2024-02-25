package com.example.filmo

import android.content.Context
import androidx.test.core.app.ApplicationProvider.getApplicationContext
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaseDatosUserInstrumentedTest {
    @Test
    fun testVerificarCredencialesCorrectas() {
        val context: Context = getApplicationContext()
        val baseDatos = BaseDatosUser(context)
        assertTrue(baseDatos.verificarCredenciales("johndoe@example.com", "123456"))
    }

    @Test
    fun testVerificarCredencialesIncorrectas() {
        val context: Context = getApplicationContext()
        val baseDatos = BaseDatosUser(context)
        assertFalse(baseDatos.verificarCredenciales("johndoe@example.com", "contraseñaincorrecta"))
    }
}
