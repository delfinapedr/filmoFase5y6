package com.example.filmo
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var dbPeliculas: BaseDatos
    private lateinit var dbSeries: BaseDatosSeries

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Asignar valores a las vistas
        val logo = findViewById<ImageView>(R.id.logo)
        logo.setImageResource(R.drawable.logo)

        val imagenUsuario1 = findViewById<ImageView>(R.id.imagenUsuario1)
        imagenUsuario1.setImageResource(R.drawable.usuario1)

        val textoNombreUsuario1 = findViewById<TextView>(R.id.textoNombreUsuario1)

        val imagenUsuario2 = findViewById<ImageView>(R.id.imagenUsuario2)
        imagenUsuario2.setImageResource(R.drawable.usuario2)

        val textoNombreUsuario2 = findViewById<TextView>(R.id.textoNombreUsuario2)

        val textoEresNuevo = findViewById<Button>(R.id.buttonRegister1)
        textoEresNuevo.setOnClickListener {
            irACrearCuenta(it)
        }

        val botonIniciarSesion = findViewById<Button>(R.id.buttonLogin1)
        botonIniciarSesion.setOnClickListener {
            irAMenPerfil(it)
        }

        imagenUsuario1.setOnClickListener {
            irAMenuPrincipal(it)
        }
        textoNombreUsuario1.setOnClickListener {
            irAMenuPrincipal(it)
        }
        imagenUsuario2.setOnClickListener {
            irAMenuPrincipal(it)
        }
        textoNombreUsuario2.setOnClickListener {
            irAMenuPrincipal(it)
        }

        // Animar los TextView e ImageView
        animarTextView(textoNombreUsuario1, 1000, -1000f, 0f)
        animarTextView(textoNombreUsuario2, 1000, 1000f, 0f)
        animarImageView(imagenUsuario1, 1000, -1000f, 0f)
        animarImageView(imagenUsuario2, 1000, 1000f, 0f)

        // Obtener la base de datos de películas
        dbPeliculas = BaseDatos(applicationContext)

        // Obtener una lista de las películas de acción más vistas
        val projectionPeliculas = arrayOf("id", "titulo", "genero", "ano", "vistas")
        val selectionPeliculas = "genero = ?"
        val selectionArgsPeliculas = arrayOf("Acción")
        val sortOrderPeliculas = "vistas DESC"

        val cursorPeliculas = dbPeliculas.readableDatabase.query(
            "peliculas",
            projectionPeliculas,
            selectionPeliculas,
            selectionArgsPeliculas,
            null,
            null,
            sortOrderPeliculas
        )

        val columnIndexIdPeliculas = cursorPeliculas.getColumnIndexOrThrow(BaseDatos.COLUMN_ID)
        val columnIndexTituloPeliculas = cursorPeliculas.getColumnIndexOrThrow(BaseDatos.COLUMN_TITULO)
        val columnIndexGeneroPeliculas = cursorPeliculas.getColumnIndexOrThrow(BaseDatos.COLUMN_GENERO)
        val columnIndexAnoPeliculas = cursorPeliculas.getColumnIndexOrThrow(BaseDatos.COLUMN_ANO)
        val columnIndexVistasPeliculas = cursorPeliculas.getColumnIndexOrThrow(BaseDatos.COLUMN_VISTAS)

        while (cursorPeliculas.moveToNext()) {
            val idPeliculas = cursorPeliculas.getInt(columnIndexIdPeliculas)
            val tituloPeliculas = cursorPeliculas.getString(columnIndexTituloPeliculas)
            val generoPeliculas = cursorPeliculas.getString(columnIndexGeneroPeliculas)
            val anoPeliculas = cursorPeliculas.getInt(columnIndexAnoPeliculas)
            val vistasPeliculas = cursorPeliculas.getInt(columnIndexVistasPeliculas)

            println("Peliculas - ID: $idPeliculas, Título: $tituloPeliculas, Género: $generoPeliculas, Año: $anoPeliculas, Vistas: $vistasPeliculas")
        }

        cursorPeliculas.close()

        // Obtener la base de datos de series
        dbSeries = BaseDatosSeries(applicationContext)

        // Obtener una lista de series de terror
        val projectionSeries = arrayOf("id", "titulo", "genero", "temporada", "episodio", "fecha_estreno")
        val selectionSeries = "genero = ?"
        val selectionArgsSeries = arrayOf("Terror")
        val sortOrderSeries = "fecha_estreno DESC"

        val cursorSeries = dbSeries.readableDatabase.query(
            "series",
            projectionSeries,
            selectionSeries,
            selectionArgsSeries,
            null,
            null,
            sortOrderSeries
        )

        val columnIndexIdSeries = cursorSeries.getColumnIndexOrThrow(BaseDatosSeries.COLUMN_ID)
        val columnIndexTituloSeries = cursorSeries.getColumnIndexOrThrow(BaseDatosSeries.COLUMN_TITULO)
        val columnIndexGeneroSeries = cursorSeries.getColumnIndexOrThrow(BaseDatosSeries.COLUMN_GENERO)
        val columnIndexTemporadaSeries = cursorSeries.getColumnIndexOrThrow(BaseDatosSeries.COLUMN_TEMPORADA)
        val columnIndexEpisodioSeries = cursorSeries.getColumnIndexOrThrow(BaseDatosSeries.COLUMN_EPISODIO)
        val columnIndexFechaEstrenoSeries = cursorSeries.getColumnIndexOrThrow(BaseDatosSeries.COLUMN_FECHA_ESTRENO)

        while (cursorSeries.moveToNext()) {
            val idSeries = cursorSeries.getInt(columnIndexIdSeries)
            val tituloSeries = cursorSeries.getString(columnIndexTituloSeries)
            val generoSeries = cursorSeries.getString(columnIndexGeneroSeries)
            val temporadaSeries = cursorSeries.getInt(columnIndexTemporadaSeries)
            val episodioSeries = cursorSeries.getInt(columnIndexEpisodioSeries)
            val fechaEstrenoSeries = cursorSeries.getString(columnIndexFechaEstrenoSeries)

            println("Series - ID: $idSeries, Título: $tituloSeries, Género: $generoSeries, Temporada: $temporadaSeries, Episodio: $episodioSeries, Fecha Estreno: $fechaEstrenoSeries")
        }
        cursorSeries.close()
    }

    private fun irACrearCuenta(view: View) {
        val intent = Intent(this, men_crearCuenta::class.java)
        startActivity(intent)
    }

    private fun irAMenuPrincipal(view: View) {
        val intent = Intent(this, men_principal::class.java)
        startActivity(intent)
    }

    private fun irAMenPerfil(view: View) {
        val intent = Intent(this, men_perfil::class.java)
        startActivity(intent)
    }

    private fun irAModPerfil(view: View) {
        val intent = Intent(this, menu_modificar_perfil::class.java)
        startActivity(intent)
    }

    private fun animarTextView(textView: TextView, duracion: Long, inicioX: Float, finX: Float) {
        val animacion = ObjectAnimator.ofFloat(textView, "translationX", inicioX, finX)
        animacion.duration = duracion
        animacion.start()
    }

    private fun animarImageView(imageView: ImageView, duracion: Long, inicioX: Float, finX: Float) {
        val animacion = ObjectAnimator.ofFloat(imageView, "translationX", inicioX, finX)
        animacion.duration = duracion
        animacion.start()
    }
}