package com.example.catapp.di

import com.example.catapp.data.local.CatRepository
import com.example.catapp.data.local.CatsDatabase
import com.example.catapp.data.remote.CatApiService
import com.example.catapp.ui.cats.CatViewModel
import com.example.catapp.utils.NetworkUtils
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val appModule = module {

    single {
        Retrofit.Builder()
            .baseUrl("https://thecatapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()
    }

    single<CatApiService> {
        get<Retrofit>().create(CatApiService::class.java)
    }

    single {
        CatsDatabase.getDatabase(get())
    }

    single {
        get<CatsDatabase>().catDao()
    }

    single {
        CatRepository(
            catDao = get(),
            apiService = get()
        )
    }

    single {
        NetworkUtils(androidContext())
    }

    viewModel {
        CatViewModel(
            repository = get(),
            networkUtils = get()
        )
    }
}