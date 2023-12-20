package manager

import model.Hotel

class HotelManager {
    private val hoteles = mutableListOf<Hotel>()

    fun agregarHotel(hotel: Hotel) {
        hoteles.add(hotel)
    }

    fun mostrarHoteles() {
        hoteles.forEach { println(it) }
    }

}