package com.manuelcarvalho.nasaapi.viewmodel

import android.app.Application
import android.util.Log
import com.casa.azul.dogs.viewmodel.BaseViewModel
import com.manuelcarvalho.nasaapi.model.NasaApiService
import com.manuelcarvalho.nasaapi.model.Photo
import com.manuelcarvalho.nasaapi.model.Root
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers


private const val TAG = "NasaViewModel"

class NasaViewModel(application: Application) : BaseViewModel(application) {


    private val nasaservice = NasaApiService()
    private val disposable = CompositeDisposable()


    fun fetchFromRemote() {

        disposable.add(
                nasaservice.getData()
                        .subscribeOn(Schedulers.newThread())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribeWith(object : DisposableSingleObserver<Root>() {
                            override fun onSuccess(nasaList: Root) {

                                val photos = createNasaList(nasaList)
                                Log.d(TAG, "RxJava  ${photos}")
                                for (photo in photos) {
                                    Log.d(TAG, "List  ${photo.img_src}")
                                }
                            }

                            override fun onError(e: Throwable) {

                                Log.d(TAG, "RxJava Error ${e.printStackTrace()}")
                            }

                        })
        )
    }

    private fun createNasaList(nasaList: Root): List<Photo> {
        var list = mutableListOf<Photo>()
        for (q in nasaList.photos!!) {
            list.add(q)
        }
        return list
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}

