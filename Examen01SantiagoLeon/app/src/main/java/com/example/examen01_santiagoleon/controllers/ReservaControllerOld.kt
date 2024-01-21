package com.example.examen01_santiagoleon.controllers
/*
import com.example.examen01_santiagoleon.models.dao.ReservaDAOOld
import com.example.examen01_santiagoleon.models.entities.Reserva
import java.text.SimpleDateFormat
import java.text.ParseException
import java.util.*

class ReservaControllerOld(private val reservaDAOOld: ReservaDAOOld, private val hotelControllerOld: HotelControllerOld) {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    fun readAllReservas(): List<Reserva> {
        return try {
            reservaDAOOld.getAllReservas()
        } catch (e: Exception) {
            println("Error al leer las reservas: ${e.message}")
            emptyList()
        }
    }

    fun createReserva(
        id: Int,
        cliente: String,
        fechaEntradaString: String,
        fechaSalidaString: String,
        numeroPersonas: Int,
        esCancelable: Boolean,
        hotelId: Int
    ): Reserva? {
        try {
            val hotelExists = hotelControllerOld.getHotel(hotelId) != null
            if (!hotelExists) {
                throw IllegalArgumentException("No existe un hotel con el ID proporcionado: $hotelId")
            }

            val fechaEntrada: Date = dateFormat.parse(fechaEntradaString)
            val fechaSalida: Date = dateFormat.parse(fechaSalidaString)

            if (fechaEntrada.after(fechaSalida)) {
                throw IllegalArgumentException("La fecha de entrada no puede ser posterior a la fecha de salida.")
            }

            val newReserva = Reserva(id, cliente, fechaEntrada, fechaSalida, numeroPersonas, esCancelable, hotelId)
            reservaDAOOld.saveReserva(newReserva)
            return newReserva
        } catch (e: ParseException) {
            println("Error en el formato de fecha: ${e.message}")
        } catch (e: IllegalArgumentException) {
            println("Error en los argumentos proporcionados: ${e.message}")
        } catch (e: Exception) {
            println("Ocurrió un error al crear la reserva: ${e.message}")
        }
        return null
    }

    fun updateReserva(
        id: Int,
        cliente: String,
        fechaEntradaString: String,
        fechaSalidaString: String,
        numeroPersonas: Int,
        esCancelable: Boolean,
        hotelId: Int
    ): Reserva? {
        try {
            val fechaEntrada: Date = dateFormat.parse(fechaEntradaString)
            val fechaSalida: Date = dateFormat.parse(fechaSalidaString)

            if (fechaEntrada.after(fechaSalida)) {
                throw IllegalArgumentException("La fecha de entrada no puede ser posterior a la fecha de salida.")
            }

            val updatedReserva = Reserva(id, cliente, fechaEntrada, fechaSalida, numeroPersonas, esCancelable, hotelId)
            reservaDAOOld.updateReserva(id, updatedReserva)
            return updatedReserva
        } catch (e: ParseException) {
            println("Error en el formato de fecha: ${e.message}")
        } catch (e: IllegalArgumentException) {
            println("Error en los argumentos proporcionados: ${e.message}")
        } catch (e: Exception) {
            println("Ocurrió un error al actualizar la reserva: ${e.message}")
        }
        return null
    }

    fun deleteReserva(id: Int): Boolean {
        return try {
            reservaDAOOld.deleteReserva(id)
            true
        } catch (e: NoSuchElementException) {
            println("No se encontró una reserva con ID: $id")
            false
        } catch (e: Exception) {
            println("Error al eliminar la reserva: ${e.message}")
            false
        }
    }

    fun getReserva(id: Int): Reserva? {
        return try {
            reservaDAOOld.findReservaById(id) ?: throw NoSuchElementException("No se encontró una reserva con ID: $id")
        } catch (e: NoSuchElementException) {
            println(e.message)
            null
        } catch (e: Exception) {
            println("Error al obtener la reserva: ${e.message}")
            null
        }
    }

    fun getReservationsByHotelId(hotelId: Int): List<Reserva> {
        return try {
            reservaDAOOld.getReservationsByHotelId(hotelId)
        } catch (e: Exception) {
            println("Error al obtener reservas para el hotel con ID: $hotelId, Error: ${e.message}")
            emptyList()
        }
    }
}

 */