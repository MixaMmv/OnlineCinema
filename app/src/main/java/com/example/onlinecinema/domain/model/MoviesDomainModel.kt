package com.example.onlinecinema.domain.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class MoviesDomainModel(
    val id: Int,
    val genres: List<String>?,
    val actors: List<String>?,
    val title_alternative: String?,
    val title: String?,
    val year: Int?,
    val countries: List<String>?,
    val description: String?,
    val premiere_world: String?,
    val premiere_russia: String?,
    val poster: String?,
    val trailer: String?,
    val rating: String?,
    val isBookmarked: Boolean = false
) : Parcelable