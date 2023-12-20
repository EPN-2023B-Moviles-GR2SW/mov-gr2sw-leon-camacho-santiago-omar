package model

import java.time.LocalDate

data class Reserva(
    val id: Int,
    val nombreCliente: String,
    val fechaEntrada: LocalDate,
    val fechaSalida: LocalDate,
    val tipoHabitacion: String,
    val cantidadPersonas: Int
)
