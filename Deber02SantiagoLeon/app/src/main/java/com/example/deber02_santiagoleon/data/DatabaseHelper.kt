package com.example.deber02_santiagoleon.data

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(
    context: Context?
) : SQLiteOpenHelper(
    context,
    "HotelReservation",
    null,
    1) {
    override fun onCreate(db: SQLiteDatabase?) {
        val createTableHotel = """
            CREATE TABLE Hotel (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                nombre TEXT NOT NULL,
                direccion TEXT NOT NULL,
                calificacion REAL NOT NULL,
                tieneEstacionamiento INTEGER NOT NULL
            );
        """.trimIndent()

        val createTableReserva = """
            CREATE TABLE Reserva (
                id INTEGER PRIMARY KEY AUTOINCREMENT,
                cliente TEXT NOT NULL,
                fechaEntrada TEXT NOT NULL,
                fechaSalida TEXT NOT NULL,
                numeroPersonas INTEGER NOT NULL,
                esCancelable INTEGER NOT NULL,
                hotelId INTEGER NOT NULL,
                FOREIGN KEY (hotelId) REFERENCES Hotel(id) ON DELETE CASCADE
            );
        """.trimIndent()

        db?.execSQL(createTableHotel)
        db?.execSQL(createTableReserva)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.execSQL("DROP TABLE IF EXISTS Reserva")
        db?.execSQL("DROP TABLE IF EXISTS Hotel")
        onCreate(db)
    }
}
