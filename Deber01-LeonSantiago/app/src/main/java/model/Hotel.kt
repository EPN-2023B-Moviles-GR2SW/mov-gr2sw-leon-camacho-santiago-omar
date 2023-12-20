package model

data class Hotel(
    val nombre: String,
    val ubicacion: String,
    val calificacion: Float,
    val numeroDeHabitaciones: Int,
    val tienePiscina: Boolean,
    val reservas: MutableList<Reserva> = mutableListOf()
)
