package com.example.filmo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Pelicula(
    val id: Int,
    val titulo: String,
    val genero: String,
    val duracion: Int,
    val fechaEstreno: Int
    ) : Parcelable
