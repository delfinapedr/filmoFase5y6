package com.example.filmo.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmo.R
import com.example.filmo.data.Pelicula
import com.example.filmo.data.Serie

class PeliculasAdapter(var peliculas: List<Pelicula>) : RecyclerView.Adapter<PeliculasAdapter.ViewHolder>() {

    var onItemClick: ((Pelicula) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pelicula, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val pelicula = peliculas[position]
        holder.bind(pelicula)
    }

    override fun getItemCount(): Int {
        return peliculas.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tituloTextView: TextView = itemView.findViewById(R.id.tituloTextView)
        val generoTextView: TextView = itemView.findViewById(R.id.generoTextView)
        val duracionTextView: TextView = itemView.findViewById(R.id.duracionTextView)
        val fechaEstrenoTextView: TextView = itemView.findViewById(R.id.fechaEstrenoTextView)

        init {
            itemView.setOnClickListener {
                val position = adapterPosition
                if (position != RecyclerView.NO_POSITION) {
                    onItemClick?.invoke(peliculas[position])
                }
            }
        }

        fun bind(pelicula: Pelicula) {
            tituloTextView.text = pelicula.titulo
            generoTextView.text = "Género: ${pelicula.genero}"
            duracionTextView.text = "Duración: ${pelicula.duracion} min"
            fechaEstrenoTextView.text = "Año: ${pelicula.fechaEstreno}"
        }
    }

    fun updateData(newPeliculas: List<Pelicula>) {
        peliculas = newPeliculas
        notifyDataSetChanged()
    }
}
