package com.example.examen02_santiagoleon.models.entities

import java.text.SimpleDateFormat
import java.util.Date

data class Reserva(
    val id: Int,
    val cliente: String,
    val fechaEntrada: Date,
    val fechaSalida: Date,
    val numeroPersonas: Int,
    val esCancelable: Boolean,
    val hotelId: Int
) {
    override fun toString(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")
        return "\n\nid: $id, " +
                "\ncliente: $cliente, " +
                "\nfechaEntrada: ${dateFormat.format(fechaEntrada)}, " +
                "\nfechaSalida: ${dateFormat.format(fechaSalida)}, " +
                "\nnumeroPersonas: $numeroPersonas, " +
                "\nesCancelable: $esCancelable, " +
                "\nhotelId: $hotelId"
    }
}