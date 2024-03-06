package com.example.deber02_santiagoleon.controllers.Reservas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.deber02_santiagoleon.R
import com.example.deber02_santiagoleon.models.dao.ReservaDAO
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Locale

class VerTodasReservasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_todas_reservas)

        val reservaDAO = ReservaDAO(this)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val listView = findViewById<ListView>(R.id.lv_todas_reservas)

        // Fetch all reservations across all hotels
        reservaDAO.getAllReservas({ reservas ->
            // onSuccess
            val formattedReservas = reservas.map { reserva ->
                "HotelID: ${reserva.hotelId}, Cliente: ${reserva.cliente}, " +
                        "Entrada: ${dateFormat.format(reserva.fechaEntrada.toDate())}, " +
                        "Salida: ${dateFormat.format(reserva.fechaSalida.toDate())}, " +
                        "Personas: ${reserva.numeroPersonas}, Cancelable: ${if (reserva.esCancelable) "Sí" else "No"}"
            }
            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, formattedReservas)
            listView.adapter = adapter
        }, { exception ->
            // onFailure
            mostrarSnackbar("Error al cargar todas las reservas: ${exception.message}")
        })

        val btnRegresarReservas = findViewById<Button>(R.id.btn_reservas_regresar_listar_gestionar)
        btnRegresarReservas.setOnClickListener {
            finish()
        }
    }

    private fun mostrarSnackbar(texto: String) {
        Snackbar.make(findViewById(R.id.contl_ver_todas_reservas_activity), texto, Snackbar.LENGTH_LONG).show()
    }
}