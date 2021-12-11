package com.example.onlinecinema.features.movies_list_screen.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinecinema.R
import com.example.onlinecinema.domain.model.MoviesDomainModel

class MoviesAdapter(
    private var movies: List<MoviesDomainModel>,
    private val onCardClick: (movies: MoviesDomainModel) -> Unit,

): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.ivMovieCard)
        val title: TextView = view.findViewById(R.id.tvTitle)
        val year: TextView = view.findViewById(R.id.tvYear)
        val genre: TextView = view.findViewById(R.id.tvGenre)
        val rating: TextView = view.findViewById(R.id.tvRating)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Glide.with(holder.itemView).load(movies[position].poster).into(holder.image)
        holder.title.text = movies[position].title
        holder.year.text = movies[position].year?.toString() ?: ""
        holder.genre.text = movies[position].genres?.get(0) ?: ""
        holder.rating.text = movies[position].rating

        holder.itemView.setOnClickListener{
            onCardClick(movies[position])

        }

    }

    override fun getItemCount() = movies.size

    fun updateMovies(newMovies: List<MoviesDomainModel>) {
        movies = newMovies
        notifyDataSetChanged()
    }

}

