package com.example.deber02_santiagoleon.controllers.Reservas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.deber02_santiagoleon.R
import com.example.deber02_santiagoleon.models.dao.ReservaDAO
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Locale

class ReservasPorHotelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas_por_hotel)

        val reservaDAO = ReservaDAO(this)
        val inputHotelID = findViewById<EditText>(R.id.input_buscar_reservas_ID_hotel)
        val btnBuscar = findViewById<Button>(R.id.btn_buscar_reservas_ID_hotel)
        val listView = findViewById<ListView>(R.id.lv_PorHotel_reservas)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        btnBuscar.setOnClickListener {
            val hotelId = inputHotelID.text.toString()
            if (hotelId.isNotEmpty()) {
                reservaDAO.getReservationsByHotelId(hotelId, { reservas ->
                    // onSuccess
                    val formattedReservas = reservas.map { reserva ->
                        "Cliente: ${reserva.cliente}, " +
                                "Entrada: ${dateFormat.format(reserva.fechaEntrada.toDate())}, " +
                                "Salida: ${dateFormat.format(reserva.fechaSalida.toDate())}, " +
                                "Personas: ${reserva.numeroPersonas}, " +
                                "Cancelable: ${if (reserva.esCancelable) "Sí" else "No"}"
                    }
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, formattedReservas)
                    listView.adapter = adapter
                }, { exception ->
                    // onFailure
                    mostrarSnackbar("Error al cargar reservas: ${exception.message}")
                })
            } else {
                mostrarSnackbar("Ingrese un ID de hotel válido")
            }
        }

        val btnRegresarReservas = findViewById<Button>(R.id.btn_reservas_regresar_PorHotel_gestionar)
        btnRegresarReservas.setOnClickListener {
            finish()
        }
    }

    private fun mostrarSnackbar(texto: String) {
        Snackbar.make(findViewById(R.id.constl_reservas_por_hotel), texto, Snackbar.LENGTH_LONG).show()
    }
}