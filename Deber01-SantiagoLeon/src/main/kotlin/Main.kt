import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import models.entities.Reserva
import utils.DateDeserializer
import utils.DateSerializer
import java.io.FileReader
import java.util.*

fun main(args: Array<String>){
    val gson = GsonBuilder()
        .registerTypeAdapter(Date::class.java, DateSerializer())
        .registerTypeAdapter(Date::class.java, DateDeserializer())
        .create()

    val fileReader = FileReader("src/main/kotlin/SampleData/reservas.json")
    val typeToken = object : TypeToken<List<Reserva>>() {}.type
    val reservas: List<Reserva> = gson.fromJson(fileReader, typeToken)

    reservas.forEach { println(it) }
}