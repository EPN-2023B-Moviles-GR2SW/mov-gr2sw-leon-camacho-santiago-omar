package com.example.deber02_santiagoleon.controllers.Hoteles

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import com.example.deber02_santiagoleon.R
import com.example.deber02_santiagoleon.models.dao.HotelDAO
import com.google.android.material.snackbar.Snackbar

class VerTodosHotelesActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ver_todos_hoteles)

        val hotelDAO = HotelDAO(this)
        val listView = findViewById<ListView>(R.id.lv_todos_hoteles)

        // Fetch all hotels from Firestore and update the UI in the onSuccess callback
        hotelDAO.getAllHotels({ todosHoteles ->
            // onSuccess
            runOnUiThread {
                val adapter = ArrayAdapter(
                    this@VerTodosHotelesActivity,
                    android.R.layout.simple_list_item_1,
                    todosHoteles.map { it.toStringNoReservas() }
                )
                listView.adapter = adapter
            }
        }, { exception ->
            // onFailure
            runOnUiThread {
                mostrarSnackbar("Error al cargar hoteles: ${exception.message}")
            }
        })

        val btnRegresarHoteles = findViewById<Button>(R.id.btn_hoteles_regresar_listar_gestionar)
        btnRegresarHoteles.setOnClickListener {
            finish()
        }
    }

    private fun mostrarSnackbar(texto: String) {
        Snackbar.make(findViewById(R.id.contl_ver_todos_hoteles_activity), texto, Snackbar.LENGTH_LONG).show()
    }
}