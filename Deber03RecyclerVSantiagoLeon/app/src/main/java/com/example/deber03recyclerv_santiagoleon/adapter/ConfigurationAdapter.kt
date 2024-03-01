package com.example.deber03recyclerv_santiagoleon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03recyclerv_santiagoleon.Configuration
import com.example.deber03recyclerv_santiagoleon.R

class ConfigurationAdapter (private val configurationList:List<Configuration>) : RecyclerView.Adapter<ConfigurationViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfigurationViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return ConfigurationViewHolder(layoutInflater.inflate(R.layout.item_configuration, parent, false))
    }
    override fun getItemCount(): Int = configurationList.size
    override fun onBindViewHolder(holder: ConfigurationViewHolder, position: Int) {
        val item = configurationList[position]
        holder.render(item)
    }
}