package com.example.examen01_santiagoleon.controllers.Hoteles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen01_santiagoleon.R

class VerTodosHotelesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_todos_hoteles)

        val btnRegresarHoteles = findViewById<Button>(R.id.btn_hoteles_regresar_listar_gestionar)
        btnRegresarHoteles.setOnClickListener {
            finish()
        }
    }
}