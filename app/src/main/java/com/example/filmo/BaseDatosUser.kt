package com.example.filmo

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper


class BaseDatosUser(context: Context) : SQLiteOpenHelper(context, "bd", null, 1) {

    companion object {
        const val COLUMN_ID = "id"
        const val COLUMN_NOMBRE_USUARIO = "nombre_usuario"
        const val COLUMN_CORREO_ELECTRONICO = "correo_electronico"
        const val COLUMN_CONTRASEÑA = "contraseña"
        const val TABLE_FAVORITOS = "favoritos"
        const val COLUMN_TIPO = "tipo"
        const val COLUMN_TITULO = "titulo"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        // Crear la tabla usuarios
        db?.execSQL("CREATE TABLE usuarios ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NOMBRE_USUARIO TEXT NOT NULL, $COLUMN_CORREO_ELECTRONICO TEXT UNIQUE NOT NULL, $COLUMN_CONTRASEÑA TEXT NOT NULL);")

        // Insertar datos en la tabla
        db?.execSQL("INSERT INTO usuarios ($COLUMN_NOMBRE_USUARIO, $COLUMN_CORREO_ELECTRONICO, $COLUMN_CONTRASEÑA) VALUES ('John Doe', 'johndoe@example.com', '123456');")
        db?.execSQL("INSERT INTO usuarios ($COLUMN_NOMBRE_USUARIO, $COLUMN_CORREO_ELECTRONICO, $COLUMN_CONTRASEÑA) VALUES ('Jane Doe', 'janedoe@example.com', '789012');")
        db?.execSQL("INSERT INTO usuarios ($COLUMN_NOMBRE_USUARIO, $COLUMN_CORREO_ELECTRONICO, $COLUMN_CONTRASEÑA) VALUES ('Peter Parker', 'peterparker@example.com', 'spiderman123');")

        // Crear la tabla de favoritos
        db?.execSQL("CREATE TABLE $TABLE_FAVORITOS ($COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, $COLUMN_NOMBRE_USUARIO TEXT NOT NULL, $COLUMN_TIPO TEXT NOT NULL, $COLUMN_TITULO TEXT NOT NULL);")

        // Insertar películas y series favoritas por defecto
        insertarFavorito(db, "John Doe", "Película", "Doraemon Stand by Me")
        insertarFavorito(db, "Jane Doe", "Serie", "The 100")
        insertarFavorito(db, "Peter Parker", "Serie", "The 100")
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // Implementa acciones específicas al actualizar la base de datos (si es necesario)
    }

    fun verificarCredenciales(email: String, contraseña: String): Boolean {
        val db = this.readableDatabase
        val query = "SELECT * FROM usuarios WHERE $COLUMN_CORREO_ELECTRONICO = ? AND $COLUMN_CONTRASEÑA = ?"
        val cursor = db.rawQuery(query, arrayOf(email, contraseña))
        val count = cursor.count
        cursor.close()
        return count > 0
    }

    fun insertarFavorito(db: SQLiteDatabase?, usuario: String, tipo: String, titulo: String) {
        val values = ContentValues().apply {
            put(COLUMN_NOMBRE_USUARIO, usuario)
            put(COLUMN_TIPO, tipo)
            put(COLUMN_TITULO, titulo)
        }
        db?.insert(TABLE_FAVORITOS, null, values)
    }

    fun obtenerFavoritos(usuario: String, tipo: String): List<String> {
        val favoritos = mutableListOf<String>()
        val db = readableDatabase
        val cursor = db.query(
            TABLE_FAVORITOS,
            arrayOf(COLUMN_TITULO),
            "$COLUMN_NOMBRE_USUARIO = ? AND $COLUMN_TIPO = ?",
            arrayOf(usuario, tipo),
            null,
            null,
            null
        )
        with(cursor) {
            while (moveToNext()) {
                favoritos.add(getString(getColumnIndexOrThrow(COLUMN_TITULO)))
            }
        }
        return favoritos
    }
}
