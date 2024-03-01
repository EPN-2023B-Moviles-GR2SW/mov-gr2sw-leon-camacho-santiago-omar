package com.example.deber03recyclerv_santiagoleon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03recyclerv_santiagoleon.adapter.ConfigurationAdapter
import com.example.deber03recyclerv_santiagoleon.adapter.GameAdapter

class SecondRV : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second_rv)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerSecond)
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        recyclerView.adapter = ConfigurationAdapter(ConfigurationProvider.configurationList)
    }
}