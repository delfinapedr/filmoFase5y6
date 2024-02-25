package com.example.filmo

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.util.Log
import com.example.filmo.data.Serie

class BaseDatosSeries(context: Context?) : SQLiteOpenHelper(context, "series.db", null, 1) {

    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_TITULO = "titulo"
        const val COLUMN_GENERO = "genero"
        const val COLUMN_TEMPORADA = "temporada"
        const val COLUMN_EPISODIO = "episodio"
        const val COLUMN_FECHA_ESTRENO = "fecha_estreno"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Crear la tabla series
        db?.execSQL("CREATE TABLE series ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_TITULO TEXT, $COLUMN_GENERO TEXT, $COLUMN_TEMPORADA INTEGER, $COLUMN_EPISODIO INTEGER, $COLUMN_FECHA_ESTRENO TEXT);")

        // Insertar datos en la tabla
        db?.execSQL("INSERT INTO series ($COLUMN_TITULO, $COLUMN_GENERO, $COLUMN_TEMPORADA, $COLUMN_EPISODIO, $COLUMN_FECHA_ESTRENO) VALUES ('Stranger Things', 'Terror', 4, 1, '2022-05-27');")
        db?.execSQL("INSERT INTO series ($COLUMN_TITULO, $COLUMN_GENERO, $COLUMN_TEMPORADA, $COLUMN_EPISODIO, $COLUMN_FECHA_ESTRENO) VALUES ('The Witcher', 'Fantasía', 2, 1, '2021-12-17');")
        db?.execSQL("INSERT INTO series ($COLUMN_TITULO, $COLUMN_GENERO, $COLUMN_TEMPORADA, $COLUMN_EPISODIO, $COLUMN_FECHA_ESTRENO) VALUES ('The Boys', 'Superhéroes', 3, 1, '2022-06-03');")

        // Crear un índice en la columna genero
        db?.execSQL("CREATE INDEX idx_genero ON series ($COLUMN_GENERO);")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Implementa acciones específicas al actualizar la base de datos (si es necesario)
    }

    fun obtenerTodasLasSeries(): List<Serie> {
        val series = mutableListOf<Serie>()
        val db = readableDatabase
        val cursor = db.rawQuery("SELECT * FROM series", null)

        with(cursor) {
            while (moveToNext()) {
                val id = getInt(getColumnIndexOrThrow(COLUMN_ID))
                val titulo = getString(getColumnIndexOrThrow(COLUMN_TITULO))
                val genero = getString(getColumnIndexOrThrow(COLUMN_GENERO))
                val temporada = getInt(getColumnIndexOrThrow(COLUMN_TEMPORADA))
                val episodio = getInt(getColumnIndexOrThrow(COLUMN_EPISODIO))
                val fechaEstreno = getString(getColumnIndexOrThrow(COLUMN_FECHA_ESTRENO))

                series.add(Serie(id, titulo, genero, temporada, episodio, fechaEstreno))
            }
        }

        cursor.close()
        Log.d("BaseDatosSeries", "Number of series retrieved: ${series.size}")
        return series
    }

}