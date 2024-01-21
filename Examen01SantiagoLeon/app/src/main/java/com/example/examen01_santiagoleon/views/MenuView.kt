package view

import controllers.MenuController
import controllers.HotelController
import controllers.ReservaController

class MenuView(
    hotelController: HotelController,
    reservaController: ReservaController
) {
    private val menuController = MenuController(HotelView(hotelController), ReservaView(reservaController))

    fun show() {
        var continueLoop = true
        while (continueLoop) {
            continueLoop = menuController.processSelection(showMenuAndGetSelection())
        }
    }

    private fun showMenuAndGetSelection(): Int {
        println("\n--- Menú Principal ---")
        println("1. Gestión de Hoteles")
        println("2. Gestión de Reservas")
        println("3. Salir")
        print("Seleccione una opción: ")
        return readLine()?.toIntOrNull() ?: 0
    }
}
