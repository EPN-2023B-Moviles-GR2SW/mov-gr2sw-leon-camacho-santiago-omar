package com.example.examen01_santiagoleon.views.Reservas

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen01_santiagoleon.R
import com.example.examen01_santiagoleon.views.Hoteles.HotelesActivity

class ReservasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)

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
}