package com.manuelcarvalho.nasaapi.model

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

interface NasaApi {

    @Headers(
            "Accept: application/json",
            "Content-type:application/json"
    )

    //https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?
    @GET("sol=1000&camera=fhaz&api_key=DEMO_KEY")
    fun getData(): Single<Root>


}

