package models.dao

import models.entities.Reserva
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import utils.DateDeserializer
import utils.DateSerializer
import java.io.FileReader
import java.io.FileWriter
import java.util.Date

class ReservaDAO {
    private val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateSerializer())
        .registerTypeAdapter(Date::class.java, DateDeserializer())
        .setPrettyPrinting()
        .create()

    private val filePath = "src/main/kotlin/SampleData/reservas.json"

    fun getAllReservas(): List<Reserva> {
        val fileReader = FileReader(filePath)
        val typeToken = object : TypeToken<List<Reserva>>() {}.type
        return gson.fromJson(fileReader, typeToken) ?: emptyList()
    }

    fun saveReserva(reserva: Reserva) {
        val reservas = getAllReservas().toMutableList()
        reservas.add(reserva)
        val fileWriter = FileWriter(filePath)
        gson.toJson(reservas, fileWriter)
        fileWriter.close()
    }

    fun updateReserva(id: Int, updatedReserva: Reserva) {
        val reservas = getAllReservas().toMutableList()
        val index = reservas.indexOfFirst { it.id == id }

        if (index != -1) {
            reservas[index] = updatedReserva
            val fileWriter = FileWriter(filePath)
            gson.toJson(reservas, fileWriter)
            fileWriter.close()
        }
    }

    fun deleteReserva(id: Int) {
        val reservas = getAllReservas().toMutableList()
        val index = reservas.indexOfFirst { it.id == id }

        if (index != -1) {
            reservas.removeAt(index)
            val fileWriter = FileWriter(filePath)
            gson.toJson(reservas, fileWriter)
            fileWriter.close()
        }
    }

    fun findReservaById(id: Int): Reserva? {
        val reservas = getAllReservas()
        return reservas.find { it.id == id }
    }

    fun getReservationsByHotelId(hotelId: Int): List<Reserva> {
        val fileReader = FileReader(filePath)
        val typeToken = object : TypeToken<List<Reserva>>() {}.type
        val allReservas = gson.fromJson<List<Reserva>>(fileReader, typeToken) ?: emptyList()

        return allReservas.filter { it.hotelId == hotelId }
    }
}
