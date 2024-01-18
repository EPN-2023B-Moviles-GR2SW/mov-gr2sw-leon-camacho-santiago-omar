import models.dao.HotelDAO
import models.entities.Reserva
import models.dao.ReservaDAO
import models.entities.Hotel
import java.text.SimpleDateFormat

fun main(args: Array<String>){
    val reservaDAO = ReservaDAO()
    val hotelDAO = HotelDAO(reservaDAO)
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    // Test getAllHotels
    println("Testing getAllHotels:")
    hotelDAO.getAllHotels().forEach { println(it) }

    // Test saveHotel
    println("\nTesting saveHotel:")
    val newHotel = Hotel(201, "Test Hotel", "123 Test Street", 4.0, true, mutableListOf())
    hotelDAO.saveHotel(newHotel)
    hotelDAO.getAllHotels().forEach { println(it) }

    // Test updateHotel
    println("\nTesting updateHotel:")
    val updatedHotel = Hotel(201, "Updated Hotel", "123 Updated Street", 4.5, true, mutableListOf())
    hotelDAO.updateHotel(updatedHotel)
    hotelDAO.getAllHotels().forEach { println(it) }

    // Test deleteHotel
    println("\nTesting deleteHotel:")
    hotelDAO.deleteHotel(201)
    hotelDAO.getAllHotels().forEach { println(it) }

    // Test findHotelById
    println("\nTesting findHotelById:")
    val foundHotel = hotelDAO.findHotelById(101)
    println(foundHotel ?: "Hotel no encontrado.")

    // Optionally, test the loading of reservations for a specific hotel
    // This requires that reservaDAO and Reservas.json are correctly set up
    println("\nTesting loadReservationsForHotel (for Hotel ID 101):")
    val reservationsForHotel = hotelDAO.loadReservationsForHotel(101)
    reservationsForHotel.forEach { println(it) }
}