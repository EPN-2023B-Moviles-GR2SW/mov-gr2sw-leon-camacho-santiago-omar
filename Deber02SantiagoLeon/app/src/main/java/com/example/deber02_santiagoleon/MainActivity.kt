package com.example.deber02_santiagoleon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.deber02_santiagoleon.controllers.MenuActivity

//import com.example.deber02_santiagoleon.controllers.MenuActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buttonEnterMenu = findViewById<Button>(R.id.btn_entrar_menu)
        buttonEnterMenu.setOnClickListener {
            val intent = Intent(this, MenuActivity::class.java)
            startActivity(intent)
        }
    }
}