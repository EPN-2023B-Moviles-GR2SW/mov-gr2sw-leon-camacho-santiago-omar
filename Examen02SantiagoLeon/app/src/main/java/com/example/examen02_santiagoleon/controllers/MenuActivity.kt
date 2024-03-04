package com.example.examen02_santiagoleon.controllers

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen02_santiagoleon.R
import com.example.examen02_santiagoleon.controllers.Hoteles.HotelesActivity
import com.example.examen02_santiagoleon.controllers.Reservas.ReservasActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnRegresar = findViewById<Button>(R.id.btn_regresar_bienvenida)
        btnRegresar.setOnClickListener {
            finish()
        }

        val btnGestionHoteles = findViewById<Button>(R.id.btn_gestion_hoteles)
        btnGestionHoteles.setOnClickListener {
            val intentHoteles = Intent(this, HotelesActivity::class.java)
            startActivity(intentHoteles)
        }

        val btnGestionReservas = findViewById<Button>(R.id.btn_gestion_reservas)
        btnGestionReservas.setOnClickListener {
            val intentReservas = Intent(this, ReservasActivity::class.java)
            startActivity(intentReservas)
        }
    }
}

