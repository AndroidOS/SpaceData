package com.manuelcarvalho.nasaapi.viewmodel

import android.app.Application
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import com.casa.azul.dogs.viewmodel.BaseViewModel
import com.manuelcarvalho.nasaapi.model.*
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

//    private fun storePhotosLocally(photos: Root) {
//        launch {
//            val list = createPhotoList(photos)
//            val dao = PhotoDatabase(getApplication()).photoDao()
//            dao.deleteAllPhotos()
//            val result = dao.insertAll(*list.toTypedArray())
//            var i = 0
////            while (i < list.size) {
////                list[i].uuid = result[i].
////                ++i
////            }
//            //fetchFromDatabase()
//        }
//    }

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

    private fun createPhotoList(photosList: Root): List<Photo1> {
        var list = mutableListOf<Photo1>()
        for (q in photosList.photos!!) {
            list.add(Photo1(q.img_src))
        }
        return list
    }


}

