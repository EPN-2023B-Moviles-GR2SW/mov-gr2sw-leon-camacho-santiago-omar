package models.dao

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.example.examen01_santiagoleon.models.entities.Hotel
import com.example.examen01_santiagoleon.models.entities.Reserva
import utils.DateDeserializer
import utils.DateSerializer
import java.io.FileReader
import java.io.FileWriter
import java.util.*

class HotelDAO(private val reservaDAO: ReservaDAO) {

    private val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateSerializer())
        .registerTypeAdapter(Date::class.java, DateDeserializer())
        .create()

    private val hotelsFilePath = "src/main/kotlin/SampleData/hoteles.json"

    fun getAllHotels(): List<Hotel> {
        val fileReader = FileReader(hotelsFilePath)
        val typeToken = object : TypeToken<List<Hotel>>() {}.type
        return gson.fromJson(fileReader, typeToken) ?: emptyList()
    }

    fun saveHotel(hotel: Hotel) {
        val hotels = getAllHotels().toMutableList()
        hotels.add(hotel)
        val fileWriter = FileWriter(hotelsFilePath)
        gson.toJson(hotels, fileWriter)
        fileWriter.close()
    }

    fun updateHotel(updatedHotel: Hotel) {
        val hotels = getAllHotels().toMutableList()
        val index = hotels.indexOfFirst { it.id == updatedHotel.id }
        if (index != -1) {
            hotels[index] = updatedHotel
            val fileWriter = FileWriter(hotelsFilePath)
            gson.toJson(hotels, fileWriter)
            fileWriter.close()
        }
    }

    fun deleteHotel(hotelId: Int) {
        val hotels = getAllHotels().toMutableList()
        hotels.removeAll { it.id == hotelId }
        val fileWriter = FileWriter(hotelsFilePath)
        gson.toJson(hotels, fileWriter)
        fileWriter.close()
    }

    fun findHotelById(hotelId: Int): Hotel? {
        return getAllHotels().find { it.id == hotelId }
    }

    fun loadReservationsForHotel(hotelId: Int): MutableList<Reserva> {
        val reservaDAO = ReservaDAO()
        return reservaDAO.getReservationsByHotelId(hotelId).toMutableList()
    }
}