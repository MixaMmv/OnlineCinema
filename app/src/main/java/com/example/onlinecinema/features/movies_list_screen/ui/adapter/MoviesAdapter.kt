package com.example.onlinecinema.features.movies_list_screen.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.onlinecinema.R
import com.example.onlinecinema.domain.model.MoviesDomainModel
import com.example.onlinecinema.features.movies_list_screen.ui.UiEvent

class MoviesAdapter(
    private var movies: List<MoviesDomainModel>,
    private val onCardClick: (movies: MoviesDomainModel) -> Unit
): RecyclerView.Adapter<MoviesAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val image: ImageView = view.findViewById(R.id.ivMovieCard)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView).load(movies[position].posterPath).into(holder.image)

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
