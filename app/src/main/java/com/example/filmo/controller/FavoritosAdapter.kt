package com.example.filmo.controller

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.filmo.R

class FavoritosAdapter : RecyclerView.Adapter<FavoritosAdapter.FavoritoViewHolder>() {

    private val listaFavoritos: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoritoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_favorito, parent, false)
        return FavoritoViewHolder(view)
    }

    override fun onBindViewHolder(holder: FavoritoViewHolder, position: Int) {
        val favorito = listaFavoritos[position]
        holder.bind(favorito)
    }

    override fun getItemCount(): Int {
        return listaFavoritos.size
    }

    fun actualizarListaFavoritos(nuevaLista: List<String>) {
        listaFavoritos.clear()
        listaFavoritos.addAll(nuevaLista)
        notifyDataSetChanged()
    }

    inner class FavoritoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val textViewTitulo: TextView = itemView.findViewById(R.id.textViewTitulo)

        fun bind(favorito: String) {
            textViewTitulo.text = favorito
        }
    }
}
