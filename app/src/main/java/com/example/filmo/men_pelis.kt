package com.example.filmo

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmo.controller.PeliculasAdapter
import com.example.filmo.controller.SerieAdapter
import com.example.filmo.data.Pelicula

class men_pelis : AppCompatActivity() {

    private lateinit var peliculasAdapter: PeliculasAdapter
    private lateinit var baseDatosPelis: BaseDatos
    private lateinit var allPeliculasFromDatabase: List<Pelicula>

    fun irAMenuPrincipal(view: View) {
        val intent = Intent(this, men_principal::class.java)
        startActivity(intent)
    }

    fun irAFavoritos(view: View) {
        val intent = Intent(this, FavoritosActivity::class.java)
        startActivity(intent)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_men_pelis)

        baseDatosPelis = BaseDatos(this)

        val botonHome = findViewById<Button>(R.id.botonHome)
        botonHome.setOnClickListener {
            irAMenuPrincipal(it)
        }

        val botonFavoritos = findViewById<Button>(R.id.botonFavoritos)
        botonFavoritos.setOnClickListener {
            irAFavoritos(it)
        }

        val recyclerViewPelis = findViewById<RecyclerView>(R.id.recyclerViewPelis)

        allPeliculasFromDatabase = baseDatosPelis.obtenerPeliculas()

        peliculasAdapter = PeliculasAdapter(allPeliculasFromDatabase)
        recyclerViewPelis.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewPelis.adapter = peliculasAdapter

        peliculasAdapter.onItemClick = { pelicula ->
            Toast.makeText(this, "Clicked on ${pelicula.titulo}", Toast.LENGTH_SHORT).show()
        }

        val searchView = findViewById<SearchView>(R.id.BuscadorPelis)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                val filteredPeliculas = allPeliculasFromDatabase.filter { pelicula ->
                    pelicula.titulo.contains(newText.orEmpty(), true)
                }
                peliculasAdapter.updateData(filteredPeliculas)
                peliculasAdapter.notifyDataSetChanged()
                return true
            }
        })
    }
}