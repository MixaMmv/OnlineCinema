package com.example.onlinecinema.di

import com.example.onlinecinema.data.api.MoviesApi
import com.example.onlinecinema.data.api.MoviesRemoteSource
import com.example.onlinecinema.data.api.MoviesRepository
import com.example.onlinecinema.data.api.MoviesRepositoryImpl
import com.example.onlinecinema.domain.MoviesInteractor
import com.example.onlinecinema.features.movies_list_screen.ui.MoviesListViewModel
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

const val BASE_URL = "https://gist.githubusercontent.com/LukyanovAnatoliy/"
//eca5141dedc79751b3d0b339649e06a3/raw/38f9419762adf27c34a3f052733b296385b6aa8d/
val appModule = module {

    single<OkHttpClient> {
        OkHttpClient.Builder().build()
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