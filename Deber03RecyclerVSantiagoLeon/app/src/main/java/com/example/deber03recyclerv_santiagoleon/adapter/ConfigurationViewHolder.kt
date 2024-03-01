package com.example.deber03recyclerv_santiagoleon.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.deber03recyclerv_santiagoleon.Configuration
import com.example.deber03recyclerv_santiagoleon.R

class ConfigurationViewHolder(view: View): RecyclerView.ViewHolder(view) {
    val icon = view.findViewById<ImageView>(R.id.ivConfiguration)
    val name = view.findViewById<TextView>(R.id.tvName)

    fun render(configuration: Configuration){
        Glide.with(icon.context).load(configuration.icon).into(icon)
        name.text = configuration.name
    }
}