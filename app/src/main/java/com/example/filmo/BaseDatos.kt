package com.example.filmo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import com.example.filmo.data.Pelicula

class BaseDatos(context: Context?) : SQLiteOpenHelper(context, "peliculas.db", null, 1) {

    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_TITULO = "titulo"
        const val COLUMN_GENERO = "genero"
        const val COLUMN_ANO = "ano"
        const val COLUMN_VISTAS = "vistas"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Crear la tabla peliculas
        db?.execSQL("CREATE TABLE peliculas ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TITULO TEXT, $COLUMN_GENERO TEXT, $COLUMN_ANO INTEGER, $COLUMN_VISTAS INTEGER);")

        // Insertar datos en la tabla
        db?.execSQL("INSERT INTO peliculas ($COLUMN_TITULO, $COLUMN_GENERO, $COLUMN_ANO, $COLUMN_VISTAS) VALUES ('Parásitos', 'Drama', 2019, 10000000);")
        db?.execSQL("INSERT INTO peliculas ($COLUMN_TITULO, $COLUMN_GENERO, $COLUMN_ANO, $COLUMN_VISTAS) VALUES ('Spider-Man: No Way Home', 'Acción', 2021, 12000000);")
        db?.execSQL("INSERT INTO peliculas ($COLUMN_TITULO, $COLUMN_GENERO, $COLUMN_ANO, $COLUMN_VISTAS) VALUES ('Top Gun: Maverick', 'Acción', 2022, 15000000);")

        // Crear un índice en la columna genero
        db?.execSQL("CREATE INDEX idx_genero ON peliculas ($COLUMN_GENERO);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Implementa acciones específicas al actualizar la base de datos (si es necesario)
    }

    fun obtenerPeliculas(): List<Pelicula> {
        val peliculas = mutableListOf<Pelicula>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM peliculas ", null)

        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(COLUMN_ID))
                val titulo = getString(getColumnIndexOrThrow(COLUMN_TITULO))
                val genero = getString(getColumnIndexOrThrow(COLUMN_GENERO))
                val ano = getInt(getColumnIndexOrThrow(COLUMN_ANO))
                val vistas = getInt(getColumnIndexOrThrow(COLUMN_VISTAS))

                peliculas.add(Pelicula(id, titulo, genero, ano, vistas))
            }
        }

        cursor.close()
        return peliculas
    }
}

