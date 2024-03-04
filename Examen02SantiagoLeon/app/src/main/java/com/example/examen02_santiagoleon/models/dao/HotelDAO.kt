package com.example.examen02_santiagoleon.models.dao

import android.content.ContentValues
import android.content.Context
import com.example.examen02_santiagoleon.data.Database
import com.example.examen02_santiagoleon.data.DatabaseHelper
import com.example.examen02_santiagoleon.models.entities.Hotel
import com.example.examen02_santiagoleon.models.entities.Reserva
import java.text.SimpleDateFormat

class HotelDAO(context: Context?) {
    private val dbHelper = DatabaseHelper(context)
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")
    fun getAllHotels(): List<Hotel> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Hotel", null)
        val hotels = mutableListOf<Hotel>()

        if (cursor.moveToFirst()) {
            do {
                val hotel = Hotel(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                    direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion")),
                    calificacion = cursor.getDouble(cursor.getColumnIndexOrThrow("calificacion")),
                    tieneEstacionamiento = cursor.getInt(cursor.getColumnIndexOrThrow("tieneEstacionamiento")) > 0,
                    reservas = null // Reservas are fetched separately and thus initialized as null here
                )
                hotels.add(hotel)
            } while (cursor.moveToNext())
        }
        cursor.close()
        return hotels
    }

    fun saveHotel(hotel: Hotel) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", hotel.nombre)
            put("direccion", hotel.direccion)
            put("calificacion", hotel.calificacion)
            put("tieneEstacionamiento", if (hotel.tieneEstacionamiento) 1 else 0)
        }

        db.insert("Hotel", null, values)
    }

    fun updateHotel(updatedHotel: Hotel) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("nombre", updatedHotel.nombre)
            put("direccion", updatedHotel.direccion)
            put("calificacion", updatedHotel.calificacion)
            put("tieneEstacionamiento", if (updatedHotel.tieneEstacionamiento) 1 else 0)
        }

        db.update("Hotel", values, "id = ?", arrayOf(updatedHotel.id.toString()))
    }

    fun deleteHotel(hotelId: Int) {
        val db = dbHelper.writableDatabase
        db.delete("Hotel", "id = ?", arrayOf(hotelId.toString()))
    }

    fun findHotelById(hotelId: Int): Hotel? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "Hotel", null, "id = ?", arrayOf(hotelId.toString()), null, null, null
        )

        var hotel: Hotel? = null
        if (cursor.moveToFirst()) {
            hotel = Hotel(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                nombre = cursor.getString(cursor.getColumnIndexOrThrow("nombre")),
                direccion = cursor.getString(cursor.getColumnIndexOrThrow("direccion")),
                calificacion = cursor.getDouble(cursor.getColumnIndexOrThrow("calificacion")),
                tieneEstacionamiento = cursor.getInt(cursor.getColumnIndexOrThrow("tieneEstacionamiento")) > 0,
                reservas = null // Reservas are fetched separately and thus initialized as null here
            )
        }
        cursor.close()
        return hotel
    }

    fun loadReservationsForHotel(hotelId: Int): MutableList<Reserva> {
        val db = dbHelper.readableDatabase
        val reservas = mutableListOf<Reserva>()
        val cursor = db.query(
            "Reserva",
            null, // all columns
            "hotelId = ?", // selection
            arrayOf(hotelId.toString()), // selectionArgs
            null, // groupBy
            null, // having
            null  // orderBy
        )

        if (cursor.moveToFirst()) {
            do {
                val reserva = Reserva(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cliente = cursor.getString(cursor.getColumnIndexOrThrow("cliente")),
                    fechaEntrada = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("fechaEntrada"))) ?: throw Exception("Invalid date format"),
                    fechaSalida = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("fechaSalida"))) ?: throw Exception("Invalid date format"),
                    numeroPersonas = cursor.getInt(cursor.getColumnIndexOrThrow("numeroPersonas")),
                    esCancelable = cursor.getInt(cursor.getColumnIndexOrThrow("esCancelable")) > 0,
                    hotelId = cursor.getInt(cursor.getColumnIndexOrThrow("hotelId"))
                )
                reservas.add(reserva)
            } while (cursor.moveToNext())
        }
        cursor.close()

        return reservas
    }
}