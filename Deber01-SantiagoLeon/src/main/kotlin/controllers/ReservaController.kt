package controllers

import models.dao.ReservaDAO
import models.entities.Reserva
import java.text.SimpleDateFormat
import java.text.ParseException
import java.util.*

class ReservaController(private val reservaDAO: ReservaDAO, private val hotelController: HotelController) {

    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    fun readAllReservas(): List<Reserva> {
        return try {
            reservaDAO.getAllReservas()
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
            val hotelExists = hotelController.getHotel(hotelId) != null
            if (!hotelExists) {
                throw IllegalArgumentException("No existe un hotel con el ID proporcionado: $hotelId")
            }

            val fechaEntrada: Date = dateFormat.parse(fechaEntradaString)
            val fechaSalida: Date = dateFormat.parse(fechaSalidaString)

            if (fechaEntrada.after(fechaSalida)) {
                throw IllegalArgumentException("La fecha de entrada no puede ser posterior a la fecha de salida.")
            }

            val newReserva = Reserva(id, cliente, fechaEntrada, fechaSalida, numeroPersonas, esCancelable, hotelId)
            reservaDAO.saveReserva(newReserva)
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
            reservaDAO.updateReserva(id, updatedReserva)
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
            reservaDAO.deleteReserva(id)
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
            reservaDAO.findReservaById(id) ?: throw NoSuchElementException("No se encontró una reserva con ID: $id")
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
            reservaDAO.getReservationsByHotelId(hotelId)
        } catch (e: Exception) {
            println("Error al obtener reservas para el hotel con ID: $hotelId, Error: ${e.message}")
            emptyList()
        }
    }
}