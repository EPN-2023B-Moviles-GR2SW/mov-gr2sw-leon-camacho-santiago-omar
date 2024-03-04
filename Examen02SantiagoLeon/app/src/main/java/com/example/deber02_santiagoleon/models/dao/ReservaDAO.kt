package com.example.deber02_santiagoleon.models.dao

import android.content.ContentValues
import android.content.Context
import com.example.deber02_santiagoleon.data.Database
import com.example.deber02_santiagoleon.data.DatabaseHelper
import com.example.deber02_santiagoleon.models.entities.Reserva
import java.text.SimpleDateFormat

class ReservaDAO(context: Context?) {
    private val dbHelper = DatabaseHelper(context)
    private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    fun getAllReservas(): List<Reserva> {
        val db = dbHelper.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM Reserva", null)
        val reservas = mutableListOf<Reserva>()

        if (cursor.moveToFirst()) {
            do {
                val reserva = Reserva(
                    id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                    cliente = cursor.getString(cursor.getColumnIndexOrThrow("cliente")),
                    fechaEntrada = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("fechaEntrada"))),
                    fechaSalida = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("fechaSalida"))),
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

    fun saveReserva(reserva: Reserva) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("cliente", reserva.cliente)
            put("fechaEntrada", dateFormat.format(reserva.fechaEntrada))
            put("fechaSalida", dateFormat.format(reserva.fechaSalida))
            put("numeroPersonas", reserva.numeroPersonas)
            put("esCancelable", if (reserva.esCancelable) 1 else 0)
            put("hotelId", reserva.hotelId)
        }

        db.insert("Reserva", null, values)
    }

    fun updateReserva(updatedReserva: Reserva) {
        val db = dbHelper.writableDatabase
        val values = ContentValues().apply {
            put("cliente", updatedReserva.cliente)
            put("fechaEntrada", dateFormat.format(updatedReserva.fechaEntrada))
            put("fechaSalida", dateFormat.format(updatedReserva.fechaSalida))
            put("numeroPersonas", updatedReserva.numeroPersonas)
            put("esCancelable", if (updatedReserva.esCancelable) 1 else 0)
            // Note: hotelId is not updated because a reservation's hotel shouldn't change
        }

        db.update("Reserva", values, "id = ?", arrayOf(updatedReserva.id.toString()))
    }

    fun deleteReserva(reservaId: Int) {
        val db = dbHelper.writableDatabase
        db.delete("Reserva", "id = ?", arrayOf(reservaId.toString()))
    }

    fun findReservaById(reservaId: Int): Reserva? {
        val db = dbHelper.readableDatabase
        val cursor = db.query(
            "Reserva",
            null, // all columns
            "id = ?", // selection criteria
            arrayOf(reservaId.toString()), // selection args
            null, // groupBy
            null, // having
            null // orderBy
        )

        var reserva: Reserva? = null
        if (cursor.moveToFirst()) {
            reserva = Reserva(
                id = cursor.getInt(cursor.getColumnIndexOrThrow("id")),
                cliente = cursor.getString(cursor.getColumnIndexOrThrow("cliente")),
                fechaEntrada = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("fechaEntrada"))) ?: throw Exception("Invalid date format"),
                fechaSalida = dateFormat.parse(cursor.getString(cursor.getColumnIndexOrThrow("fechaSalida"))) ?: throw Exception("Invalid date format"),
                numeroPersonas = cursor.getInt(cursor.getColumnIndexOrThrow("numeroPersonas")),
                esCancelable = cursor.getInt(cursor.getColumnIndexOrThrow("esCancelable")) > 0,
                hotelId = cursor.getInt(cursor.getColumnIndexOrThrow("hotelId"))
            )
        }
        cursor.close()

        return reserva
    }

    fun getReservationsByHotelId(hotelId: Int): MutableList<Reserva> {
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