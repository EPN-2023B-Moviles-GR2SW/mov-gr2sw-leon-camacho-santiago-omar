package com.example.examen01_santiagoleon.controllers.Reservas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import com.example.examen01_santiagoleon.R
import com.example.examen01_santiagoleon.models.dao.ReservaDAO
import com.google.android.material.snackbar.Snackbar

class ReservasPorHotelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas_por_hotel)

        val reservaDAO = ReservaDAO()
        val inputHotelID = findViewById<EditText>(R.id.input_buscar_reservas_ID_hotel)
        val btnBuscar = findViewById<Button>(R.id.btn_buscar_reservas_ID_hotel)
        val listView = findViewById<ListView>(R.id.lv_PorHotel_reservas)

        btnBuscar.setOnClickListener {
            val hotelId = inputHotelID.text.toString().toIntOrNull()
            if (hotelId != null) {
                val reservasList = reservaDAO.getReservationsByHotelId(hotelId)
                if (reservasList.isNotEmpty()) {
                    val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, reservasList.map { it.toString() })
                    listView.adapter = adapter
                } else {
                    mostrarSnackbar("No existen reservas para el hotel con ID: $hotelId")
                }
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