import models.entities.Reserva
import models.dao.ReservaDAO
import java.text.SimpleDateFormat

fun main(args: Array<String>){
    val reservaDAO = ReservaDAO()
    val dateFormat = SimpleDateFormat("yyyy-MM-dd")

    // Test getAllReservas
    println("Testing getAllReservas:")
    reservaDAO.getAllReservas().forEach { println(it) }

    // Test saveReserva
    println("\nTesting saveReserva:")
    val newReserva = Reserva(5, "Test Client", dateFormat.parse("2024-08-01"), dateFormat.parse("2024-08-10"), 3, true)
    reservaDAO.saveReserva(newReserva)
    reservaDAO.getAllReservas().forEach { println(it) }

    // Test updateReserva
    println("\nTesting updateReserva:")
    val updatedReserva = Reserva(1, "Updated Client", dateFormat.parse("2024-05-12"), dateFormat.parse("2024-05-20"), 5, true)
    reservaDAO.updateReserva(1, updatedReserva)
    reservaDAO.getAllReservas().forEach { println(it) }

    // Test deleteReserva
    println("\nTesting deleteReserva:")
    reservaDAO.deleteReserva(2)
    reservaDAO.getAllReservas().forEach { println(it) }

    // Test findReservaById
    println("\nTesting findReservaById:")
    val foundReserva = reservaDAO.findReservaById(3)
    println(foundReserva ?: "Reserva no encontrada.")
}