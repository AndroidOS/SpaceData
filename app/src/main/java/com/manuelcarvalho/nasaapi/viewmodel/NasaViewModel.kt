package com.manuelcarvalho.nasaapi.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.casa.azul.dogs.viewmodel.BaseViewModel
import com.manuelcarvalho.nasaapi.model.NasaApiService
import com.manuelcarvalho.nasaapi.model.Photo
import com.manuelcarvalho.nasaapi.model.PhotoDatabase
import com.manuelcarvalho.nasaapi.model.Root
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


private const val TAG = "NasaViewModel"

class NasaViewModel(application: Application) : BaseViewModel(application) {

    val photoList = MutableLiveData<List<Photo>>()


    private val nasaservice = NasaApiService()
    private val disposable = CompositeDisposable()


    fun fetchFromRemote() {

        disposable.add(
            nasaservice.getData()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<Root>() {
                    override fun onSuccess(nasaList: Root) {

                        photoList.value = createNasaList(nasaList)

                        for (photo in photoList.value!!) {
                            Log.d(TAG, "List  ${photo.img_src}")
                        }
                    }

                    override fun onError(e: Throwable) {

                        Log.d(TAG, "RxJava Error ${e.printStackTrace()}")
                    }

                })
        )
    }

    fun fetchFromDatabase() {

        launch {
            val photos = PhotoDatabase(getApplication()).photoDao().getAllPhotos()

            Toast.makeText(
                getApplication(),
                "Photos retrieved from database",
                Toast.LENGTH_SHORT
            ).show()
        }
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

