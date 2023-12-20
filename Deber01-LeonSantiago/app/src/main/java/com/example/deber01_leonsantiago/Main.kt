package com.example.deber01_leonsantiago

import manager.HotelManager
import manager.ReservaManager

fun main() {
    val hotelManager = HotelManager()
    val reservaManager = ReservaManager()

    while (true) {
        println("\nMenu Principal")
        println("1. Agregar Hotel")
        println("2. Mostrar Hoteles")
        println("3. Salir")
        print("Seleccione una opción: ")

        when (readLine()) {
            "1" -> {
                // Solicitar datos del hotel y llamar a hotelManager.agregarHotel(...)
            }
            "2" -> {
                hotelManager.mostrarHoteles()
            }
            "3" -> break
            else -> println("Opción no válida")
        }
    }
}
