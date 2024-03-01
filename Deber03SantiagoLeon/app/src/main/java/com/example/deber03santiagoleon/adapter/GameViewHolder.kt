package com.example.deber03santiagoleon.adapter

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.deber03santiagoleon.Game
import com.example.deber03santiagoleon.R

class GameViewHolder(view:View): RecyclerView.ViewHolder(view) {

    val title = view.findViewById<TextView>(R.id.tvGameTitle)
    val price = view.findViewById<TextView>(R.id.tvGamePrice)
    val image = view.findViewById<ImageView>(R.id.ivGame)

    fun render(game: Game){
        title.text = game.title
        price.text = game.price
    }
}