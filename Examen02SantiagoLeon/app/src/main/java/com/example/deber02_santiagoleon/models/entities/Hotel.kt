package com.example.deber02_santiagoleon.models.entities

data class Hotel(
    val nombre: String,
    val direccion: String,
    val calificacion: Double,
    val tieneEstacionamiento: Boolean
) {
    override fun toString(): String {
        return "\n\nnombre: ${nombre}, " +
                "\ndireccion: ${direccion}, " +
                "\ncalificacion: ${calificacion}, " +
                "\ntieneEstacionamiento: ${tieneEstacionamiento}"
    }

    fun toStringNoReservas(): String {
        return "\n\nnombre: ${nombre}, " +
                "\ndireccion: ${direccion}, " +
                "\ncalificacion: ${calificacion}, " +
                "\ntieneEstacionamiento: ${tieneEstacionamiento}"
    }
}