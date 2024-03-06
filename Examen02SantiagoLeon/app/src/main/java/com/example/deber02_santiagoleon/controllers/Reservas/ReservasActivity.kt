package com.example.deber02_santiagoleon.controllers.Reservas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import com.example.deber02_santiagoleon.R
import com.example.deber02_santiagoleon.models.dao.ReservaDAO
import com.example.deber02_santiagoleon.models.entities.Reserva
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.Timestamp
import java.text.SimpleDateFormat
import java.util.Locale

class ReservasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)

        val reservaDAO = ReservaDAO(this)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())

        val inputId = findViewById<EditText>(R.id.input_id_reservas)
        val inputCliente = findViewById<EditText>(R.id.input_cliente)
        val inputFechaEntrada = findViewById<EditText>(R.id.input_fecha_entrada)
        val inputFechaSalida = findViewById<EditText>(R.id.input_fecha_salida)
        val inputNumeroPersonas = findViewById<EditText>(R.id.input_numero_personas)
        val inputEsCancelable = findViewById<Switch>(R.id.input_es_cancelable)
        val inputHotelId = findViewById<EditText>(R.id.input_hotel_id)

        val btnCrear = findViewById<Button>(R.id.btn_reservas_crear)
        btnCrear.setOnClickListener {
            try {
                val fechaEntrada = Timestamp(dateFormat.parse(inputFechaEntrada.text.toString())!!)
                val fechaSalida = Timestamp(dateFormat.parse(inputFechaSalida.text.toString())!!)
                val reserva = Reserva(
                    cliente = inputCliente.text.toString(),
                    fechaEntrada = fechaEntrada,
                    fechaSalida = fechaSalida,
                    numeroPersonas = inputNumeroPersonas.text.toString().toInt(),
                    esCancelable = inputEsCancelable.isChecked,
                    hotelId = inputHotelId.text.toString() // Assuming hotelId is a String
                )
                reservaDAO.saveReserva(reserva.hotelId, reserva, {
                    mostrarSnackbar("Reserva creada con éxito")
                }) {
                    mostrarSnackbar("Error al crear reserva: ${it.message}")
                }
            } catch (e: Exception) {
                mostrarSnackbar("Error al crear reserva: ${e.message}")
            }
        }

        val btnActualizar = findViewById<Button>(R.id.btn_reservas_actualizar)
        btnActualizar.setOnClickListener {
            val hotelId = inputHotelId.text.toString()
            val reservaId = inputId.text.toString() // Assuming you have an input field for the reservation ID
            if (hotelId.isNotEmpty() && reservaId.isNotEmpty()) {
                val updatedFields = mapOf(
                    "cliente" to inputCliente.text.toString(),
                    "fechaEntrada" to Timestamp(dateFormat.parse(inputFechaEntrada.text.toString())!!),
                    "fechaSalida" to Timestamp(dateFormat.parse(inputFechaSalida.text.toString())!!),
                    "numeroPersonas" to inputNumeroPersonas.text.toString().toInt(),
                    "esCancelable" to inputEsCancelable.isChecked
                )
                reservaDAO.updateReserva(hotelId, reservaId, updatedFields, {
                    mostrarSnackbar("Reserva actualizada con éxito")
                }) {
                    mostrarSnackbar("Error al actualizar reserva: ${it.message}")
                }
            } else {
                mostrarSnackbar("Error: ID de hotel o reserva está vacío")
            }
        }

        val btnEliminar = findViewById<Button>(R.id.btn_reservas_eliminar)
        btnEliminar.setOnClickListener {
            val hotelId = inputHotelId.text.toString()
            val reservaId = inputId.text.toString() // Again, assuming this is the unique reservation ID
            if (hotelId.isNotEmpty() && reservaId.isNotEmpty()) {
                reservaDAO.deleteReserva(hotelId, reservaId, {
                    mostrarSnackbar("Reserva eliminada con éxito")
                }) {
                    mostrarSnackbar("Error al eliminar reserva: ${it.message}")
                }
            } else {
                mostrarSnackbar("Error: ID de hotel o reserva está vacío")
            }
        }

        val btnBuscarPorID = findViewById<Button>(R.id.btn_reservas_buscar_id)
        btnBuscarPorID.setOnClickListener {
            val hotelId = inputHotelId.text.toString()
            val reservaId = inputId.text.toString()
            if (hotelId.isNotEmpty() && reservaId.isNotEmpty()) {
                reservaDAO.findReservaById(hotelId, reservaId, { reserva ->
                    reserva?.let {
                        inputCliente.setText(it.cliente)
                        inputFechaEntrada.setText(dateFormat.format(it.fechaEntrada.toDate()))
                        inputFechaSalida.setText(dateFormat.format(it.fechaSalida.toDate()))
                        inputNumeroPersonas.setText(it.numeroPersonas.toString())
                        inputEsCancelable.isChecked = it.esCancelable
                    } ?: mostrarSnackbar("Reserva no encontrada")
                }) {
                    mostrarSnackbar("Error al buscar reserva: ${it.message}")
                }
            } else {
                mostrarSnackbar("Por favor, ingrese un ID de hotel y reserva válido")
            }
        }

        val btnRegresarReservas = findViewById<Button>(R.id.btn_regresar_reservas_menu)
        btnRegresarReservas.setOnClickListener {
            finish()
        }

        val btnVerTodasReservas = findViewById<Button>(R.id.btn_ver_todas_reservas)
        btnVerTodasReservas.setOnClickListener {
            val intentVerTodasReservas = Intent(this, VerTodasReservasActivity::class.java)
            startActivity(intentVerTodasReservas)
        }

        val btnReservasPorHotel = findViewById<Button>(R.id.btn_reservas_listar_id_hotel)
        btnReservasPorHotel.setOnClickListener {
            val intentReservasPorHotel = Intent(this, ReservasPorHotelActivity::class.java)
            startActivity(intentReservasPorHotel)
        }
    }

    private fun mostrarSnackbar(texto: String) {
        Snackbar.make(findViewById(R.id.constl_reservas_principal), texto, Snackbar.LENGTH_LONG).show()
    }
}