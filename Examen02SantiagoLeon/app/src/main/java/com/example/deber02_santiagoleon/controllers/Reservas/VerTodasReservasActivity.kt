package com.example.deber02_santiagoleon.controllers.Reservas

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.deber02_santiagoleon.R
import com.example.deber02_santiagoleon.models.dao.ReservaDAO

class VerTodasReservasActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_todas_reservas)

        val reservaDAO = ReservaDAO(this)
        val todasReservas = reservaDAO.getAllReservas()

        val listView = findViewById<ListView>(R.id.lv_todas_reservas)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todasReservas.map { it.toString() })
        listView.adapter = adapter

        val btnRegresarReservas = findViewById<Button>(R.id.btn_reservas_regresar_listar_gestionar)
        btnRegresarReservas.setOnClickListener {
            finish()
        }
    }
}