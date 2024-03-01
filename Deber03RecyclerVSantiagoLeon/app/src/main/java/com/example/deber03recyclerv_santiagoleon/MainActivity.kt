package com.example.deber03recyclerv_santiagoleon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03recyclerv_santiagoleon.adapter.GameAdapter

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initRecyclerView()

        val buttonEnterSecondRV = findViewById<Button>(R.id.btnSecondRV)
        buttonEnterSecondRV.setOnClickListener {
            val intent = Intent(this, SecondRV::class.java)
            startActivity(intent)
        }
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerMainPage)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter = GameAdapter(GameProvider.gameList)
    }
}
