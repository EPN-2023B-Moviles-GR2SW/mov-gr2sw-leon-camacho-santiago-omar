package com.example.examen02_santiagoleon.controllers.Hoteles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.examen02_santiagoleon.R
import com.example.examen02_santiagoleon.models.dao.HotelDAO

class VerTodosHotelesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_todos_hoteles)

        val hotelDAO = HotelDAO(this)
        val todosHoteles = hotelDAO.getAllHotels()

        val listView = findViewById<ListView>(R.id.lv_todos_hoteles)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, todosHoteles.map { it.toStringNoReservas() })
        listView.adapter = adapter

        val btnRegresarHoteles = findViewById<Button>(R.id.btn_hoteles_regresar_listar_gestionar)
        btnRegresarHoteles.setOnClickListener {
            finish()
        }
    }
}