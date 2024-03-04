package com.example.examen02_santiagoleon.models.entities

data class Hotel(
    val id: Int,
    val nombre: String,
    val direccion: String,
    val calificacion: Double,
    val tieneEstacionamiento: Boolean,
    val reservas: MutableList<Reserva>?
) {
    override fun toString(): String {
        return "\n\nid: ${id}, " +
                "\nnombre: ${nombre}, " +
                "\ndireccion: ${direccion}, " +
                "\ncalificacion: ${calificacion}, " +
                "\ntieneEstacionamiento: ${tieneEstacionamiento}, " +
                "\nreservas: ${reservas}"
    }

    fun toStringNoReservas(): String {
        return "\n\nid: ${id}, " +
                "\nnombre: ${nombre}, " +
                "\ndireccion: ${direccion}, " +
                "\ncalificacion: ${calificacion}, " +
                "\ntieneEstacionamiento: ${tieneEstacionamiento}"
    }
}