package controllers

import view.HotelViewOld
import view.ReservaViewOld

class MenuControllerOld(
    private val hotelViewOld: HotelViewOld,
    private val reservaViewOld: ReservaViewOld
) {
    fun processSelection(selection: Int): Boolean {
        return when (selection) {
            1 -> {
                hotelViewOld.showMenu()
                true
            }
            2 -> {
                reservaViewOld.showMenu()
                true
            }
            3 -> false
            else -> {
                println("Opción inválida, por favor intente de nuevo.")
                true
            }
        }
    }
}
