package com.example.examen01_santiagoleon.views

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.examen01_santiagoleon.R

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        val btnRegresar = findViewById<Button>(R.id.btn_regresar_bienvenida)
        btnRegresar.setOnClickListener {
            finish()
        }
    }
}