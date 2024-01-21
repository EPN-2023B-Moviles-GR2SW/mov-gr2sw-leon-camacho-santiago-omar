package com.example.examen01_santiagoleon.views.Reservas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen01_santiagoleon.R

class ReservasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_reservas)

        val btnRegresarReservas = findViewById<Button>(R.id.btn_regresar_reservas_menu)
        btnRegresarReservas.setOnClickListener {
            finish()
        }
    }
}