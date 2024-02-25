// En el archivo SerieAdapter.kt

package com.example.filmo.controller

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmo.R
import com.example.filmo.data.Serie

class SerieAdapter(var series: List<Serie>) : RecyclerView.Adapter<SerieAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_serie, parent, false)
        return ViewHolder(view)
    }
    var  onItemClick : ((Serie) -> Unit)? = null

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Define los elementos de la vista aquí
        val titulo: TextView = itemView.findViewById(R.id.tituloTextView)
        val genero: TextView = itemView.findViewById(R.id.generoTextView)
        val temporada: TextView=itemView.findViewById(R.id.temporadaTextView)
        val estreno: TextView=itemView.findViewById(R.id.estrenoTextView)
    }
    fun updateData(newSeries: List<Serie>) {
        series = newSeries
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // Actualiza la vista con los datos de la serie en la posición actual
        val serie = series[position]
        Log.d("SerieAdapter", "Binding data for serie: ${serie.titulo}, position: $position")
        holder.titulo.text = serie.titulo
        holder.genero.text = serie.genero
        holder.temporada.text = serie.temporada.toString()
        holder.estreno.text = serie.fechaEstreno

        holder.itemView.setOnClickListener {
            onItemClick?.invoke(serie)
        }
    }

    override fun getItemCount(): Int {
        Log.d("SerieAdapter", "getItemCount: ${series.size}")
        return series.size
    }
}
