package com.example.examen01_santiagoleon.models.dao

import com.example.examen01_santiagoleon.data.Database
import com.example.examen01_santiagoleon.models.entities.Reserva

class ReservaDAO {

    fun getAllReservas(): List<Reserva> {
        return Database.listaReservas101 + Database.listaReservas102 + Database.listaReservas103
    }

    fun saveReserva(reserva: Reserva) {
        when (reserva.hotelId) {
            101 -> Database.listaReservas101.add(reserva)
            102 -> Database.listaReservas102.add(reserva)
            103 -> Database.listaReservas103.add(reserva)
        }
    }

    fun updateReserva(updatedReserva: Reserva) {
        when (updatedReserva.hotelId) {
            101 -> updateReservaInList(Database.listaReservas101, updatedReserva)
            102 -> updateReservaInList(Database.listaReservas102, updatedReserva)
            103 -> updateReservaInList(Database.listaReservas103, updatedReserva)
        }
    }

    private fun updateReservaInList(reservasList: MutableList<Reserva>, updatedReserva: Reserva) {
        val index = reservasList.indexOfFirst { it.id == updatedReserva.id }
        if (index != -1) {
            reservasList[index] = updatedReserva
        }
    }

    fun deleteReserva(reservaId: Int) {
        when {
            reservaId in Database.listaReservas101.map { it.id } -> deleteReservaFromList(Database.listaReservas101, reservaId)
            reservaId in Database.listaReservas102.map { it.id } -> deleteReservaFromList(Database.listaReservas102, reservaId)
            reservaId in Database.listaReservas103.map { it.id } -> deleteReservaFromList(Database.listaReservas103, reservaId)
            // Add cases for other hotel IDs if necessary
        }
    }

    private fun deleteReservaFromList(reservasList: MutableList<Reserva>, reservaId: Int) {
        reservasList.removeAll { it.id == reservaId }
    }

    fun findReservaById(reservaId: Int): Reserva? {
        val allReservas = getAllReservas()
        return allReservas.find { it.id == reservaId }
    }

    fun getReservationsByHotelId(hotelId: Int): List<Reserva> {
        return when (hotelId) {
            101 -> Database.listaReservas101
            102 -> Database.listaReservas102
            103 -> Database.listaReservas103
            else -> emptyList()
        }
    }
}
