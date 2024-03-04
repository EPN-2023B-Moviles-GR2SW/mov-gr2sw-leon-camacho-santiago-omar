package com.example.deber02_santiagoleon.controllers.Hoteles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.Switch
import com.example.deber02_santiagoleon.R
import com.example.deber02_santiagoleon.models.dao.HotelDAO
import com.example.deber02_santiagoleon.models.entities.Hotel
import com.google.android.material.snackbar.Snackbar

class HotelesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hoteles)

        val hotelDAO = HotelDAO(this)
        val listView = findViewById<ListView>(R.id.lv_lista_reservas_de_hotel)

        val inputId = findViewById<EditText>(R.id.input_id)
        val inputNombre = findViewById<EditText>(R.id.input_nombre)
        val inputDireccion = findViewById<EditText>(R.id.input_direccion)
        val inputCalificacion = findViewById<EditText>(R.id.input_calificacion)
        val inputTieneEstacionamiento = findViewById<Switch>(R.id.input_tiene_estacionamiento)

        val btnCrear = findViewById<Button>(R.id.btn_hoteles_crear)
        btnCrear.setOnClickListener {
            try {
                val newHotel = Hotel(
                    id = inputId.text.toString().toInt(),
                    nombre = inputNombre.text.toString(),
                    direccion = inputDireccion.text.toString(),
                    calificacion = inputCalificacion.text.toString().toDouble(),
                    tieneEstacionamiento = inputTieneEstacionamiento.isChecked,
                    reservas = mutableListOf()
                )
                hotelDAO.saveHotel(newHotel)
                mostrarSnackbar("Hotel creado con éxito")
            } catch (e: Exception) {
                mostrarSnackbar("Error al crear hotel: ${e.message}")
            }
        }

        val btnActualizar = findViewById<Button>(R.id.btn_hoteles_actualizar)
        btnActualizar.setOnClickListener {
            try {
                val updatedHotel = Hotel(
                    id = inputId.text.toString().toInt(),
                    nombre = inputNombre.text.toString(),
                    direccion = inputDireccion.text.toString(),
                    calificacion = inputCalificacion.text.toString().toDouble(),
                    tieneEstacionamiento = inputTieneEstacionamiento.isChecked,
                    reservas = null  // The reservations list will not be updated here
                )
                hotelDAO.updateHotel(updatedHotel)
                mostrarSnackbar("Hotel actualizado con éxito")
            } catch (e: Exception) {
                mostrarSnackbar("Error al actualizar hotel: ${e.message}")
            }
        }

        val btnEliminar = findViewById<Button>(R.id.btn_hoteles_eliminar)
        btnEliminar.setOnClickListener {
            try {
                val hotelId = inputId.text.toString().toInt()
                hotelDAO.deleteHotel(hotelId)
                mostrarSnackbar("Hotel eliminado con éxito")
            } catch (e: Exception) {
                mostrarSnackbar("Error al eliminar hotel: ${e.message}")
            }
        }

        val btnBuscarPorID = findViewById<Button>(R.id.btn_hoteles_buscar_id)
        btnBuscarPorID.setOnClickListener {
            val hotelId = inputId.text.toString().toIntOrNull()
            if (hotelId != null) {
                val hotel = hotelDAO.findHotelById(hotelId)
                if (hotel != null) {
                    // Update UI with the details of the found hotel
                    inputNombre.setText(hotel.nombre)
                    inputDireccion.setText(hotel.direccion)
                    inputCalificacion.setText(hotel.calificacion.toString())
                    inputTieneEstacionamiento.isChecked = hotel.tieneEstacionamiento

                    // Load and display associated reservations
                    val reservas = hotelDAO.loadReservationsForHotel(hotelId)
                    if (reservas.isNotEmpty()) {
                        val adapter = ArrayAdapter(
                            this,
                            android.R.layout.simple_list_item_1,
                            reservas.map { it.toString() }
                        )
                        listView.adapter = adapter
                    } else {
                        mostrarSnackbar("No hay reservas para este hotel")
                    }
                } else {
                    mostrarSnackbar("Hotel con ID: $hotelId no encontrado")
                }
            } else {
                mostrarSnackbar("Ingrese un ID de hotel válido")
            }
        }

        val btnRegresarHoteles = findViewById<Button>(R.id.btn_regresar_hoteles_menu)
        btnRegresarHoteles.setOnClickListener {
            finish()
        }

        val btnVerTodosHoteles = findViewById<Button>(R.id.btn_ver_todos_hoteles)
        btnVerTodosHoteles.setOnClickListener {
            val intentVerTodosHoteles = Intent(this, VerTodosHotelesActivity::class.java)
            startActivity(intentVerTodosHoteles)
        }
    }

    private fun mostrarSnackbar(texto: String) {
        Snackbar.make(findViewById(R.id.constl_hoteles_principal), texto, Snackbar.LENGTH_LONG).show()
    }
}