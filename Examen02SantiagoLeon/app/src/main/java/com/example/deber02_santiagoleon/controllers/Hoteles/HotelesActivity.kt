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
            val newHotel = Hotel(
                nombre = inputNombre.text.toString(),
                direccion = inputDireccion.text.toString(),
                calificacion = inputCalificacion.text.toString().toDouble(),
                tieneEstacionamiento = inputTieneEstacionamiento.isChecked
            )
            hotelDAO.saveHotel(newHotel, {
                mostrarSnackbar("Hotel creado con éxito")
            }) {
                mostrarSnackbar("Error al crear hotel: ${it.message}")
            }
        }

        val btnActualizar = findViewById<Button>(R.id.btn_hoteles_actualizar)
        btnActualizar.setOnClickListener {
            inputId.text.toString().let { hotelId ->
                if (hotelId.isNotEmpty()) {
                    val updatedFields = mapOf(
                        "nombre" to inputNombre.text.toString(),
                        "direccion" to inputDireccion.text.toString(),
                        "calificacion" to inputCalificacion.text.toString().toDouble(),
                        "tieneEstacionamiento" to inputTieneEstacionamiento.isChecked
                    )
                    hotelDAO.updateHotel(hotelId, updatedFields, {
                        mostrarSnackbar("Hotel actualizado con éxito")
                    }) {
                        mostrarSnackbar("Error al actualizar hotel: ${it.message}")
                    }
                } else {
                    mostrarSnackbar("Ingrese un ID válido para actualizar")
                }
            }
        }

        val btnEliminar = findViewById<Button>(R.id.btn_hoteles_eliminar)
        btnEliminar.setOnClickListener {
            inputId.text.toString().let { hotelId ->
                if (hotelId.isNotEmpty()) {
                    hotelDAO.deleteHotel(hotelId, {
                        mostrarSnackbar("Hotel eliminado con éxito")
                    }) {
                        mostrarSnackbar("Error al eliminar hotel: ${it.message}")
                    }
                } else {
                    mostrarSnackbar("Ingrese un ID válido para eliminar")
                }
            }
        }

        val btnBuscarPorID = findViewById<Button>(R.id.btn_hoteles_buscar_id)
        btnBuscarPorID.setOnClickListener {
            inputId.text.toString().let { hotelId ->
                if (hotelId.isNotEmpty()) {
                    hotelDAO.findHotelById(hotelId, { hotel ->
                        hotel?.let {
                            inputNombre.setText(it.nombre)
                            inputDireccion.setText(it.direccion)
                            inputCalificacion.setText(it.calificacion.toString())
                            inputTieneEstacionamiento.isChecked = it.tieneEstacionamiento

                            hotelDAO.loadReservationsForHotel(hotelId, { reservas ->
                                val adapter = ArrayAdapter(
                                    this@HotelesActivity,
                                    android.R.layout.simple_list_item_1,
                                    reservas.map { reserva -> "Cliente: ${reserva.cliente}, Fecha Entrada: ${reserva.fechaEntrada.toDate()}" }
                                )
                                listView.adapter = adapter
                            }) {
                                mostrarSnackbar("Error al cargar reservas: ${it.message}")
                            }
                        } ?: mostrarSnackbar("Hotel con ID: $hotelId no encontrado")
                    }) {
                        mostrarSnackbar("Error al buscar hotel: ${it.message}")
                    }
                } else {
                    mostrarSnackbar("Ingrese un ID de hotel válido")
                }
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