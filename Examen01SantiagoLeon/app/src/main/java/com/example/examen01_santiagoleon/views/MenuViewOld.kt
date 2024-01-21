package view

import controllers.MenuControllerOld
import controllers.HotelControllerOld
import controllers.ReservaControllerOld

class MenuViewOld(
    hotelControllerOld: HotelControllerOld,
    reservaControllerOld: ReservaControllerOld
) {
    private val menuControllerOld = MenuControllerOld(HotelViewOld(hotelControllerOld), ReservaViewOld(reservaControllerOld))

    fun show() {
        var continueLoop = true
        while (continueLoop) {
            continueLoop = menuControllerOld.processSelection(showMenuAndGetSelection())
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
