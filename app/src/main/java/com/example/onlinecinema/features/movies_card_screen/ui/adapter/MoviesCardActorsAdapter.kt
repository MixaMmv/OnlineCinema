package com.example.onlinecinema.features.movies_card_screen.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.onlinecinema.R

class MoviesCardActorsAdapter(
    private var actors: List<String>?,
    ): RecyclerView.Adapter<MoviesCardActorsAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val name: TextView = view.findViewById(R.id.tvActorName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_actor, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.name.text = actors?.get(position)?.replace(" ", "\n")


    }

    override fun getItemCount(): Int = actors?.size ?: 0

//    fun updateMovies(newMovies: List<MoviesDomainModel>) {
//        movies = newMovies
//        notifyDataSetChanged()
//    }

}
