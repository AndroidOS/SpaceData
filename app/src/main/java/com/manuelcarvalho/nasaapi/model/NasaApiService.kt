package com.manuelcarvalho.nasaapi.model

import io.reactivex.Single
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NasaApiService {

    private val BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?"

    private val api = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
            .create(NasaApi::class.java)

    fun getQuakes(): Single<Root> {
        return api.getData()
    }


}