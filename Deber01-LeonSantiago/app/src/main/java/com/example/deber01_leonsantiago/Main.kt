package com.example.deber01_leonsantiago

import manager.HotelManager
import manager.ReservaManager

fun main() {
    val hotelManager = HotelManager()
    val reservaManager = ReservaManager()

    while (true) {
        println("\nMenu Principal")
        println("1. Buscar Hotel")
        println("2. Agregar Hotel")
        println("3. Eliminar Hotel")
        println("4. Actualizar Hotel")
        println("5. Mostrar Hoteles")
        println("6. Ir a reservas")
        println("7. Salir")
        print("Seleccione una opción: ")

        when (readLine()) {
            "1" -> {
                // Buscar por nombre el hotel y mostrar sus datos
            }
            "2" -> {
                // Solicitar datos del hotel y llamar a HotelManager.agregarHotel(...)
            }
            "3" -> {
                // Leer txt y buscar por nombre el hotel y borrar todos sus datos buscando un primer }
            }
            "4" -> {
                // Leer txt y buscar por nombre el hotel y pedir que datos se van a actualizar
            }
            "5" -> {
                // Leer txt del hoteles y mostrar el txt completo
            }
            "6" -> {
                while (true) {
                    println("\nMenu de Reservas")
                    println("1. Buscar Reserva para un Hotel")
                    println("2. Agregar Reserva para un Hotel")
                    println("3. Eliminar Reserva para un Hotel")
                    println("4. Actualizar Reserva para un Hotel")
                    println("5. Mostrar Reservas para un Hotel")
                    println("6. Regresar a Menu Principal")
                    print("Seleccione una opción: ")

                    when (readLine()) {
                        "1" -> {
                            // Buscar por nombre el hotel y por nombre la reserva y mostrar sus datos
                        }
                        "2" -> {
                            // Buscar por nombre el hotel y solicitar datos de reserva y llamar a ReservaManager.agregarReserva(...)
                        }
                        "3" -> {
                            // Buscar por nombre el hotel, leer txt y buscar por nombre la reserva y borrar todos sus datos buscando un primer }
                        }
                        "4" -> {
                            // Buscar por nombre el hotel, leer txt y buscar por nombre la reserva y pedir que datos se van a actualizar
                        }
                        "5" -> {
                            // Buscar por nombre el hotel, leer txt de reservas y mostrar solo reservas de ese hotel
                        }
                        "6" -> break
                        else -> println("Opción no válida.")
                    }
                }
            }
            "7" -> break
            else -> println("Opción no válida.")
        }
    }
}
