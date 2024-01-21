package com.example.examen01_santiagoleon.views.Reservas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen01_santiagoleon.R

class ReservasPorHotelActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas_por_hotel)

        val btnRegresarReservas = findViewById<Button>(R.id.btn_reservas_regresar_PorHotel_gestionar)
        btnRegresarReservas.setOnClickListener {
            finish()
        }
    }
}