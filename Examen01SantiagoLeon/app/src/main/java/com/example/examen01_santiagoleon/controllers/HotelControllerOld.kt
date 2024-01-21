package com.example.examen01_santiagoleon.controllers
/*
import com.example.examen01_santiagoleon.models.dao.HotelDAOOld
import com.example.examen01_santiagoleon.models.entities.Hotel
import com.example.examen01_santiagoleon.models.entities.Reserva

class HotelControllerOld(private val hotelDAOOld: HotelDAOOld) {
    fun getAllHotels(): List<Hotel> {
        return try {
            hotelDAOOld.getAllHotels()
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
            hotelDAOOld.saveHotel(newHotel)
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
            val existingHotel = hotelDAOOld.findHotelById(id)
            if (existingHotel != null) {
                val updatedHotel = Hotel(
                    id,
                    nombre,
                    direccion,
                    calificacion,
                    tieneEstacionamiento,
                    existingHotel.reservas // Preserving the existing list of reservations
                )
                hotelDAOOld.updateHotel(updatedHotel)
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
            hotelDAOOld.deleteHotel(hotelId)
            true
        } catch (e: Exception) {
            println("Error al eliminar el hotel: ${e.message}")
            false
        }
    }

    fun getHotel(hotelId: Int): Hotel? {
        return try {
            hotelDAOOld.findHotelById(hotelId)
        } catch (e: Exception) {
            println("Error al buscar el hotel con ID: $hotelId, Error: ${e.message}")
            null
        }
    }

    fun loadReservationsForHotel(hotelId: Int): MutableList<Reserva> {
        return try {
            hotelDAOOld.loadReservationsForHotel(hotelId)
        } catch (e: Exception) {
            println("Error al cargar reservas para el hotel con ID: $hotelId, Error: ${e.message}")
            mutableListOf()
        }
    }
}
*/