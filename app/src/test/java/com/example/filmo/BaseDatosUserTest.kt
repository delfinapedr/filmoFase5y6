package com.example.filmo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(AndroidJUnit4::class)
class BaseDatosUserTest {

    @Test
    fun testVerificarCredencialesCorrectas() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val baseDatos = BaseDatosUser(context)
        assertTrue(baseDatos.verificarCredenciales("johndoe@example.com", "123456"))
    }

    @Test
    fun testVerificarCredencialesIncorrectas() {
        val context: Context = ApplicationProvider.getApplicationContext()
        val baseDatos = BaseDatosUser(context)
        assertFalse(baseDatos.verificarCredenciales("johndoe@example.com", "contraseñaincorrecta"))
    }

    @Test
    fun testVerificarCredencialesConMock() {
        val contextMock: Context = mock(Context::class.java)
        val baseDatosMock = BaseDatosUser(contextMock)

        // Mocking behavior of verificarCredenciales() method
        `when`(baseDatosMock.verificarCredenciales("johndoe@example.com", "123456")).thenReturn(true)

        assertTrue(baseDatosMock.verificarCredenciales("johndoe@example.com", "123456"))
    }

    // Additional test cases can be added here
}

