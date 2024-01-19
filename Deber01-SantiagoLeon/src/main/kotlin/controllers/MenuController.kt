package controllers

import view.HotelView
import view.ReservaView

class MenuController(
    private val hotelView: HotelView,
    private val reservaView: ReservaView
) {
    fun processSelection(selection: Int): Boolean {
        return when (selection) {
            1 -> {
                hotelView.showMenu()
                true
            }
            2 -> {
                reservaView.showMenu()
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
