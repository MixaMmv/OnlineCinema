package com.example.onlinecinema.data.api.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.onlinecinema.data.api.local.MoviesEntity.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
data class MoviesEntity(
    @PrimaryKey
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "genres")
    val genres: List<String>?,
    @ColumnInfo(name = "id")
    val actors: List<String>?,
    @ColumnInfo(name = "title_alternative")
    val title_alternative: String?,
    @ColumnInfo(name = "title")
    val title: String?,
    @ColumnInfo(name = "year")
    val year: Int?,
    @ColumnInfo(name = "countries")
    val countries: List<String>?,
    @ColumnInfo(name = "description")
    val description: String?,
    @ColumnInfo(name = "premiere_world")
    val premiere_world: String?,
    @ColumnInfo(name = "premiere_russia")
    val premiere_russia: String?,
    @ColumnInfo(name = "poster")
    val poster: String?,
    @ColumnInfo(name = "trailer")
    val trailer: String?,
    @ColumnInfo(name = "rating")
    val rating: String?,
    @ColumnInfo(name = "isBookmarked")
    val isBookmarked: Boolean
) {
    companion object {
        const val TABLE_NAME = "MOVIES_TABLE"
    }
}