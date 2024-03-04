package com.example.examen02_santiagoleon.data

import com.example.examen02_santiagoleon.models.entities.Hotel
import com.example.examen02_santiagoleon.models.entities.Reserva
import java.text.SimpleDateFormat

class Database {
    companion object{
        private val dateFormat = SimpleDateFormat("yyyy-MM-dd")

        val listaReservas101: MutableList<Reserva> = arrayListOf()
        val listaReservas102: MutableList<Reserva> = arrayListOf()
        val listaReservas103: MutableList<Reserva> = arrayListOf()
        val listaHoteles: MutableList<Hotel> = arrayListOf()

        init {
            listaReservas101.add(Reserva(
                3,
                "Ricardo",
                dateFormat.parse("2024-02-16"),
                dateFormat.parse("2024-03-15"),
                2,
                false,
                101
            ))
            listaReservas101.add(Reserva(
                9,
                "Test Client",
                dateFormat.parse("2024-08-01"),
                dateFormat.parse("2024-08-10"),
                3,
                true,
                101
            ))
            listaReservas101.add(Reserva(
                10,
                "gonzalo",
                dateFormat.parse("2024-08-13"),
                dateFormat.parse("2034-08-13"),
                5,
                false,
                101
            ))

            listaReservas102.add(Reserva(
                4,
                "Omar",
                dateFormat.parse("2024-07-09"),
                dateFormat.parse("2024-07-21"),
                1,
                false,
                102
            ))

            listaReservas103.add(Reserva(
                7,
                "Test Client",
                dateFormat.parse("2024-08-01"),
                dateFormat.parse("2024-08-10"),
                1,
                true,
                103
            ))
            listaReservas102.add(Reserva(
                8,
                "Test Client2 Pero igual",
                dateFormat.parse("2024-08-01"),
                dateFormat.parse("2024-08-10"),
                3,
                true,
                103
            ))

            listaHoteles.add(Hotel(
                101,
                "Hotel Sunshine",
                "123 Sunny Street",
                4.5,
                true,
                listaReservas101
            ))
            listaHoteles.add(Hotel(
                102,
                "Hotel Ejemplo",
                "Calle ejemplo",
                4.8,
                false,
                listaReservas102
            ))
            listaHoteles.add(Hotel(
                103,
                "Hotel Playita",
                "123 St Bakers Street",
                3.9,
                true,
                listaReservas103
            ))
        }
    }
}