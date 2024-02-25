// En el archivo SerieAdapter.kt

package com.example.filmo

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class SerieAdapter(var series: List<Serie>) : RecyclerView.Adapter<SerieAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Define los elementos de la vista aquí
        val tituloTextView: TextView = itemView.findViewById(R.id.tituloTextView)
        val generoTextView: TextView = itemView.findViewById(R.id.generoTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_serie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Actualiza la vista con los datos de la serie en la posición actual
        val serie = series[position]
        holder.tituloTextView.text = serie.titulo
        holder.generoTextView.text = serie.genero
    }

    override fun getItemCount(): Int {
        return series.size
    }
}
