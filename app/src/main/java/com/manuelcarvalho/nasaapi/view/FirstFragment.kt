package com.manuelcarvalho.nasaapi.view

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.manuelcarvalho.nasaapi.R
import com.manuelcarvalho.nasaapi.viewmodel.NasaViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.fragment_first.*

private const val TAG = "FirstFragment"
class FirstFragment : Fragment() {

    private lateinit var viewModel: NasaViewModel



    //private var listURL =  MutableList<String>(0)
    private var nasaURL = mutableListOf<String>()
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        pics = view.findViewById(R.id.img_spacePic)
        img_spacePic.setImageResource(R.drawable.cat)

        viewModel = activity?.run {
            ViewModelProviders.of(this)[NasaViewModel::class.java]
        } ?: throw Exception("Invalid Activity")

        observeViewModel()

    }

    private fun observeViewModel() {


        viewModel.photoList.observe(viewLifecycleOwner, Observer { listURL ->
            listURL?.let {
                Log.d(TAG, "${it}")
                for (url in it) {
                    Log.d(TAG, "vm observe ${url.img_src.toString()}")
                    nasaURL.add("${url.img_src.toString()}")
                }

//                Picasso.get()
//                        .load(nasaURL[0])
//
//                        .into(pics)

                Picasso.get()
                    .load(nasaURL[0])
                    .placeholder(R.drawable.ic_launcher_background)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(img_spacePic)
            }
        })


    }


}