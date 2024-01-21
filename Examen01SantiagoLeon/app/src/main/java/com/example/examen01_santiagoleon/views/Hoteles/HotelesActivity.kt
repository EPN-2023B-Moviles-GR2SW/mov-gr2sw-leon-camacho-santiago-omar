package com.example.examen01_santiagoleon.views.Hoteles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen01_santiagoleon.R

class HotelesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hoteles)

        val btnRegresarHoteles = findViewById<Button>(R.id.btn_regresar_hoteles_menu)
        btnRegresarHoteles.setOnClickListener {
            finish()
        }
    }
}