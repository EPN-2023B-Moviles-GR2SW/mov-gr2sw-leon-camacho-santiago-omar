import controllers.HotelController
import controllers.ReservaController
import models.dao.HotelDAO
import models.dao.ReservaDAO
import view.MenuView

fun main() {

    val reservaDAO = ReservaDAO()
    val hotelDAO = HotelDAO(reservaDAO)

    val hotelController = HotelController(hotelDAO)
    val reservaController = ReservaController(reservaDAO, hotelController)

    val menuView = MenuView(hotelController, reservaController)
    menuView.show()
}
