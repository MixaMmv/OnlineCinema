package com.example.onlinecinema.di

import androidx.room.Room
import com.example.onlinecinema.MoviesDataBase
import com.example.onlinecinema.data.api.MoviesApi
import com.example.onlinecinema.data.api.MoviesRemoteSource
import com.example.onlinecinema.data.api.MoviesRepository
import com.example.onlinecinema.data.api.MoviesRepositoryImpl
import com.example.onlinecinema.data.api.local.MoviesDAO
import com.example.onlinecinema.domain.MoviesInteractor
import com.example.onlinecinema.features.movies_list_screen.ui.MoviesListViewModel
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

//GsonBuilder().setLenient().create()

//const val BASE_URL = "https://gist.githubusercontent.com/LukyanovAnatoliy/"
//const val BASE_URL = "https://api.kinopoisk.cloud/"
const val BASE_URL = "https://raw.githubusercontent.com/MixaMmv/"

val appModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder().addInterceptor(HttpLoggingInterceptor().apply {HttpLoggingInterceptor.Level.BODY}).build()
    }


    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
    }

    single<MoviesApi> {
        get<Retrofit>().create()
    }

    single<MoviesRemoteSource> {
        MoviesRemoteSource(get<MoviesApi>())
    }

    single<MoviesRepository> {
        MoviesRepositoryImpl(get<MoviesRemoteSource>())
    }


    single<MoviesInteractor> {
        MoviesInteractor(get<MoviesRepository>())
    }

    viewModel<MoviesListViewModel> {
        MoviesListViewModel(get<MoviesInteractor>())
    }

}

const val DATA_BASE = "DATA_BASE"

val dataBaseModule = module {
    single {
        Room.databaseBuilder(androidContext(), MoviesDataBase::class.java, DATA_BASE)
            .fallbackToDestructiveMigration().build()
    }

    single {
        get<MoviesDataBase>().moviesDAO()
    }

}