//package com.example.onlinecinema.data.api.local
//
//import androidx.room.*
//
//@Dao
//interface MoviesDAO {
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun create(entity: MoviesEntity)
//
//    @Query("SELECT * FROM ${MoviesEntity.TABLE_NAME}")
//    suspend fun read(): List<MoviesEntity>
//
//    @Update
//    suspend fun update(entity: MoviesEntity)
//
//    @Delete
//    suspend fun delete(entity: MoviesEntity)
//
//}