package view

import controllers.HotelController

class HotelView(private val hotelController: HotelController) {

    fun showMenu() {
        while (true) {
            println("\n--- Menú de Hoteles ---")
            println("1. Ver todos los hoteles")
            println("2. Crear un hotel")
            println("3. Actualizar un hotel")
            println("4. Eliminar un hotel")
            println("5. Buscar hotel por ID")
            println("6. Ver reservas de un hotel")
            println("7. Volver al menú principal")
            print("Seleccione una opción: ")

            val option = readLine()?.toIntOrNull()
            when (option) {
                1 -> viewAllHotels()
                2 -> createHotel()
                3 -> updateHotel()
                4 -> deleteHotel()
                5 -> getHotel()
                6 -> loadReservationsForHotel()
                7 -> return
                else -> println("Opción inválida, por favor intente de nuevo.")
            }
        }
    }

    private fun viewAllHotels() {
        val hotels = hotelController.getAllHotels()
        hotels.forEach { println(it) }
    }

    private fun createHotel() {
        println("Ingrese los detalles del hotel:")
        print("ID: ")
        val id = readLine()?.toIntOrNull() ?: return
        print("Nombre: ")
        val nombre = readLine().orEmpty()
        print("Dirección: ")
        val direccion = readLine().orEmpty()
        print("Calificación: ")
        val calificacion = readLine()?.toDoubleOrNull() ?: return
        print("¿Tiene estacionamiento? (true/false): ")
        val tieneEstacionamiento = readLine()?.toBoolean() ?: return

        val hotel = hotelController.createHotel(id, nombre, direccion, calificacion, tieneEstacionamiento)
        if (hotel) {
            println("Hotel creado con éxito: $hotel")
        } else {
            println("No se pudo crear el hotel.")
        }
    }

    private fun updateHotel() {
        println("\nActualizar Hotel:")
        print("Ingrese el ID del hotel a actualizar: ")
        val id = readLine()?.toIntOrNull() ?: return
        println("Ingrese los nuevos detalles del hotel:")
        print("Nombre: ")
        val nombre = readLine().orEmpty()
        print("Dirección: ")
        val direccion = readLine().orEmpty()
        print("Calificación: ")
        val calificacion = readLine()?.toDoubleOrNull() ?: return
        print("¿Tiene estacionamiento? (true/false): ")
        val tieneEstacionamiento = readLine()?.toBoolean() ?: return

        val updatedHotel = hotelController.updateHotel(id, nombre, direccion, calificacion, tieneEstacionamiento)
        if (updatedHotel) {
            println("Hotel actualizado con éxito: $updatedHotel")
        } else {
            println("No se pudo actualizar el hotel.")
        }
    }

    private fun deleteHotel() {
        println("\nEliminar Hotel:")
        print("Ingrese el ID del hotel a eliminar: ")
        val id = readLine()?.toIntOrNull() ?: return

        val success = hotelController.deleteHotel(id)
        if (success) {
            println("Hotel eliminado con éxito.")
        } else {
            println("No se pudo eliminar el hotel.")
        }
    }

    private fun getHotel() {
        println("\nBuscar Hotel por ID:")
        print("Ingrese el ID del hotel: ")
        val id = readLine()?.toIntOrNull() ?: return

        val hotel = hotelController.getHotel(id)
        if (hotel != null) {
            println("Detalles del hotel: $hotel")
        } else {
            println("No se encontró un hotel con el ID proporcionado.")
        }
    }

    private fun loadReservationsForHotel() {
        println("\nVer Reservas de un Hotel:")
        print("Ingrese el ID del hotel: ")
        val hotelId = readLine()?.toIntOrNull() ?: return

        val reservas = hotelController.loadReservationsForHotel(hotelId)
        if (reservas.isNotEmpty()) {
            reservas.forEach { println(it) }
        } else {
            println("No hay reservas para el hotel con el ID proporcionado.")
        }
    }
}
