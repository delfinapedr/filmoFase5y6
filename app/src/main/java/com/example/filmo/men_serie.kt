package com.example.filmo

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.SearchView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.filmo.controller.SerieAdapter
import com.example.filmo.data.Serie

class men_serie : AppCompatActivity() {

    private lateinit var serieAdapter: SerieAdapter
    private lateinit var baseDatosSeries: BaseDatosSeries
    private lateinit var allSeriesFromDatabase: List<Serie>

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
        setContentView(R.layout.activity_men_serie)

        baseDatosSeries = BaseDatosSeries(this)

        val botonHome = findViewById<Button>(R.id.botonSeries)
        botonHome.setOnClickListener {
            irAMenuPrincipal(it)
        }

        val botonFavoritos = findViewById<Button>(R.id.botonFavoritos)
        botonFavoritos.setOnClickListener {
            irAFavoritos(it)
        }

        val recyclerViewGenero = findViewById<RecyclerView>(R.id.recyclerView2)

        // Fetch all series data from the database
        allSeriesFromDatabase = baseDatosSeries.obtenerTodasLasSeries()
        Log.d("men_serie", "Number of series fetched: ${allSeriesFromDatabase.size}")

        // Initialize and set up the adapter with the fetched data
        serieAdapter = SerieAdapter(allSeriesFromDatabase)
        recyclerViewGenero.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerViewGenero.adapter = serieAdapter

        serieAdapter.onItemClick = { serie ->
            // Handle item click here
            Toast.makeText(this, "Clicked on ${serie.titulo}", Toast.LENGTH_SHORT).show()
        }

        val searchView = findViewById<SearchView>(R.id.BuscadorSeries)

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                // No necesitas manejar la acción de submit en este caso
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                // Aquí filtrarás tus datos basados en el texto de búsqueda y actualizarás el RecyclerView
                val filteredSeries = allSeriesFromDatabase.filter { serie ->
                    serie.titulo.contains(newText.orEmpty(), true) // Filtrar por título de la serie
                }
                serieAdapter.updateData(filteredSeries)
                serieAdapter.notifyDataSetChanged()
                return true
            }
        })
    }
}
