package com.example.examen02_santiagoleon.controllers.Reservas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Switch
import com.example.examen02_santiagoleon.R
import com.example.examen02_santiagoleon.models.dao.ReservaDAO
import com.example.examen02_santiagoleon.models.entities.Reserva
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat

class ReservasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)

        val reservaDAO = ReservaDAO(this)
        val dateFormat = SimpleDateFormat("yyyy-MM-dd")

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
                val reserva = Reserva(
                    id = inputId.text.toString().toInt(),
                    cliente = inputCliente.text.toString(),
                    fechaEntrada = dateFormat.parse(inputFechaEntrada.text.toString())!!,
                    fechaSalida = dateFormat.parse(inputFechaSalida.text.toString())!!,
                    numeroPersonas = inputNumeroPersonas.text.toString().toInt(),
                    esCancelable = inputEsCancelable.isChecked,
                    hotelId = inputHotelId.text.toString().toInt()
                )
                reservaDAO.saveReserva(reserva)
                mostrarSnackbar("Reserva creada con éxito")
            } catch (e: Exception) {
                mostrarSnackbar("Error al crear reserva: ${e.message}")
            }
        }

        val btnActualizar = findViewById<Button>(R.id.btn_reservas_actualizar)
        btnActualizar.setOnClickListener {
            try {
                val reserva = Reserva(
                    id = inputId.text.toString().toInt(),
                    cliente = inputCliente.text.toString(),
                    fechaEntrada = dateFormat.parse(inputFechaEntrada.text.toString())!!,
                    fechaSalida = dateFormat.parse(inputFechaSalida.text.toString())!!,
                    numeroPersonas = inputNumeroPersonas.text.toString().toInt(),
                    esCancelable = inputEsCancelable.isChecked,
                    hotelId = inputHotelId.text.toString().toInt()
                )
                reservaDAO.updateReserva(reserva)
                mostrarSnackbar("Reserva actualizada con éxito")
            } catch (e: Exception) {
                mostrarSnackbar("Error al actualizar reserva: ${e.message}")
            }
        }

        val btnEliminar = findViewById<Button>(R.id.btn_reservas_eliminar)
        btnEliminar.setOnClickListener {
            try {
                val reservaId = inputId.text.toString().toInt()
                reservaDAO.deleteReserva(reservaId)
                mostrarSnackbar("Reserva eliminada con éxito")
            } catch (e: Exception) {
                mostrarSnackbar("Error al eliminar reserva: ${e.message}")
            }
        }

        val btnBuscarPorID = findViewById<Button>(R.id.btn_reservas_buscar_id)
        btnBuscarPorID.setOnClickListener {
            try {
                val reservaId = inputId.text.toString().toInt()
                val reserva = reservaDAO.findReservaById(reservaId)
                if (reserva != null) {
                    inputCliente.setText(reserva.cliente)
                    inputFechaEntrada.setText(dateFormat.format(reserva.fechaEntrada))
                    inputFechaSalida.setText(dateFormat.format(reserva.fechaSalida))
                    inputNumeroPersonas.setText(reserva.numeroPersonas.toString())
                    inputEsCancelable.isChecked = reserva.esCancelable
                    inputHotelId.setText(reserva.hotelId.toString())
                } else {
                    mostrarSnackbar("Reserva no encontrada")
                }
            } catch (e: Exception) {
                mostrarSnackbar("Error al buscar reserva: ${e.message}")
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