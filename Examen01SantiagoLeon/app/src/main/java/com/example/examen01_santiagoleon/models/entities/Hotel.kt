package com.example.examen01_santiagoleon.models.entities

import com.example.examen01_santiagoleon.models.entities.Reserva

data class Hotel(
    val id: Int,
    val nombre: String,
    val direccion: String,
    val calificacion: Double,
    val tieneEstacionamiento: Boolean,
    val reservas: MutableList<Reserva>
) {
    override fun toString(): String {
        return "\n\nid: ${id}, " +
                "\nnombre: ${nombre}, " +
                "\ndireccion: ${direccion}, " +
                "\ncalificacion: ${calificacion}, " +
                "\ntieneEstacionamiento: ${tieneEstacionamiento}, " +
                "\nreservas: ${reservas}"
    }
}