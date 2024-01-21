package com.example.examen01_santiagoleon.views.Reservas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen01_santiagoleon.R

class VerTodasReservasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_todas_reservas)

        val btnRegresarReservas = findViewById<Button>(R.id.btn_reservas_regresar_listar_gestionar)
        btnRegresarReservas.setOnClickListener {
            finish()
        }
    }
}