package com.example.deber02_santiagoleon.models.entities

import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

data class Reserva(
    val cliente: String,
    val fechaEntrada: Timestamp, // Using Timestamp for compatibility with Firestore.
    val fechaSalida: Timestamp,
    val numeroPersonas: Int,
    val esCancelable: Boolean,
    val hotelId: String // Keeping hotelId for explicit hotel references.
) {
    override fun toString(): String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val formattedFechaEntrada = dateFormat.format(fechaEntrada.toDate())
        val formattedFechaSalida = dateFormat.format(fechaSalida.toDate())
        return "\n\ncliente: $cliente, " +
                "\nfechaEntrada: $formattedFechaEntrada, " +
                "\nfechaSalida: $formattedFechaSalida, " +
                "\nnumeroPersonas: $numeroPersonas, " +
                "\nesCancelable: $esCancelable, " +
                "\nhotelId: $hotelId"
    }
}