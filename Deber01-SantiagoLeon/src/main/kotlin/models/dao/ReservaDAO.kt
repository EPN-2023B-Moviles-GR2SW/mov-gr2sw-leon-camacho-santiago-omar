package models.dao

import com.google.gson.GsonBuilder
import models.entities.Reserva
import utils.DateDeserializer
import utils.DateSerializer
import java.util.*

class ReservaDAO {
    private val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateSerializer())
        .registerTypeAdapter(Date::class.java, DateDeserializer())
        .create()

    fun saveReserva(reserva: Reserva) {
        val jsonData = gson.toJson(reserva)
        // Write jsonData to file
    }

    fun readReserva(): Reserva {
        // Read JSON data from file
        val jsonData = "sampledata/reservas.json"
        return gson.fromJson(jsonData, Reserva::class.java)
    }

    // Other CRUD operations
}