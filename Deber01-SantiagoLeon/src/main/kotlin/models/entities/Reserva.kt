package models.entities

import java.util.Date
import com.google.gson.annotations.SerializedName

data class Reserva(
    @SerializedName("id") val id: Int,
    @SerializedName("cliente") val cliente: String,
    @SerializedName("fechaEntrada") val fechaEntrada: Date,
    @SerializedName("fechaSalida") val fechaSalida: Date,
    @SerializedName("numeroPersonas") val numeroPersonas: Int,
    @SerializedName("esCancelable") val esCancelable: Boolean
)