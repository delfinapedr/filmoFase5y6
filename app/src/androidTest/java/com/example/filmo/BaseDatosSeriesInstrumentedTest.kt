package com.example.filmo

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class BaseDatosSeriesInstrumentedTest {

    private lateinit var baseDatosSeries: BaseDatosSeries

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        baseDatosSeries = BaseDatosSeries(context)
    }

    @Test
    fun testObtenerTodasLasSeries() {
        val series = baseDatosSeries.obtenerTodasLasSeries()
        assertEquals(3, series.size) // Verificar que se recuperan todas las series correctamente
    }
}
