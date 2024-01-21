package controllers

import models.dao.HotelDAO
import com.example.examen01_santiagoleon.models.entities.Hotel
import com.example.examen01_santiagoleon.models.entities.Reserva

class HotelController(private val hotelDAO: HotelDAO) {
    fun getAllHotels(): List<Hotel> {
        return try {
            hotelDAO.getAllHotels()
        } catch (e: Exception) {
            println("Error al obtener todos los hoteles: ${e.message}")
            emptyList()
        }
    }

    fun createHotel(
        id: Int,
        nombre: String,
        direccion: String,
        calificacion: Double,
        tieneEstacionamiento: Boolean
    ): Boolean {
        return try {
            val newHotel = Hotel(id, nombre, direccion, calificacion, tieneEstacionamiento, mutableListOf<Reserva>())
            hotelDAO.saveHotel(newHotel)
            true
        } catch (e: Exception) {
            println("Error al guardar el hotel: ${e.message}")
            false
        }
    }

    fun updateHotel(
        id: Int,
        nombre: String,
        direccion: String,
        calificacion: Double,
        tieneEstacionamiento: Boolean
    ): Boolean {
        return try {
            val existingHotel = hotelDAO.findHotelById(id)
            if (existingHotel != null) {
                val updatedHotel = Hotel(
                    id,
                    nombre,
                    direccion,
                    calificacion,
                    tieneEstacionamiento,
                    existingHotel.reservas // Preserving the existing list of reservations
                )
                hotelDAO.updateHotel(updatedHotel)
                true
            } else {
                println("Hotel con ID: $id no encontrado.")
                false
            }
        } catch (e: Exception) {
            println("Error al actualizar el hotel: ${e.message}")
            false
        }
    }

    fun deleteHotel(hotelId: Int): Boolean {
        return try {
            hotelDAO.deleteHotel(hotelId)
            true
        } catch (e: Exception) {
            println("Error al eliminar el hotel: ${e.message}")
            false
        }
    }

    fun getHotel(hotelId: Int): Hotel? {
        return try {
            hotelDAO.findHotelById(hotelId)
        } catch (e: Exception) {
            println("Error al buscar el hotel con ID: $hotelId, Error: ${e.message}")
            null
        }
    }

    fun loadReservationsForHotel(hotelId: Int): MutableList<Reserva> {
        return try {
            hotelDAO.loadReservationsForHotel(hotelId)
        } catch (e: Exception) {
            println("Error al cargar reservas para el hotel con ID: $hotelId, Error: ${e.message}")
            mutableListOf()
        }
    }
}