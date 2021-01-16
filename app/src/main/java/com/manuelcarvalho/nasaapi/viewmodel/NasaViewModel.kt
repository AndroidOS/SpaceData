package com.manuelcarvalho.nasaapi.viewmodel

import android.app.Application
import android.util.Log
import com.casa.azul.dogs.viewmodel.BaseViewModel
import com.manuelcarvalho.nasaapi.model.NasaApiService
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

                        Log.d(TAG, "RxJava  ${nasaList.photos}")
                    }

                    override fun onError(e: Throwable) {

                        Log.d(TAG, "RxJava Error ${e.printStackTrace()}")
                    }

                })
        )
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }


}

