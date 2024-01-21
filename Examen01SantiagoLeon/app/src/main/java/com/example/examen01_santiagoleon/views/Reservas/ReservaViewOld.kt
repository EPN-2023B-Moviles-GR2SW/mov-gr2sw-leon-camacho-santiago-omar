package com.example.examen01_santiagoleon.views.Reservas
/*
import controllers.ReservaControllerOld

class ReservaViewOld(private val reservaControllerOld: ReservaControllerOld) {

    fun showMenu() {
        while (true) {
            println("\n--- Menú de Reservas ---")
            println("1. Ver todas las reservas")
            println("2. Crear una reserva")
            println("3. Actualizar una reserva")
            println("4. Eliminar una reserva")
            println("5. Buscar reserva por ID")
            println("6. Buscar reservas por ID de hotel")
            println("7. Volver al menú principal")
            print("Seleccione una opción: ")

            val option = readLine()?.toIntOrNull()
            when (option) {
                1 -> viewAllReservas()
                2 -> createReserva()
                3 -> updateReserva()
                4 -> deleteReserva()
                5 -> getReserva()
                6 -> getReservasByHotelId()
                7 -> return
                else -> println("Opción inválida, por favor intente de nuevo.")
            }
        }
    }

    private fun viewAllReservas() {
        val reservas = reservaControllerOld.readAllReservas()
        reservas.forEach { println(it) }
    }

    private fun createReserva() {
        println("Ingrese los detalles de la reserva:")
        print("ID: ")
        val id = readLine()?.toIntOrNull() ?: return
        print("Cliente: ")
        val cliente = readLine().orEmpty()
        print("Fecha de entrada (yyyy-MM-dd): ")
        val fechaEntrada = readLine().orEmpty()
        print("Fecha de salida (yyyy-MM-dd): ")
        val fechaSalida = readLine().orEmpty()
        print("Número de personas: ")
        val numeroPersonas = readLine()?.toIntOrNull() ?: return
        print("¿Es cancelable? (true/false): ")
        val esCancelable = readLine()?.toBoolean() ?: return
        print("ID del hotel: ")
        val hotelId = readLine()?.toIntOrNull() ?: return

        val reserva = reservaControllerOld.createReserva(id, cliente, fechaEntrada, fechaSalida, numeroPersonas, esCancelable, hotelId)
        if (reserva != null) {
            println("Reserva creada con éxito: $reserva")
        } else {
            println("No se pudo crear la reserva.")
        }
    }

    private fun updateReserva() {
        println("\nActualizar Reserva:")
        print("Ingrese el ID de la reserva a actualizar: ")
        val id = readLine()?.toIntOrNull() ?: return
        println("Ingrese los nuevos detalles de la reserva:")
        print("Cliente: ")
        val cliente = readLine().orEmpty()
        print("Fecha de entrada (yyyy-MM-dd): ")
        val fechaEntrada = readLine().orEmpty()
        print("Fecha de salida (yyyy-MM-dd): ")
        val fechaSalida = readLine().orEmpty()
        print("Número de personas: ")
        val numeroPersonas = readLine()?.toIntOrNull() ?: return
        print("¿Es cancelable? (true/false): ")
        val esCancelable = readLine()?.toBoolean() ?: return
        print("ID del hotel: ")
        val hotelId = readLine()?.toIntOrNull() ?: return

        val updatedReserva = reservaControllerOld.updateReserva(id, cliente, fechaEntrada, fechaSalida, numeroPersonas, esCancelable, hotelId)
        if (updatedReserva != null) {
            println("Reserva actualizada con éxito: $updatedReserva")
        } else {
            println("No se pudo actualizar la reserva.")
        }
    }

    private fun deleteReserva() {
        println("\nEliminar Reserva:")
        print("Ingrese el ID de la reserva a eliminar: ")
        val id = readLine()?.toIntOrNull() ?: return

        val success = reservaControllerOld.deleteReserva(id)
        if (success) {
            println("Reserva eliminada con éxito.")
        } else {
            println("No se pudo eliminar la reserva.")
        }
    }

    private fun getReserva() {
        println("\nBuscar Reserva por ID:")
        print("Ingrese el ID de la reserva: ")
        val id = readLine()?.toIntOrNull() ?: return

        val reserva = reservaControllerOld.getReserva(id)
        if (reserva != null) {
            println("Detalles de la reserva: $reserva")
        } else {
            println("No se encontró una reserva con el ID proporcionado.")
        }
    }

    private fun getReservasByHotelId() {
        println("\nBuscar Reservas por ID de Hotel:")
        print("Ingrese el ID del hotel: ")
        val hotelId = readLine()?.toIntOrNull() ?: return

        val reservas = reservaControllerOld.getReservationsByHotelId(hotelId)
        if (reservas.isNotEmpty()) {
            reservas.forEach { println(it) }
        } else {
            println("No hay reservas para el hotel con el ID proporcionado.")
        }
    }
}
*/