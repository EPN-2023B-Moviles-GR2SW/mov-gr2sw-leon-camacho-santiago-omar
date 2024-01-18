package models.entities

import com.google.gson.annotations.SerializedName

data class Hotel(
    @SerializedName("id") val id: Int,
    @SerializedName("nombre") val nombre: String,
    @SerializedName("direccion") val direccion: String,
    @SerializedName("calificacion") val calificacion: Double,
    @SerializedName("tieneEstacionamiento") val tieneEstacionamiento: Boolean,
    @SerializedName("reservas") val reservas: MutableList<Reserva>
)