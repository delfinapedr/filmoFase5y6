package com.example.filmo.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Serie(
    val id: Int,
    val titulo: String,
    val genero: String,
    val temporada: Int,
    val episodio: Int,
    val fechaEstreno: String
    ) : Parcelable

