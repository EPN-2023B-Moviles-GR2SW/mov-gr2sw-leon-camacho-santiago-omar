package com.example.examen01_santiagoleon.views.Hoteles

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen01_santiagoleon.R
import com.example.examen01_santiagoleon.views.Reservas.VerTodasReservasActivity

class HotelesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hoteles)

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
}