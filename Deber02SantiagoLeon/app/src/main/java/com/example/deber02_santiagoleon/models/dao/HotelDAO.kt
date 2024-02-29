package com.example.deber02_santiagoleon.models.dao

import com.example.deber02_santiagoleon.data.Database
import com.example.deber02_santiagoleon.models.entities.Hotel
import com.example.deber02_santiagoleon.models.entities.Reserva

class HotelDAO {
    fun getAllHotels(): List<Hotel> {
        return Database.listaHoteles
    }

    fun saveHotel(hotel: Hotel) {
        Database.listaHoteles.add(hotel)
    }

    fun updateHotel(updatedHotel: Hotel) {
        val index = Database.listaHoteles.indexOfFirst { it.id == updatedHotel.id }
        if (index != -1) {
            Database.listaHoteles[index] = updatedHotel
        }
    }

    fun deleteHotel(hotelId: Int) {
        Database.listaHoteles.removeAll { it.id == hotelId }

        Database.listaReservas101.removeAll { it.hotelId == hotelId }
        Database.listaReservas102.removeAll { it.hotelId == hotelId }
        Database.listaReservas103.removeAll { it.hotelId == hotelId }
    }

    fun findHotelById(hotelId: Int): Hotel? {
        return Database.listaHoteles.find { it.id == hotelId }
    }

    fun loadReservationsForHotel(hotelId: Int): List<Reserva> {
        return when (hotelId) {
            101 -> Database.listaReservas101
            102 -> Database.listaReservas102
            103 -> Database.listaReservas103
            else -> emptyList()
        }
    }
}
