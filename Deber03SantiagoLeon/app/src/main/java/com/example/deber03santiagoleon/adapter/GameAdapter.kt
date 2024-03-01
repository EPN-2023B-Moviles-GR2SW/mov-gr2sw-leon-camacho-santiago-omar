package com.example.deber03santiagoleon.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.deber03santiagoleon.Game
import com.example.deber03santiagoleon.R

class GameAdapter(private val gameList:List<Game>) : RecyclerView.Adapter<GameViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return GameViewHolder(layoutInflater.inflate(R.layout.item_game, parent, false))
    }
    override fun getItemCount(): Int = gameList.size
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val item = gameList[position]
        holder.render(item)
    }
}